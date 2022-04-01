FROM node:16.14-alpine

MAINTAINER Vitor Ferreira <vrfvitor@hotmail.com>

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package.json ./
COPY package-lock.json ./

RUN npm install --silent --legacy-peer-deps
#RUN npm install react-scripts@5.0.0 -g --silent

COPY . ./

CMD ["npm", "start"]
