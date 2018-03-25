FROM nginx:latest

# --- Install npm and Sass --- #
RUN apt-get update && apt-get -y upgrade
RUN apt-get install -y curl
RUN apt-get install -y gnupg2
RUN curl -sL https://deb.nodesource.com/setup_7.x | bash -
RUN apt-get install -y nodejs
RUN apt-get install -y build-essential
RUN apt-get install -y ruby-full
RUN gem install sass


# --- Prepare for Deployment --- #
RUN mkdir /build/
COPY . /build/
WORKDIR /build/

# npm install mypackage
# sass mycss
# etc


############### RYAN YOU NEED TO SPECIFY WHAT FILES YOU WANT TO DEPLOY HERE ###################
############### RIGHT NOW IT IS DEPLOYING EVERYTHING THAT WAS BROUGHT IN AND BUILT ###################
# --- Deploy onto Nginx --- #
RUN cp /build/* /usr/share/nginx/html/