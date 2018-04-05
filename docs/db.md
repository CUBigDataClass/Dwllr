# Dwllr Database Documentation


## Schema Description

The database schema will be updated with each new stat as datasets are added. 

The keyspace name is "dwllr".

###  stats
+ zip 
    - Data Type: int
    - US Postal Zip code


## Schema To API Mappings

To remove the mapping of database attributes from the code base, a separate configuration file (/data-retrieval/mappings.txt) is used to indicate the mapping from database column to what the API serves it up as under stats.

The format of the file is as follows. Each line represents an individual mapping. Each line contains two items separated by a single whitespace. The first item is the name of the database column, and the second item is the name of the field in the "stats" object in a json response.

## Updating Database Schema

In order to document changes to the database, migrations are used to add new tables and alter any existing tables. This is done via a .cql file. Ex: brief-explaination-of-changes.cql It is important to add a migration number in front of the file name to keep the ordering.

The .cql file contains each alter or additional table as a statement. When a new database is added, all .cql files in the database folder are run against it to get it to it's current state. If a migration is written and an existing database needs those changes, only that migration is run against it. 

## Running Migrations

The run-migrations.sh file must be run after the cassandra container has been started. It will run through the migrations and run them from the start in alphabetical order. The first argument it takes must be the cassandra username, and the second must be the cassandra password. 