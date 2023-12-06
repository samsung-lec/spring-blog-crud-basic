FROM mysql

ENV MYSQL_ROOT_PASSWORD=root1234
ENV MYSQL_DATABASE=blogdb

COPY init.sql /docker-entrypoint-initdb.d/init.sql

CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]