FROM php:7.4-apache
RUN echo "ServerName 172.17.0.1" >> /etc/apache2/apache2.conf
RUN service apache2 restart
RUN docker-php-ext-install mysqli
COPY src .