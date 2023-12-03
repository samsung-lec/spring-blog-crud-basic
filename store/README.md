## MariaDB + Redis Docker

### 실행

```
docker-compose up -d
```

#### base image
- mariadb:10.4
- redis:latest

#### my.cnf 설정

- ##### /etc/mariadb/my.cnf

```
[client]
default-character-set = utf8mb4

[mysql]
default-character-set = utf8mb4

[mysqldump]
default-character-set = utf8mb4

[mysqld]
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci
skip-character-set-client-handshake
```
- urf8mb4
    - 이모지 가능
- skip-character-set-client-handshake
    - DB서버와 접속을 할 때 클아이언트는 자신이 사용할 문자셋 설정하며, 그 설정을 사용하도록 되어있지만 skip-character-set-client-handshake 옵션을 사용할 경우 클라이언트에서 설정한 문자셋을 무시하고 character_set_server값으로 설정

#### redis.conf 설정

- ##### /etc/redis/redis.conf

```
maxmemory 1g
maxmemory-policy allkeys-lru
```

- 최대 사용 메모리양은 1G
- 최대 사용 메모리를 초과할 시 가장 오래된 데이터를 지워서 메모리를 확보하며 가장 최근에 저장된 데이터를 사용