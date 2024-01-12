FROM nginx:1.25.2-alpine

WORKDIR /etc/nginx/conf.d/
COPY ./nginx/conf/default.conf .
COPY ./nginx/conf/proxy.conf .
ENTRYPOINT [ "/docker-entrypoint.sh" ]
EXPOSE 80:80
CMD [ "nginx", "-g", "daemon off;" ]
