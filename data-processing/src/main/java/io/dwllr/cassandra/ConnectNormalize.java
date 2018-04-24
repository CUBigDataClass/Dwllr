package io.dwllr.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
/*Used the following sources to help learn about and implement querying cassandra in java
http://www.baeldung.com/cassandra-with-java
*/
public class ConnectNormalize {
    static final String KEYSPACE = "dwllr";
    static final String TABLE = "normalize";
    static final String IP = "127.0.0.1";
    Cluster cluster = Cluster.builder().addContactPoint(IP).build();
    Session session = cluster.connect(KEYSPACE);

    public void query(String finalQuery){
        session.execute(finalQuery);
    }
    public void close(){
        session.close();
        cluster.close();
    }

}