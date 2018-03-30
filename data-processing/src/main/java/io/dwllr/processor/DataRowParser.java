package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.util.List;

public interface DataRowParser {
    public Query getQueryFromData(List<String> dataRow);
}
