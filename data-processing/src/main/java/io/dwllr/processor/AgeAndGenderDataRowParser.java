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

        String totalPopulation = dataRow.get(3);
        double population = Double.parseDouble(totalPopulation);

        String malePopulation = dataRow.get(4);
        double male = (Double.parseDouble(malePopulation)) / population;

        String femalePopulation = dataRow.get(5);
        double female = (Double.parseDouble(femalePopulation)) / population;

        //Age
        String underFiveString = dataRow.get(6);
        double underFive = (Double.parseDouble(underFiveString)) / population;

        String fiveToTenString = dataRow.get(9);
        double fiveToTen = (Double.parseDouble(fiveToTenString)) / population;

        String tenToFourteenString = dataRow.get(12);
        double tenToFourteen = (Double.parseDouble(tenToFourteenString)) / population;

        String fiveteenToNineteenString = dataRow.get(15);
        double fiveteenToNineteen = (Double.parseDouble(fiveteenToNineteenString)) / population;

        String twentyToTwentyfourString = dataRow.get(18);
        double twentyToTwentyfour = (Double.parseDouble(twentyToTwentyfourString)) / population;

        String twentyFiveToTwentynineString = dataRow.get(21);
        double twentyFiveToTwentynine = (Double.parseDouble(twentyFiveToTwentynineString)) / population;

        String thirtyToThirtyfourString = dataRow.get(24);
        double thirtyToThirtyfour = (Double.parseDouble(thirtyToThirtyfourString)) / population;

        String thirtyfiveToThirtynineString = dataRow.get(27);
        double thirtyfiveToThirtynine = (Double.parseDouble(thirtyfiveToThirtynineString)) / population;

        String fourtyToFourtyfourString = dataRow.get(30);
        double fourtyToFourtyfour = (Double.parseDouble(fourtyToFourtyfourString)) / population;

        String fourtyfiveToFourtynineString = dataRow.get(33);
        double fourtyfiveToFourtynine = (Double.parseDouble(fourtyfiveToFourtynineString)) / population;

        String fiftyToFiftyfourString = dataRow.get(36);
        double fiftyToFiftyfour = (Double.parseDouble(fiftyToFiftyfourString)) / population;

        String fiftyfiveToFiftynineString = dataRow.get(39);
        double fiftyfiveToFiftynine = (Double.parseDouble(fiftyfiveToFiftynineString)) / population;

        String sixtyToSixtyfourString = dataRow.get(42);
        double sixtyToSixtyfour = (Double.parseDouble(sixtyToSixtyfourString)) / population;

        String sixtyFiveToSixtynineString = dataRow.get(45);
        double sixtyFiveToSixtyNine = (Double.parseDouble(sixtyFiveToSixtynineString)) / population;

        String seventyToSeventyfourString = dataRow.get(48);
        double seventyToSeventyfour = (Double.parseDouble(seventyToSeventyfourString)) / population;

        String seventyfiveToSeventynineString = dataRow.get(51);
        double seventyfiveToSeventynine = (Double.parseDouble(seventyfiveToSeventynineString)) / population;

        String eightyToEightyfourString = dataRow.get(54);
        double eightyToEightyfour = (Double.parseDouble(eightyToEightyfourString)) / population;

        String eightyFiveAndOverString = dataRow.get(57);
        double eightyFiveAndOver = (Double.parseDouble(eightyFiveAndOverString)) / population;

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