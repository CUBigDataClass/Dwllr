package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class UrbanRuralDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        //ZCTA5 00601
        String zipCode = dataRow.get(2);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);

        String totalPopulation = dataRow.get(3);
        Double population = Double.parseDouble(totalPopulation);

        String urbanPopulation = dataRow.get(4);
        Double urban = Double.parseDouble(urbanPopulation);

        String ruralPopulation = dataRow.get(7);
        Double rural = Double.parseDouble(ruralPopulation);

        Double ruralPercent = rural / population;
        Double urbanPercent = urban / population;
        DecimalFormat df = new DecimalFormat("#");
        ruralPopulation = df.format(ruralPercent);
        urbanPopulation = df.format(urbanPercent);

        String queryString = "UPDATE stats SET urbanPopulation = '" + urbanPopulation + "', "
                + "ruralPopulation = '" + ruralPopulation + "' "
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}