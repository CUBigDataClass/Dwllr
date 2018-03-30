import unittest
import api
from flask import json
from cassandra.cluster import Cluster

class ApiTestCase(unittest.TestCase):
    def setUp(self):
        api.app.testing = True
        self.app = api.app.test_client()
        rv = api.session.execute("SELECT zip, population FROM stats where zip=80027")
        if len(rv.current_rows) == 0 or rv.current_rows[0].population != 22361: 
            api.session.execute("INSERT INTO stats (zip, population) VALUES (80027, 22361)")

    def test_search(self):
        rv = self.app.get("/api/search", data={"city": 80027})
        self.assertEqual(22361, json.loads(rv.data)["data"]["stats"]["population"])
        
if __name__ == '__main__':
    unittest.main()