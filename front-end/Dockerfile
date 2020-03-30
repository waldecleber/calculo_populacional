FROM node:10-alpine as angular
WORKDIR /app
COPY populacao-app/package.json /app
RUN npm install --silent
COPY ./populacao-app ./
RUN npm run build --prod

FROM nginx:alpine
VOLUME [ "/var/cache/nginx" ]
COPY --from=angular app/dist/populacao-app /usr/share/nginx/html

COPY populacao-app/config/nginx.conf /etc/nginx/conf.d/default.conf