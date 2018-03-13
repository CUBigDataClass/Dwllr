import unittest
from cassandra.cluster import Cluster

KEYSPACE = "testkeyspace"

class DataRetrievalTest(unittest.TestCase):

    """

    Data retrieval test using CQL queries. 
    Creates a keyspace called "testkeyspace", and fails
    if the keyspace exists already. Drops the 
    keyspace on tear down.

    """

    def setUp(self):
        self.cluster = Cluster(["127.0.0.1"])
        self.session = self.cluster.connect()
        if KEYSPACE in self.cluster.metadata.keyspaces:
            raise RuntimeError("Keyspace already defined")
        self.session.execute("CREATE KEYSPACE %s WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '1' }" % KEYSPACE)
        self.session.execute("USE %s" % KEYSPACE)
        self.session.execute("CREATE TABLE testtable (id int PRIMARY KEY, name text)")
        self.session.execute("INSERT INTO testtable (id, name) VALUES (1, 'justin')")

    def test_query(self):
        res = self.session.execute("SELECT * from testtable")
        self.assertEqual(res[0].id, 1)
        self.assertEqual(res[0].name, "justin")

    def tearDown(self):
        self.session.execute("DROP KEYSPACE %s" % KEYSPACE)
        
if __name__ == "__main__": 
    unittest.main()