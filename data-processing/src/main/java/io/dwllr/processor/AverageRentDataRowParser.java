package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class AverageRentDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        //ZCTA5 00601
        String zipCode = dataRow.get(2);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);

        String medianRent = dataRow.get(3);
        int rent;
        try{
            rent = Integer.parseInt(medianRent);
        }
        catch(NumberFormatException e){
            rent = 0;
        }
        String queryString = "UPDATE stats SET medianRent = '" + rent + "'"
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
    public Query getQueryFromDataNormalize(List<String> dataRow) {
        String zipCode = dataRow.get(2);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);

        String medianRent = dataRow.get(3);
        int rent;
        try{
            rent = Integer.parseInt(medianRent);
        }
        catch(NumberFormatException e){
            rent = 0;
        }
        double normalizeRent = ((double)rent - 0) / (3484.0 - 0);
        DecimalFormat df = new DecimalFormat("#.000");
        String queryString = "UPDATE normalize SET medianRent = '" + df.format(normalizeRent) + "'"
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}
