# Deployment Documentation

## Dependencies

- Docker
- Docker Compose


## Steps

1. docker-compose up -d cassandra
2. docker-compose exec cassandra /bin/bash
3. cd migrations && sleep 15
4. ./run-migrations.sh _username_ _password_
5. exit
6. docker-compose up -d (Go get a cup of coffee, this will take a while)
7. After a while, run docker-compose ps, if the processor has a state of Exit 0, then it has finished writing the datasets into the cassandra container and you can start hitting the api