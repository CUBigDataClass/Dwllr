package io.dwllr;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
/*Used the following sources to help learn about and implement querying cassandra in java
http://www.baeldung.com/cassandra-with-java
*/
public class Connect {
    private Cluster cluster;
    private Session session;

    public void connectToCassandra(String contactPoint,int portNum){
        Cluster.Builder builder = Cluster.builder().addContactPoint(contactPoint);
        builder.withPort(portNum);
        cluster = builder.build();
        session = cluster.connect();
    }
    public Session getSession(){
        return this.session;
    }
    public void close(){
        session.close();
        cluster.close();
    }

}
