for migration in ./*.cql
do
	cqlsh -u $1 -p $2 -f $migration
done
