FROM openjdk:8

# --- Install Gradle --- #
RUN apt-get update
RUN apt-get -y upgrade
RUN apt-get install -y software-properties-common 
RUN add-apt-repository -y ppa:cwchien/gradle
RUN apt-get update
RUN apt-get install -y --allow-unauthenticated gradle

# --- Deploy --- #
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN gradle build 
CMD ["java", "Main"]