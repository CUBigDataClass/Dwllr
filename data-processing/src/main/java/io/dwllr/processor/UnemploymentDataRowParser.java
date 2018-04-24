package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class UnemploymentDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        String unemployment = dataRow.get(1);
        String sampleSize = dataRow.get(2);
        if(sampleSize.equals("0") || unemployment.indexOf('%') < 0){
            unemployment = "0%";
        }
        unemployment = unemployment.substring(0, unemployment.length()-1);
        String queryString = "UPDATE stats SET unemployment = '" + unemployment + "'"
                + " WHERE zip = '" + dataRow.get(0) + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
    public Query getQueryFromDataNormalize(List<String> dataRow) {
        DecimalFormat df = new DecimalFormat("#.000");
        String unemployment = dataRow.get(1);
        String sampleSize = dataRow.get(2);
        if(sampleSize.equals("0") || unemployment.indexOf('%') < 0){
            unemployment = "0%";
        }
        unemployment = unemployment.substring(0, unemployment.length()-1);
        double normalizedUnemployment = (Double.parseDouble(unemployment) - 0) / (94);
        String queryString = "UPDATE normalize SET unemployment = '" + df.format(normalizedUnemployment) + "'"
                + " WHERE zip = '" + dataRow.get(0) + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}
