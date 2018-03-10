import com.datastax.driver.core.Session;
import com.datastax.driver.core.Cluster;
public class testQuery {
    public static void main(String []args){
        Cluster cluster;
        Session session;

        //Should use the default port 9042?
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        //keyspace name in this case is test
        session = cluster.connect("test");
        //create table data (name text primary key, age text, city text);
        session.execute("INSERT INTO data (name, age, city) VALUES ('Ryan', '21', 'Boulder')");
    }
}
