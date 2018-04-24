package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class AverageIncomeDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        String averageTotalIncome = dataRow.get(7);
        double income;
        try{
            income = Double.parseDouble(averageTotalIncome);
        }
        catch(NumberFormatException e){
            income = 0.0;
        }
        income = income * 1000;
        int finalIncome = (int)income;
        String queryString = "UPDATE stats SET averageIncome = '" + finalIncome + "'"
                + " WHERE zip = '" + dataRow.get(1) + "';";
        
        // Wrap queryString into Query here
        return new Query(queryString);
    }
    public Query getQueryFromDataNormalize(List<String> dataRow) {
        String averageTotalIncome = dataRow.get(7);
        double income;
        DecimalFormat df = new DecimalFormat("#.000");
        try{
            income = Double.parseDouble(averageTotalIncome);
        }
        catch(NumberFormatException e){
            income = 0.0;
        }
        income = income * 1000;
        double normalizedIncome = (income - 8841) / (1831837 - 8841);
        String queryString = "UPDATE normalize SET averageIncome = '" + df.format(normalizedIncome) + "'"
                + " WHERE zip = '" + dataRow.get(1) + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}
