package io.dwllr.processor;

import io.dwllr.cassandra.Query;

import java.text.DecimalFormat;
import java.util.List;

public class EducationDataRowParser implements DataRowParser {

    @Override
    public Query getQueryFromData(List<String> dataRow) {
        //ZCTA5 00601
        /*Note: I'm using statistics for the population over 25 years old
           because the stats would be weird for the younger age group
         */
        String zipCode = dataRow.get(1);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);
        Double population = 0.0;
        try{
            population = Double.parseDouble(dataRow.get(13));
        }
        catch(NumberFormatException e){
            population = 0.0;
        }
        String queryString;
        if (population == 0.0){
            queryString = "UPDATE stats SET noHighSchool = '" + 0 + "', "
                    + "highSchoolNoDiploma = '" + 0 +"', "
                    + "highSchoolGraduate = '" + 0 +"', "
                    + "someCollegeNoDegree = '" + 0 +"', "
                    + "associatesDegree = '" + 0 +"', "
                    + "bachelorsDegree = '" + 0 +"', "
                    + "graduateDegree = '" + 0 +"'"
                    + " WHERE zip = '" + 0 + "';";
        }
        else {
            Double noHighSchool = Double.parseDouble(dataRow.get(16));
            Double highSchoolNoDiploma = Double.parseDouble(dataRow.get(18));
            Double highSchoolGraduate = Double.parseDouble(dataRow.get(20));
            Double someCollegeNoDegree = Double.parseDouble(dataRow.get(22));
            Double associatesDegree = Double.parseDouble(dataRow.get(24));
            Double bachelorsDegree = Double.parseDouble(dataRow.get(26));
            Double graduateDegree = Double.parseDouble(dataRow.get(28));

            queryString = "UPDATE stats SET noHighSchool = '" + noHighSchool + "', "
                    + "highSchoolNoDiploma = '" + highSchoolNoDiploma + "', "
                    + "highSchoolGraduate = '" + highSchoolGraduate + "', "
                    + "someCollegeNoDegree = '" + someCollegeNoDegree + "', "
                    + "associatesDegree = '" + associatesDegree + "', "
                    + "bachelorsDegree = '" + bachelorsDegree + "', "
                    + "graduateDegree = '" + graduateDegree + "'"
                    + " WHERE zip = '" + zipCode + "';";
        }
        // Wrap queryString into Query here
        return new Query(queryString);
    }

    public Query getQueryFromDataNormalize(List<String> dataRow) {
        String zipCode = dataRow.get(1);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);
        Double population = 0.0;
        try{
            population = Double.parseDouble(dataRow.get(13));
        }
        catch(NumberFormatException e){
            population = 0.0;
        }
        String queryString;
        if (population == 0.0){
            queryString = "UPDATE normalize SET noHighSchool = '" + 0 + "', "
                    + "highSchoolNoDiploma = '" + 0 +"', "
                    + "highSchoolGraduate = '" + 0 +"', "
                    + "someCollegeNoDegree = '" + 0 +"', "
                    + "associatesDegree = '" + 0 +"', "
                    + "bachelorsDegree = '" + 0 +"', "
                    + "graduateDegree = '" + 0 +"'"
                    + " WHERE zip = '" + 0 + "';";
        }
        else {
            DecimalFormat df = new DecimalFormat("#.000");
            Double noHighSchool = (Double.parseDouble(dataRow.get(16)) - 0) / (100);
            Double highSchoolNoDiploma = Double.parseDouble(dataRow.get(18))/ (100);
            Double highSchoolGraduate = Double.parseDouble(dataRow.get(20))/ (100);
            Double someCollegeNoDegree = Double.parseDouble(dataRow.get(22))/ (100);
            Double associatesDegree = Double.parseDouble(dataRow.get(24))/ (100);
            Double bachelorsDegree = Double.parseDouble(dataRow.get(26))/ (100);
            Double graduateDegree = Double.parseDouble(dataRow.get(28))/ (100);

            queryString = "UPDATE normalize SET noHighSchool = '" + df.format(noHighSchool) + "', "
                    + "highSchoolNoDiploma = '" + df.format(highSchoolNoDiploma) + "', "
                    + "highSchoolGraduate = '" + df.format(highSchoolGraduate) + "', "
                    + "someCollegeNoDegree = '" + df.format(someCollegeNoDegree) + "', "
                    + "associatesDegree = '" + df.format(associatesDegree) + "', "
                    + "bachelorsDegree = '" + df.format(bachelorsDegree) + "', "
                    + "graduateDegree = '" + df.format(graduateDegree) + "'"
                    + " WHERE zip = '" + zipCode + "';";
        }
        // Wrap queryString into Query here
        return new Query(queryString);
    }
}