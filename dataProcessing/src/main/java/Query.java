import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import java.util.*;
public class Query {
    static final String KEYSPACE = "test";
    static final String TABLE = "sampledata";
    static final String IP = "127.0.0.1";
    static final String locationColumn = "location";
    Map <String, String> queryValues = new HashMap<String, String>();
    String finalQuery = "INSERT INTO " + TABLE + " (";
    public Query(String zipCode){
        this.queryValues.put(locationColumn, zipCode);
    }
    public void addValue(String column, String value){
        this.queryValues.put(column, value);
    }
    public void sendQuery(){
        Cluster cluster;
        Session session;
        createQuery();
        cluster = Cluster.builder().addContactPoint(IP).build();
        session = cluster.connect(KEYSPACE);
        session.execute(finalQuery);
        System.out.println(finalQuery);
    }
    private void createQuery(){
        addAttributeNames();
        addValues();
    }
    private void  addAttributeNames(){
        for (String key : queryValues.keySet()){
            finalQuery = finalQuery + key+",";
        }
        finalQuery = finalQuery.substring(0, finalQuery.length()-1) + ") ";
    }
    private void addValues(){
        finalQuery = finalQuery + "VALUES (";
        for(String value : queryValues.values()){
            finalQuery = finalQuery + "'"+value + "',";
        }
        finalQuery = finalQuery.substring(0, finalQuery.length()-1) + ")";
    }

}
