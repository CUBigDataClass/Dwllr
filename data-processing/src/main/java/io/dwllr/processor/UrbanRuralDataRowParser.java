package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class UrbanRuralDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        //ZCTA5 00601
        String zipCode = dataRow.get(1);
        Double ruralPercent, urbanPercent;
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);

        String totalPopulation = dataRow.get(3);
        double population;
        try{
            population = Double.parseDouble(totalPopulation);
        }
        catch(NumberFormatException e){
            population = 0.0;
        }
        if (population == 0.0){
            String queryString = "UPDATE stats SET urbanPopulation = '" + 0 + "', "
                    + "ruralPopulation = '" + 0 + "' "
                    + " WHERE zip = '" + zipCode + "';";

            // Wrap queryString into Query here
            return new Query(queryString);
        }
        String urbanPopulation = dataRow.get(4);
        Double urban = Double.parseDouble(urbanPopulation);

        String ruralPopulation = dataRow.get(7);
        Double rural = Double.parseDouble(ruralPopulation);
        if (population == 0){
            ruralPercent = 0.0;
            urbanPercent = 0.0;
        }
        else {
            ruralPercent = (rural / population) * 100;
            urbanPercent = (urban / population) * 100;
        }
        DecimalFormat df = new DecimalFormat("#.0");
        ruralPopulation = df.format(ruralPercent);
        urbanPopulation = df.format(urbanPercent);

        String queryString = "UPDATE stats SET urbanPopulation = '" + urbanPopulation + "', "
                + "ruralPopulation = '" + ruralPopulation + "' "
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }

    public Query getQueryFromDataNormalize(List<String> dataRow) {
        String zipCode = dataRow.get(1);
        Double ruralPercent, urbanPercent;
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);

        String totalPopulation = dataRow.get(3);
        double population;
        try{
            population = Double.parseDouble(totalPopulation);
        }
        catch(NumberFormatException e){
            population = 0.0;
        }
        if (population == 0.0){
            String queryString = "UPDATE stats SET urbanPopulation = '" + 0 + "', "
                    + "ruralPopulation = '" + 0 + "' "
                    + " WHERE zip = '" + zipCode + "';";

            // Wrap queryString into Query here
            return new Query(queryString);
        }
        String urbanPopulation = dataRow.get(4);
        Double urban = Double.parseDouble(urbanPopulation);

        String ruralPopulation = dataRow.get(7);
        Double rural = Double.parseDouble(ruralPopulation);
        if (population == 0){
            ruralPercent = 0.0;
            urbanPercent = 0.0;
        }
        else {
            ruralPercent = (rural / population) * 100;
            urbanPercent = (urban / population) * 100;
        }
        double ruralNormal = (ruralPercent - 0) / 100;
        double urbanNormal = urbanPercent /100;
        DecimalFormat df = new DecimalFormat("#.000");
        ruralPopulation = df.format(ruralNormal);
        urbanPopulation = df.format(urbanNormal);

        String queryString = "UPDATE normalize SET urbanPopulation = '" + urbanPopulation + "', "
                + "ruralPopulation = '" + ruralPopulation + "' "
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}