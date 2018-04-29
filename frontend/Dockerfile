# http://mherman.org/blog/2017/12/07/dockerizing-a-react-app/

# build environment
FROM node:8.0.0 as builder
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
ENV PATH /usr/src/app/node_modules/.bin:$PATH
COPY dwllr/package.json /usr/src/app/package.json
RUN npm install --silent
# RUN npm install react-scripts@1.1.1 -g --silent
COPY dwllr/ /usr/src/app
RUN npm run build

# production environment
FROM nginx:1.13.9-alpine
COPY --from=builder /usr/src/app/dist /usr/share/nginx/html
COPY ./default.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]