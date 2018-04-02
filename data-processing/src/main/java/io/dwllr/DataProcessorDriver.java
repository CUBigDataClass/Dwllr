package io.dwllr;

import io.dwllr.processor.CsvDataRowIterator;
import io.dwllr.cassandra.Query;
import io.dwllr.processor.PopulationDataRowParser;
import io.dwllr.processor.ZipDataRowParser;

import java.util.List;
import java.util.Optional;

public class DataProcessorDriver {

    public static void main(String[] args) {

        //TODO: Set this up to work with config and java reflections. Code setup right now to establish steel thread.
        //This is for adding the zip codes to the db
        String datasetFileName = "/home/user/Dropbox/Junior/bigData/Dwllr/data-processing/src/main/java/io/dwllr/processor/postal_codes";
        try{
            CsvDataRowIterator iterator= new CsvDataRowIterator(datasetFileName);
            Optional<List<String>> row = iterator.getNextRow();
            ZipDataRowParser zip = new ZipDataRowParser();
            while(row.isPresent()){
                Query query = zip.getQueryFromData(row.get());
                try{
                    query.sendQuery(query);
                } catch (Exception e){
                    System.out.println("QUERY FAILED");
                }
                row = iterator.getNextRow();
            }
        } catch (Exception e) {
            System.out.println("Error creating iterator with filename");
        }
        //This is for adding the population to the db
        datasetFileName = "/home/user/Dropbox/Junior/bigData/Dwllr/data-processing/src/main/java/io/dwllr/processor/population.csv";
        try{
            CsvDataRowIterator iterator= new CsvDataRowIterator(datasetFileName);
            Optional<List<String>> row = iterator.getNextRow();
            PopulationDataRowParser zip = new PopulationDataRowParser();
            while(row.isPresent()){
                Query query = zip.getQueryFromData(row.get());
                try{
                    query.sendQuery(query);
                } catch (Exception e){
                    System.out.println("QUERY FAILED");
                }
                row = iterator.getNextRow();
            }
        } catch (Exception e) {
            //Not really sure what else to put here for now
            System.out.println("Error creating iterator with filename");
        }

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
