FROM mysql

COPY init.sql /docker-entrypoint-initdb.d/init.sql

ENV MYSQL_ROOT_PASSWORD=root1234
ENV MYSQL_DATABASE=blogdb

CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]