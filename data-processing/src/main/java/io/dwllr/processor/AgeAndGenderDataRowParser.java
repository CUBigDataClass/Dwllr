package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class AgeAndGenderDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        //ZCTA5 00601
        String zipCode = dataRow.get(2);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);
        double population = Double.parseDouble(dataRow.get(3));
        double male = (Double.parseDouble(dataRow.get(4))) / population;
        double female = (Double.parseDouble(dataRow.get(5))) / population;

        //Age
        double underFive = (Double.parseDouble(dataRow.get(6))) / population;
        double fiveToTen = (Double.parseDouble(dataRow.get(9))) / population;
        double tenToFourteen = (Double.parseDouble(dataRow.get(12))) / population;
        double fiveteenToNineteen = (Double.parseDouble(dataRow.get(15))) / population;
        double twentyToTwentyfour = (Double.parseDouble(dataRow.get(18))) / population;
        double twentyFiveToTwentynine = (Double.parseDouble(dataRow.get(21))) / population;
        double thirtyToThirtyfour = (Double.parseDouble(dataRow.get(24))) / population;
        double thirtyfiveToThirtynine = (Double.parseDouble(dataRow.get(27))) / population;
        double fourtyToFourtyfour = (Double.parseDouble(dataRow.get(30))) / population;
        double fourtyfiveToFourtynine = (Double.parseDouble(dataRow.get(33))) / population;
        double fiftyToFiftyfour = (Double.parseDouble(dataRow.get(36))) / population;
        double fiftyfiveToFiftynine = (Double.parseDouble(dataRow.get(39))) / population;
        double sixtyToSixtyfour = (Double.parseDouble(dataRow.get(42))) / population;
        double sixtyFiveToSixtyNine = (Double.parseDouble(dataRow.get(45))) / population;
        double seventyToSeventyfour = (Double.parseDouble(dataRow.get(48))) / population;
        double seventyfiveToSeventynine = (Double.parseDouble(dataRow.get(51))) / population;
        double eightyToEightyfour = (Double.parseDouble(dataRow.get(54))) / population;
        double eightyFiveAndOver = (Double.parseDouble(dataRow.get(57))) / population;

        Double lessThanTen = fiveToTen + underFive;
        Double tenToNineteen = tenToFourteen + fiveteenToNineteen;
        Double twentyToTwentynine = twentyToTwentyfour + twentyFiveToTwentynine;
        Double thirtyToThirtynine = thirtyToThirtyfour + thirtyfiveToThirtynine;
        Double fourtyToFourtynine = fourtyToFourtyfour + fourtyfiveToFourtynine;
        Double fiftyToFiftynine = fiftyToFiftyfour + fiftyfiveToFiftynine;
        Double sixtyToSixtynine = sixtyToSixtyfour + sixtyFiveToSixtyNine;
        Double seventyToSeventynine = seventyToSeventyfour + seventyfiveToSeventynine;
        Double eightyAndUp = eightyToEightyfour + eightyFiveAndOver;


        DecimalFormat df = new DecimalFormat("#.0");

        String malePop = df.format(male);
        String femalePop = df.format(female);




        String queryString = "UPDATE stats SET malePercentage = '" + malePop + "', "
                + "femalePercentage = '" + femalePop +"', "
                + "lessThanTen = '" + lessThanTen +"', "
                + "tenToNineteen = '" + tenToNineteen +"', "
                + "twentyToTwentynine = '" + twentyToTwentynine +"', "
                + "thirtyToThirtynine = '" + thirtyToThirtynine +"', "
                + "fourtyToFourtynine = '" + fourtyToFourtynine +"', "
                + "fiftyToFiftynine = '" + fiftyToFiftynine +"', "
                + "sixtyToSixtynine = '" + sixtyToSixtynine +"', "
                + "seventyToSeventynine = '" + seventyToSeventynine +"', "
                + "eightyAndUp = '" + eightyAndUp + "' "
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}