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
   twoOrMoreRacePercentage text
);
