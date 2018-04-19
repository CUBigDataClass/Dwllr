package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.util.List;

public class UnemploymentDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        String unemployment = dataRow.get(1);
        unemployment = unemployment.substring(0, unemployment.length()-1);
        String queryString = "UPDATE stats SET unemployment = '" + unemployment + "'"
                + " WHERE zip = '" + dataRow.get(0) + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}
