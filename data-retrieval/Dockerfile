FROM tiangolo/uwsgi-nginx-flask:python3.6





########### THIS IS WHAT WILL NEED TO BE CHANGED ###########
########### THE FIRST ARG WILL NEED TO BE OUR APP FOLDER NAME ###########
COPY ./app /app

RUN pip install -r /app/requirements.txt