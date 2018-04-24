package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class DemographicDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        String zipCode = dataRow.get(1);
        String totalPopulation = dataRow.get(3);
        //ZCTA5 00612
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);
        DecimalFormat df = new DecimalFormat("#.00");
        double population;
        try{
            population = Double.parseDouble(totalPopulation);
        }
        catch(NumberFormatException e){
            population = 0.0;
        }
        if (population == 0.0){
            String queryString = "UPDATE stats SET whitePercentage = '" + 0 + "', "
                    + "blackPercentage = '" + 0 +"', "
                    + "nativeAmericanPercentage = '" + 0 +"', "
                    + "asianPercentage = '" + 0 +"', "
                    + "pacificIslanderPercentage = '" + 0 +"', "
                    + "otherPercentage = '" + 0 +"', "
                    + "twoOrMoreRacePercentage = '" + 0 +"'"
                    + " WHERE zip = '" + zipCode + "';";
            // Wrap queryString into Query here
            return new Query(queryString);
        }
        double whitePercentage = (Double.parseDouble(dataRow.get(5))/population) * 100;
        String whitePopulation = df.format(whitePercentage);

        double blackPercentage = (Double.parseDouble(dataRow.get(7))/population) * 100;
        String blackPopulation = df.format(blackPercentage);

        double nativeAmericanPercentage = (Double.parseDouble(dataRow.get(9))/population) * 100;
        String nativeAmericanPopulation = df.format(nativeAmericanPercentage);

        double asianPercentage = (Double.parseDouble(dataRow.get(11))/population) * 100;
        String asianPopulation = df.format(asianPercentage);

        double pacificIslanderPercentage = (Double.parseDouble(dataRow.get(13))/population) * 100;
        String pacificIslanderPopulation = df.format(pacificIslanderPercentage);

        double otherPercentage = (Double.parseDouble(dataRow.get(15))/population) * 100;
        String otherPopulation = df.format(otherPercentage);

        double twoOrMoreRacePercentage = (Double.parseDouble(dataRow.get(17))/population) * 100;
        String twoOrMoreRacePopulation = df.format(twoOrMoreRacePercentage);
        String queryString = "UPDATE stats SET whitePercentage = '" + whitePopulation + "', "
                + "blackPercentage = '" + blackPopulation +"', "
                + "nativeAmericanPercentage = '" + nativeAmericanPopulation +"', "
                + "asianPercentage = '" + asianPopulation +"', "
                + "pacificIslanderPercentage = '" + pacificIslanderPopulation +"', "
                + "otherPercentage = '" + otherPopulation +"', "
                + "twoOrMoreRacePercentage = '" + twoOrMoreRacePopulation +"'"
                + " WHERE zip = '" + zipCode + "';";
        // Wrap queryString into Query here
        return new Query(queryString);
    }
    public Query getQueryFromDataNormalize(List<String> dataRow) {
        String zipCode = dataRow.get(1);
        String totalPopulation = dataRow.get(3);
        //ZCTA5 00612
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);
        DecimalFormat df = new DecimalFormat("#.000");
        double population;
        try{
            population = Double.parseDouble(totalPopulation);
        }
        catch(NumberFormatException e){
            population = 0.0;
        }
        if (population == 0.0){
            String queryString = "UPDATE normalize SET whitePercentage = '" + 0 + "', "
                    + "blackPercentage = '" + 0 +"', "
                    + "nativeAmericanPercentage = '" + 0 +"', "
                    + "asianPercentage = '" + 0 +"', "
                    + "pacificIslanderPercentage = '" + 0 +"', "
                    + "otherPercentage = '" + 0 +"', "
                    + "twoOrMoreRacePercentage = '" + 0 +"'"
                    + " WHERE zip = '" + zipCode + "';";
            // Wrap queryString into Query here
            return new Query(queryString);
        }
        double whitePercentage = (((Double.parseDouble(dataRow.get(5))/population) * 100) - 0) / (100);
        String whitePopulation = df.format(whitePercentage);

        double blackPercentage = (((Double.parseDouble(dataRow.get(7))/population) * 100) - 0) / (100);
        String blackPopulation = df.format(blackPercentage);

        double nativeAmericanPercentage = (((Double.parseDouble(dataRow.get(9))/population) * 100) - 0) / (100);
        String nativeAmericanPopulation = df.format(nativeAmericanPercentage);

        double asianPercentage = (((Double.parseDouble(dataRow.get(11))/population) * 100) - 0) / (100);
        String asianPopulation = df.format(asianPercentage);

        double pacificIslanderPercentage = (((Double.parseDouble(dataRow.get(13))/population) * 100) - 0) / (72.85);
        String pacificIslanderPopulation = df.format(pacificIslanderPercentage);

        double otherPercentage = (((Double.parseDouble(dataRow.get(15))/population) * 100) - 0) / (100);
        String otherPopulation = df.format(otherPercentage);

        double twoOrMoreRacePercentage = (((Double.parseDouble(dataRow.get(17))/population) * 100) - 0) / (100);
        String twoOrMoreRacePopulation = df.format(twoOrMoreRacePercentage);

        String queryString = "UPDATE normalize SET whitePercentage = '" + df.format(whitePopulation) + "', "
                + "blackPercentage = '" + df.format(blackPopulation) +"', "
                + "nativeAmericanPercentage = '" + df.format(nativeAmericanPopulation) +"', "
                + "asianPercentage = '" + df.format(asianPopulation) +"', "
                + "pacificIslanderPercentage = '" + df.format(pacificIslanderPopulation) +"', "
                + "otherPercentage = '" + df.format(otherPopulation) +"', "
                + "twoOrMoreRacePercentage = '" + df.format(twoOrMoreRacePopulation) +"'"
                + " WHERE zip = '" + zipCode + "';";
        // Wrap queryString into Query here
        return new Query(queryString);
    }
}
