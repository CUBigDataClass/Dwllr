# Dwllr macOS Deployment Documentation

## Dependencies

- Docker
- Docker Compose


## Steps

1. Create data directories
	- sudo mkdir /data
	- sudo mkdir /data/datasets 
	- sudo mkdir /data/db
2. Download datasets ([https://www.dropbox.com/sh/br77g2q7av07ps9/AAAzjvd-VXK0DRY-MCJ8USeha?dl=0]()), unzip, and place them in some folder locally (not /data/datasets, that's used for some kind of docker magic)
3. Modify docker-compose.yml
	- Delete line 17, `/data/datasets/:/home/user/Documents/dataSets/`
	- Change `/data/datasets/:/home/user/Documents/dataSets/` to `<where your datasets are>:/home/user/Documents/dataSets/`
4. Docker -> Preferences -> File Sharing -> click `+` -> enter `/data` -> click `Apply & Restart`
5. docker-compose up -d cassandra
6. docker-compose exec cassandra /bin/bash
7. cd migrations && sleep 15
8. ./run-migrations.sh _username_ _password_
9. exit
10. docker-compose up -d (Go get a cup of coffee, this will take a while)
11. After a while, run docker-compose ps, if the processor has a state of Exit 0, then it has finished writing the datasets into the cassandra container and you can start hitting the api