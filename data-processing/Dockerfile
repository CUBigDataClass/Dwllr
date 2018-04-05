FROM openjdk:8

# Install Maven
RUN apt-get update
RUN apt-get install curl tar
ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# --- Deploy --- #
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN /bin/bash ./download-datasets.sh
RUN mvn clean install
CMD ["java", "-jar", "./target/dwllr-data-processing-1.0-SNAPSHOT-jar-with-dependencies.jar"]