# FROM nginx:latest

# # --- Install npm and Sass --- #
# RUN apt-get update && apt-get -y upgrade
# RUN apt-get install -y curl
# RUN apt-get install -y gnupg2
# RUN curl -sL https://deb.nodesource.com/setup_7.x | bash -
# RUN apt-get install -y nodejs
# RUN apt-get install -y build-essential
# RUN apt-get install -y ruby-full
# RUN gem install sass


# # --- Prepare for Deployment --- #
# RUN mkdir /build/
# COPY . /build/
# WORKDIR /build/

# # npm install mypackage
# # sass mycss
# # etc


# # --- Deploy onto Nginx --- #
# RUN cp /build/* /usr/share/nginx/html/


# http://mherman.org/blog/2017/12/07/dockerizing-a-react-app/

# build environment
FROM node:8.0.0 as builder
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
ENV PATH /usr/src/app/node_modules/.bin:$PATH
COPY dwllr/package.json /usr/src/app/package.json
RUN npm install --silent
RUN npm install react-scripts@1.1.1 -g --silent
COPY dwllr/ /usr/src/app
RUN npm run build

# production environment
FROM nginx:1.13.9-alpine
COPY --from=builder /usr/src/app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]