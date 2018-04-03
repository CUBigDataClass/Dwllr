CREATE KEYSPACE IF NOT EXISTS dwllr WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use dwllr;

CREATE TABLE IF NOT EXISTS stats (
   zip int PRIMARY KEY,
   population int
);
