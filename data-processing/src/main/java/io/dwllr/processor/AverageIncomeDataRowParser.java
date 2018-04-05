package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.util.List;

public class AverageIncomeDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        String averageTotalIncome = dataRow.get(7);
        double income = Double.parseDouble(averageTotalIncome);
        income = income * 1000;
        int finalIncome = (int)income;
        String queryString = "UPDATE stats SET averageIncome = '" + finalIncome + "'"
                + " WHERE zip = '" + dataRow.get(1) + "';";
        
        // Wrap queryString into Query here
        return new Query(queryString);
    }
}
