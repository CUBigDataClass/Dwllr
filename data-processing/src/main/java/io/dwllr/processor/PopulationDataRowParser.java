package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class PopulationDataRowParser implements DataRowParser {
    /*  Dataset: https://blog.splitwise.com/2013/09/18/the-2010-us-census-population-by-zip-code-totally-free/
        Format: Zip Code, 2010 Census Population
    */

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        int population;
        try{
            population = Integer.parseInt(dataRow.get(1));
        }
        catch(NumberFormatException e){
            population = 0;
        }
        String queryString = "UPDATE stats SET population = '" + population + "'"
                + " WHERE zip = '" + dataRow.get(0) + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }


    public Query getQueryFromDataNormalize(List<String> dataRow) {
        DecimalFormat df = new DecimalFormat("#.000");
        int population;
        try{
            population = Integer.parseInt(dataRow.get(1));
        }
        catch(NumberFormatException e){
            population = 0;
        }
        double normalizePopulation = ((double)population - 0) / (113916);
        String queryString = "UPDATE normalize SET population = '" + df.format(normalizePopulation) + "'"
                + " WHERE zip = '" + dataRow.get(0) + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}
