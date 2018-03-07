FROM tiangolo/uwsgi-nginx-flask:python3.6

RUN pip install -r requirements.txt

########### THIS IS WHAT WILL NEED TO BE CHANGED ###########
########### THE FIRST ARG WILL NEED TO BE OUR APP FOLDER NAME ###########
COPY ./app /app