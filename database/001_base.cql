CREATE KEYSPACE IF NOT EXISTS dwllr WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use dwllr;

CREATE TABLE IF NOT EXISTS stats (
   zip text PRIMARY KEY,
   population text,
   unemployment text,
   averageIncome text,
   whitePercentage text,
   blackPercentage text,
   nativeAmericanPercentage text,
   asianPercentage text,
   pacificIslanderPercentage text,
   otherPercentage text,
   twoOrMoreRacePercentage text,
   medianRent text,
   malePercentage text,
   femalePercentage text,
   lessThanTen text,
   tenToNineteen text,
   twentyToTwentynine text,
   thirtyToThirtynine text,
   fourtyToFourtynine text,
   fiftyToFiftynine text,
   sixtyToSixtynine text,
   seventyToSeventynine text,
   eightyAndUp text,
   urbanPopulation text,
   ruralPopulation text,
   noHighSchool text,
   highSchoolNoDiploma text,
   highSchoolGraduate text,
   someCollegeNoDegree text,
   associatesDegree text,
   bachelorsDegree text,
   graduateDegree text
);
