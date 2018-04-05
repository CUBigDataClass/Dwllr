package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.util.List;

public class UnemploymentDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        String queryString = "UPDATE stats SET unemployment = '" + dataRow.get(1) + "'"
                + " WHERE zip = '" + dataRow.get(0) + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}
