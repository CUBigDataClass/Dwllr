package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class AgeAndGenderDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        //ZCTA5 00601
        String zipCode = dataRow.get(1);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);
        DecimalFormat df = new DecimalFormat("#.0");
        double population;
        try{
            population = Double.parseDouble(dataRow.get(3));
        }
        catch(NumberFormatException e){
            population = 0.0;
        }
        if (population == 0.0){
            String queryString = "UPDATE stats SET malePercentage = '" + 0 + "', "
                    + "femalePercentage = '" + 0 +"', "
                    + "lessThanTen = '" + df.format(0) +"', "
                    + "tenToNineteen = '" + df.format(0) +"', "
                    + "twentyToTwentynine = '" + df.format(0) +"', "
                    + "thirtyToThirtynine = '" + df.format(0) +"', "
                    + "fourtyToFourtynine = '" + df.format(0) +"', "
                    + "fiftyToFiftynine = '" + df.format(0) +"', "
                    + "sixtyToSixtynine = '" + df.format(0) +"', "
                    + "seventyToSeventynine = '" + df.format(0) +"', "
                    + "eightyAndUp = '" + df.format(0) + "' "
                    + " WHERE zip = '" + zipCode + "';";

            // Wrap queryString into Query here
            return new Query(queryString);
        }
        double male = ((Double.parseDouble(dataRow.get(4))) / population) * 100;
        double female = ((Double.parseDouble(dataRow.get(5))) / population) * 100;
        //Age
        double underFive = (Double.parseDouble(dataRow.get(6)));
        double fiveToTen = (Double.parseDouble(dataRow.get(9)));
        double tenToFourteen = (Double.parseDouble(dataRow.get(12)));
        double fiveteenToNineteen = (Double.parseDouble(dataRow.get(15)));
        double twentyToTwentyfour = (Double.parseDouble(dataRow.get(18)));
        double twentyFiveToTwentynine = (Double.parseDouble(dataRow.get(21)));
        double thirtyToThirtyfour = (Double.parseDouble(dataRow.get(24)));
        double thirtyfiveToThirtynine = (Double.parseDouble(dataRow.get(27)));
        double fourtyToFourtyfour = (Double.parseDouble(dataRow.get(30)));
        double fourtyfiveToFourtynine = (Double.parseDouble(dataRow.get(33)));
        double fiftyToFiftyfour = (Double.parseDouble(dataRow.get(36)));
        double fiftyfiveToFiftynine = (Double.parseDouble(dataRow.get(39)));
        double sixtyToSixtyfour = (Double.parseDouble(dataRow.get(42)));
        double sixtyFiveToSixtyNine = (Double.parseDouble(dataRow.get(45)));
        double seventyToSeventyfour = (Double.parseDouble(dataRow.get(48)));
        double seventyfiveToSeventynine = (Double.parseDouble(dataRow.get(51)));
        double eightyToEightyfour = (Double.parseDouble(dataRow.get(54)));
        double eightyFiveAndOver = (Double.parseDouble(dataRow.get(57)));

        double lessThanTen = fiveToTen + underFive;
        double tenToNineteen = tenToFourteen + fiveteenToNineteen;
        double twentyToTwentynine = twentyToTwentyfour + twentyFiveToTwentynine;
        double thirtyToThirtynine = thirtyToThirtyfour + thirtyfiveToThirtynine;
        double fourtyToFourtynine = fourtyToFourtyfour + fourtyfiveToFourtynine;
        double fiftyToFiftynine = fiftyToFiftyfour + fiftyfiveToFiftynine;
        double sixtyToSixtynine = sixtyToSixtyfour + sixtyFiveToSixtyNine;
        double seventyToSeventynine = seventyToSeventyfour + seventyfiveToSeventynine;
        double eightyAndUp = eightyToEightyfour + eightyFiveAndOver;

        String malePop = df.format(male);
        String femalePop = df.format(female);

        String queryString = "UPDATE stats SET malePercentage = '" + malePop + "', "
                + "femalePercentage = '" + femalePop +"', "
                + "lessThanTen = '" + df.format(lessThanTen) +"', "
                + "tenToNineteen = '" + df.format(tenToNineteen) +"', "
                + "twentyToTwentynine = '" + df.format(twentyToTwentynine) +"', "
                + "thirtyToThirtynine = '" + df.format(thirtyToThirtynine) +"', "
                + "fourtyToFourtynine = '" + df.format(fourtyToFourtynine) +"', "
                + "fiftyToFiftynine = '" + df.format(fiftyToFiftynine) +"', "
                + "sixtyToSixtynine = '" + df.format(sixtyToSixtynine) +"', "
                + "seventyToSeventynine = '" + df.format(seventyToSeventynine) +"', "
                + "eightyAndUp = '" + df.format(eightyAndUp) + "' "
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }

    public Query getQueryFromDataNormalize(List<String> dataRow) {
        //ZCTA5 00601
        String zipCode = dataRow.get(1);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);
        DecimalFormat df = new DecimalFormat("#.000");
        double population;
        try{
            population = Double.parseDouble(dataRow.get(3));
        }
        catch(NumberFormatException e){
            population = 0.0;
        }
        if (population == 0.0){
            String queryString = "UPDATE normalize SET malePercentage = '" + 0 + "', "
                    + "femalePercentage = '" + 0 +"', "
                    + "lessThanTen = '" + df.format(0) +"', "
                    + "tenToNineteen = '" + df.format(0) +"', "
                    + "twentyToTwentynine = '" + df.format(0) +"', "
                    + "thirtyToThirtynine = '" + df.format(0) +"', "
                    + "fourtyToFourtynine = '" + df.format(0) +"', "
                    + "fiftyToFiftynine = '" + df.format(0) +"', "
                    + "sixtyToSixtynine = '" + df.format(0) +"', "
                    + "seventyToSeventynine = '" + df.format(0) +"', "
                    + "eightyAndUp = '" + df.format(0) + "' "
                    + " WHERE zip = '" + zipCode + "';";

            // Wrap queryString into Query here
            return new Query(queryString);
        }
        // Max = 100, Min = 100
        double male = ((((Double.parseDouble(dataRow.get(4))) / population) * 100) - 0) / 100;
        double female = ((((Double.parseDouble(dataRow.get(5))) / population) * 100) - 0) / 100;
        //Age
        double underFive = (Double.parseDouble(dataRow.get(6)));
        double fiveToTen = (Double.parseDouble(dataRow.get(9)));
        double tenToFourteen = (Double.parseDouble(dataRow.get(12)));
        double fiveteenToNineteen = (Double.parseDouble(dataRow.get(15)));
        double twentyToTwentyfour = (Double.parseDouble(dataRow.get(18)));
        double twentyFiveToTwentynine = (Double.parseDouble(dataRow.get(21)));
        double thirtyToThirtyfour = (Double.parseDouble(dataRow.get(24)));
        double thirtyfiveToThirtynine = (Double.parseDouble(dataRow.get(27)));
        double fourtyToFourtyfour = (Double.parseDouble(dataRow.get(30)));
        double fourtyfiveToFourtynine = (Double.parseDouble(dataRow.get(33)));
        double fiftyToFiftyfour = (Double.parseDouble(dataRow.get(36)));
        double fiftyfiveToFiftynine = (Double.parseDouble(dataRow.get(39)));
        double sixtyToSixtyfour = (Double.parseDouble(dataRow.get(42)));
        double sixtyFiveToSixtyNine = (Double.parseDouble(dataRow.get(45)));
        double seventyToSeventyfour = (Double.parseDouble(dataRow.get(48)));
        double seventyfiveToSeventynine = (Double.parseDouble(dataRow.get(51)));
        double eightyToEightyfour = (Double.parseDouble(dataRow.get(54)));
        double eightyFiveAndOver = (Double.parseDouble(dataRow.get(57)));

        double lessThanTen = ((fiveToTen + underFive)-0.0) / (65.1 - 0.0);
        double tenToNineteen = ((tenToFourteen + fiveteenToNineteen)-0) / (99.5-0.0);
        double twentyToTwentynine = (twentyToTwentyfour + twentyFiveToTwentynine) / 100.0;
        double thirtyToThirtynine = (thirtyToThirtyfour + thirtyfiveToThirtynine) / 100.0;
        double fourtyToFourtynine = (fourtyToFourtyfour + fourtyfiveToFourtynine)/ 100.0;
        double fiftyToFiftynine = (fiftyToFiftyfour + fiftyfiveToFiftynine)/ 100.0;
        double sixtyToSixtynine = (sixtyToSixtyfour + sixtyFiveToSixtyNine)/ 100.0;
        double seventyToSeventynine = (seventyToSeventyfour + seventyfiveToSeventynine)/ 100.0;
        double eightyAndUp = (eightyToEightyfour + eightyFiveAndOver)/ 100.0;

        String malePop = df.format(male);
        String femalePop = df.format(female);

        String queryString = "UPDATE normalize SET malePercentage = '" + malePop + "', "
                + "femalePercentage = '" + femalePop +"', "
                + "lessThanTen = '" + df.format(lessThanTen) +"', "
                + "tenToNineteen = '" + df.format(tenToNineteen) +"', "
                + "twentyToTwentynine = '" + df.format(twentyToTwentynine) +"', "
                + "thirtyToThirtynine = '" + df.format(thirtyToThirtynine) +"', "
                + "fourtyToFourtynine = '" + df.format(fourtyToFourtynine) +"', "
                + "fiftyToFiftynine = '" + df.format(fiftyToFiftynine) +"', "
                + "sixtyToSixtynine = '" + df.format(sixtyToSixtynine) +"', "
                + "seventyToSeventynine = '" + df.format(seventyToSeventynine) +"', "
                + "eightyAndUp = '" + df.format(eightyAndUp) + "' "
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}