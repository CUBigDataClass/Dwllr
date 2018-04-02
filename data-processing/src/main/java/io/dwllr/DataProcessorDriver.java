package io.dwllr;

public class DataProcessorDriver {

    public static void main(String[] args) {


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
