package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.util.List;

public class PopulationDataRowParser implements DataRowParser {
    /*  Dataset: https://blog.splitwise.com/2013/09/18/the-2010-us-census-population-by-zip-code-totally-free/
        Format: Zip Code, 2010 Census Population
    */

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        String queryString = "UPDATE stats SET population = " + dataRow.get(1)
                + " WHERE zip = " + dataRow.get(0) + ";";

        // Wrap queryString into Query here
        String column = "population";
        return new Query(dataRow.get(0), column, dataRow.get(1));
    }
}
