package io.dwllr.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import java.util.*;
public class Query {
    static final String TABLE = "stats";
    String zipCode;
    String columnName;
    String columnValue;
    List<String> columns = new ArrayList<String>();
    String finalQuery = "UPDATE " + TABLE + " SET ";
    public Query(String zipCode, String columnName, String columnValue){
        this.zipCode = zipCode;
        this.columnName = columnName;
        this.columnValue = columnValue;
    }
    public Query(String zipCode, String columnValue){
        this.zipCode = zipCode;
        this.columnName = "empty";
        this.columnValue = columnValue;
    }
    public Query(String query){
        finalQuery = query;
    }
    public String getFinalQuery(){
        return finalQuery;
    }
    public String getQuery(){
        createQuery();
        return finalQuery;
    }
    public String getNormalizedQuery(){
        createNormalizedQuery();
        return finalQuery;
    }
    private void createNormalizedQuery(){
        if(this.columnName.equals("empty")){
            createColumnList();
            finalQuery = "INSERT INTO " + "normalize" + " (";
            for(String column : columns){
                finalQuery = finalQuery + column +", ";
            }
            finalQuery = finalQuery.substring(0, finalQuery.length()-2) + ") ";
            finalQuery = finalQuery + "VALUES (";
            for (int i=0; i<columns.size();i++){
                if (i == 0){
                    finalQuery = finalQuery + "'"+zipCode +"', ";
                }
                else{
                    finalQuery = finalQuery + "'0',";
                }
            }
            finalQuery = finalQuery.substring(0, finalQuery.length()-1) + ")";
        }
        else{
            finalQuery = finalQuery + columnName + " = " + "'"+columnValue+"'" + " WHERE zip = '" +zipCode + "'";
        }
    }
    private void createQuery() {
        if(this.columnName.equals("empty")){
            createColumnList();
            finalQuery = "INSERT INTO " + TABLE + " (";
            for(String column : columns){
                finalQuery = finalQuery + column +", ";
            }
            finalQuery = finalQuery.substring(0, finalQuery.length()-2) + ") ";
            finalQuery = finalQuery + "VALUES (";
            for (int i=0; i<columns.size();i++){
                if (i == 0){
                    finalQuery = finalQuery + "'"+zipCode +"', ";
                }
                else{
                    finalQuery = finalQuery + "'0',";
                }
            }
            finalQuery = finalQuery.substring(0, finalQuery.length()-1) + ")";
        }
        else{
            finalQuery = finalQuery + columnName + " = " + "'"+columnValue+"'" + " WHERE zip = '" +zipCode + "'";
        }
    }
    private void createColumnList(){
        columns.add("zip");
        columns.add("population");
        columns.add("unemployment");
        columns.add("averageIncome");
        columns.add("whitePercentage");
        columns.add("blackPercentage");
        columns.add("nativeAmericanPercentage");
        columns.add("asianPercentage");
        columns.add("pacificIslanderPercentage");
        columns.add("otherPercentage");
        columns.add("twoOrMoreRacePercentage");
        columns.add("medianRent");
        columns.add("malePercentage");
        columns.add("femalePercentage");
        columns.add("lessThanTen");
        columns.add("tenToNineteen");
        columns.add("twentyToTwentynine");
        columns.add("thirtyToThirtynine");
        columns.add("fourtyToFourtynine");
        columns.add("fiftyToFiftynine");
        columns.add("sixtyToSixtynine");
        columns.add("seventyToSeventynine");
        columns.add("eightyAndUp");
        columns.add("urbanPopulation");
        columns.add("ruralPopulation");
        columns.add("noHighSchool");
        columns.add("highSchoolNoDiploma");
        columns.add("highSchoolGraduate");
        columns.add("someCollegeNoDegree");
        columns.add("associatesDegree");
        columns.add("bachelorsDegree");
        columns.add("graduateDegree");
    }

}
