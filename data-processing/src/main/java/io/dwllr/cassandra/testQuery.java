package io.dwllr.cassandra;

public class testQuery {
    public static void main(String []args){
        Query query = new Query("80302");
        query.addValue("name", "Ryan");
        query.addValue("age", "21");
        query.addValue("city", "Boulder");
        query.sendQuery();
    }
}
