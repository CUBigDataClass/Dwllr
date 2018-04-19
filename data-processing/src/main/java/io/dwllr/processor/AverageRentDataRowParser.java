package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.util.List;

public class AverageRentDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        //ZCTA5 00601
        String zipCode = dataRow.get(2);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);

        String medianRent = dataRow.get(3);
        double rent = Integer.parseInt(medianRent);
        String queryString = "UPDATE stats SET medianRent = '" + rent + "'"
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}
