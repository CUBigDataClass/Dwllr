package io.dwllr;
import io.dwllr.cassandra.Connect;
import io.dwllr.processor.*;
import io.dwllr.cassandra.Query;

import java.util.List;
import java.util.Optional;

public class DataProcessorDriver {
    public static void main(String[] args) {

        Connect connection = new Connect();
        //Will need to set these filepaths to wherever the file is located. Will include the file locations in the config when its created.
        String zipCodeFile = "/datasets/us_postal_codes.csv";
        String populationFile = "/datasets/population.csv";
        String unemploymentFile = "/home/user/Documents/dataSets/unemployment.csv";
        String averageIncomeFile = "/home/user/Documents/dataSets/averageIncome.csv";

        //TODO: Set this up to work with config and java reflections. Code setup right now to establish steel thread.

        //This is for adding the zip codes to the db
        try{
            CsvDataRowIterator iterator= new CsvDataRowIterator(zipCodeFile);
            Optional<List<String>> row = iterator.getNextRow();
            ZipDataRowParser zip = new ZipDataRowParser();
            Query query;
            while(row.isPresent()){
                query = zip.getQueryFromData(row.get());
                try{
                    String finalQuery = query.getQuery();
                    connection.query(finalQuery);
                } catch (Exception e){
                    System.out.println("QUERY FAILED");
                }
                row = iterator.getNextRow();
            }
        } catch (Exception e) {
            System.out.println("Error creating iterator with filename");
        }


        //This is for adding the population to the db
        try{
            CsvDataRowIterator iterator= new CsvDataRowIterator(populationFile);
            Optional<List<String>> row = iterator.getNextRow();
            PopulationDataRowParser pop = new PopulationDataRowParser();
            Query query;
            while(row.isPresent()){
                query = pop.getQueryFromData(row.get());
                try{
                    String finalQuery = query.getFinalQuery();
                    connection.query(finalQuery);
                } catch (Exception e){
                    System.out.println("QUERY FAILED");
                }
                row = iterator.getNextRow();
            }
        } catch (Exception e) {
            System.out.println("Error creating iterator with filename");
        }

        //This is for adding the unemployment to the db
        try{
            CsvDataRowIterator iterator= new CsvDataRowIterator(unemploymentFile);
            Optional<List<String>> row = iterator.getNextRow();
            UnemploymentDataRowParser pop = new UnemploymentDataRowParser();
            Query query;
            while(row.isPresent()){
                query = pop.getQueryFromData(row.get());
                try{
                    String finalQuery = query.getFinalQuery();
                    connection.query(finalQuery);
                } catch (Exception e){
                    System.out.println("QUERY FAILED");
                }
                row = iterator.getNextRow();
            }
        } catch (Exception e) {
            System.out.println("Error creating iterator with filename");
        }

        try{
            CsvDataRowIterator iterator= new CsvDataRowIterator(averageIncomeFile);
            Optional<List<String>> row = iterator.getNextRow();
            AverageIncomeDataRowParser pop = new AverageIncomeDataRowParser();
            Query query;
            while(row.isPresent()){
                query = pop.getQueryFromData(row.get());
                try{
                    String finalQuery = query.getFinalQuery();
                    connection.query(finalQuery);
                } catch (Exception e){
                    System.out.println("QUERY FAILED");
                }
                row = iterator.getNextRow();
            }
        } catch (Exception e) {
            System.out.println("Error creating iterator with filename");
        }
        connection.close();
        /*
        Driver pseudocode:

        for each dataset:
            create a new data row iterator

            while get next row isn't empty:
                get query from data row parser
                buffer query
                if query buffer reaches buffer limit (we decide this value, some constant)
                    send to query manager to run in cassandra
            close iterator

        I think we can get the dataset list from some kind of config file. Then use java reflection features to spawn the correct classes
        Ex:

        datasets:
            zips:
                dataRowIterator: CsvDataRowIterator
                dataRowParser: ZipDataRowParser
                file: all-us-zip-codes.csv
            population:
                dataRowIterator: CsvDataRowIterator
                dataRowParser: PopulationDataRowParser
                file: population.csv
         */
    }
}
