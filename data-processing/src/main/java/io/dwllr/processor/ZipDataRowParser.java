package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.util.List;

public class ZipDataRowParser implements DataRowParser {
    /*  Dataset: https://www.aggdata.com/node/86
        Format: Zip Code, Place Name, State, State Abbreviation, County, Latitude, Longitude
    */

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        String column = "zip";
        return new Query(dataRow.get(0), column);
    }
}