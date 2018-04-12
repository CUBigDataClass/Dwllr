# FROM tiangolo/uwsgi-nginx-flask:python3.6

# COPY ./app/requirements.txt /app/requirements.txt

# RUN pip install -r /app/requirements.txt

# COPY ./app/ /app/


FROM python:latest
COPY ./app/requirements.txt /app/requirements.txt
RUN pip install -r /app/requirements.txt
COPY ./app/ /app
WORKDIR /app
ENTRYPOINT ["python3"]
CMD ["main.py"]