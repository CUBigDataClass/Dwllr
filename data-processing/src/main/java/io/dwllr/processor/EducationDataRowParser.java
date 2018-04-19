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
        String zipCode = dataRow.get(2);
        int space = zipCode.indexOf(' ');
        zipCode = zipCode.substring(space+1);
        Double population = Double.parseDouble(dataRow.get(13));
        Double noHighSchool = Double.parseDouble(dataRow.get(16));
        Double highSchoolNoDiploma = Double.parseDouble(dataRow.get(18));
        Double highSchoolGraduate = Double.parseDouble(dataRow.get(20));
        Double someCollegeNoDegree = Double.parseDouble(dataRow.get(22));
        Double associatesDegree = Double.parseDouble(dataRow.get(24));
        Double bachelorsDegree = Double.parseDouble(dataRow.get(26));
        Double graduateDegree = Double.parseDouble(dataRow.get(28));

        String queryString = "UPDATE stats SET noHighSchool = '" + noHighSchool + "', "
                + "highSchoolNoDiploma = '" + highSchoolNoDiploma +"', "
                + "highSchoolGraduate = '" + highSchoolGraduate +"', "
                + "someCollegeNoDegree = '" + someCollegeNoDegree +"', "
                + "associatesDegree = '" + associatesDegree +"', "
                + "bachelorsDegree = '" + bachelorsDegree +"', "
                + "graduateDegree = '" + graduateDegree +"'"
                + " WHERE zip = '" + zipCode + "';";

        // Wrap queryString into Query here
        return new Query(queryString);
    }
}