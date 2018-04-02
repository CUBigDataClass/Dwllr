package io.dwllr.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CsvDataRowIterator implements DataRowIterator {
    private boolean passedFirstRow = false;
    private String datasetFileName;
    private BufferedReader reader = null;


    public CsvDataRowIterator(String datasetFileName) throws DatasetException {
        this.datasetFileName = datasetFileName;
        try {
            File file = new File(datasetFileName);
            System.out.println(datasetFileName);
            this.reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            throw new DatasetException("File not found for dataset: " + datasetFileName);
        }
    }

    @Override
    public Optional<List<String>> getNextRow() throws DatasetException {
        String line;

        try {
            if (!passedFirstRow) {
                reader.readLine(); // Skip first line, in CSV this is column headers
                this.passedFirstRow = true;
            }
            line = reader.readLine();

        } catch (IOException e) {
            throw new DatasetException("Issue reading dataset after opening for dataset: " + datasetFileName);
        }

        if (line == null || line.equals("")) {
            return Optional.empty();
        }

        return splitLine(line);
    }

    @Override
    public void closeDataset() throws DatasetException{
        try {
            reader.close();
        } catch (IOException e) {
            throw new DatasetException("Issue closing dataset file for dataset: " + datasetFileName);
        }
    }

    private Optional<List<String>> splitLine(String line) {
        return Optional.of(Arrays.asList(line.split(",")));
    }
}
