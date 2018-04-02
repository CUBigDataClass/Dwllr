package io.dwllr.processor;

import java.util.List;
import java.util.Optional;

public interface DataRowIterator {
    public Optional<List<String>> getNextRow() throws DatasetException;
    public void closeDataset() throws DatasetException;
}
