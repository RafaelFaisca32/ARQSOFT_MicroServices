# Deployment


## Docker

The deployment methedology used for deployment was Docker, taking advantage of the
docker compose strategy.

## Image Build

To run the docker compose is necessary to build the image of each microservice and the gateway

```bash
docker build ./shop -t gorgeous-sandwich.shop:0.0.1-SNAPSHOT &
docker build ./promotion -t gorgeous-sandwich.promotion:0.0.1-SNAPSHOT &
docker build ./user -t gorgeous-sandwich.user:0.0.1-SNAPSHOT &
docker build ./SandwichGQL -t gorgeous-sandwich.sandwich:0.0.1-SNAPSHOT &
docker build ./order -t gorgeous-sandwich.order:0.0.1-SNAPSHOT &
docker build ./apollo-server -t gorgeous-sandwich.gateway:0.0.1-SNAPSHOT &
```
The name chosen for the tags was "gorgeous-sandwich." plus the name of the microservice. The objective
of said name convention is to visually associate the microservices as a "family"
of services.

## Compose File

```yml
version: "2"
services:
  shopmicroservice:
    container_name: shop_ms
    image: gorgeous-sandwich.shop:0.0.1-SNAPSHOT
    restart: on-failure
    ports:
      - "5000:8080"
    depends_on:
      - shopdb
    environment:
      - spring.datasource.url=jdbc:mysql://shopdb:3306/shops?autoreconnect=true&allowPublicKeyRetrieval=true&useSSL=false
      - spring.datasource.username=admin
      - spring.datasource.password=@Dmin123
      - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
      - logging.level.org.springframework.web=INFO

  shopdb:
    image: mysql
    container_name: shopdb
    environment:
      - MYSQL_USER=admin
      - MYSQL_ALLOW_EMPTY_PASSWORD=true
      - MYSQL_PASSWORD=@Dmin123
      - MYSQL_DATABASE=shops
    command: mysqld --lower_case_table_names=1 --skip-ssl --character_set_server=utf8
    restart: on-failure
    ports:
      - "5001:3360"



  promotionmicroservice:
    container_name: promotion_ms
    image: gorgeous-sandwich.promotion:0.0.1-SNAPSHOT
    depends_on:
      - promotiondb
    environment:
      - SPRING.DATASOURCE.URL=jdbc:mysql://promotiondb:3306/promotions?autoreconnect=true&allowPublicKeyRetrieval=true&useSSL=false
      - SPRING.DATASOURCE.USERNAME=admin
      - SPRING.DATASOURCE.PASSWORD=@Dmin123
      - SPRING.JPA.PROPERTIES.HIBERNATE.DIALECT=org.hibernate.dialect.MySQL8Dialect
    ports:
      - "5003:8080"
    restart: on-failure

  promotiondb:
    image: mysql
    container_name: promotiondb
    environment:
      - MYSQL_USER=admin
      - MYSQL_ALLOW_EMPTY_PASSWORD=true
      - MYSQL_PASSWORD=@Dmin123
      - MYSQL_DATABASE=promotions
    command: mysqld --lower_case_table_names=1 --skip-ssl --character_set_server=utf8
    restart: on-failure
    ports:
      - "5004:3360"

  usermicroservice:
    container_name: user_ms
    image: gorgeous-sandwich.user:0.0.1-SNAPSHOT
    environment:
      - SPRING.DATASOURCE.URL=jdbc:mysql://userdb:3306/users?autoreconnect=true&allowPublicKeyRetrieval=true&useSSL=false
      - SPRING.DATASOURCE.USERNAME=admin
      - SPRING.DATASOURCE.PASSWORD=@Dmin123
      - SPRING.JPA.PROPERTIES.HIBERNATE.DIALECT=org.hibernate.dialect.MySQL8Dialect
    ports:
      - "5005:3360"
    depends_on:
      - userdb
    restart: on-failure
  userdb:
    image: mysql
    container_name: userdb
    environment:
      - MYSQL_USER=admin
      - MYSQL_ALLOW_EMPTY_PASSWORD=true
      - MYSQL_PASSWORD=@Dmin123
      - MYSQL_DATABASE=users
    command: mysqld --lower_case_table_names=1 --skip-ssl --character_set_server=utf8
    restart: on-failure
    ports:
      - "5006:3360"

  sandwichmicroservice:
    image: gorgeous-sandwich.sandwich:0.0.1-SNAPSHOT
    container_name: sandwich_ms
    depends_on:
      - sandwichdb
    ports:
      - "5007:80"
  sandwichdb:
    image: "mcr.microsoft.com/mssql/server:2022-latest"
    container_name: sandwichdb
    environment:
      ACCEPT_EULA: "Y"
      SA_PASSWORD: "pa55w0rd!"
      MSSQL_PID: "Express"
    ports:
      - "5008:1433"

  ordermicroservice:
    image: gorgeous-sandwich.order:0.0.1-SNAPSHOT
    container_name: order_ms
    depends_on:
      - orderdb
    ports:
      - "5008:8080"
    restart: on-failure
    environment:
      - SPRING.DATASOURCE.URL=jdbc:mysql://orderdb:3306/orders?autoreconnect=true&allowPublicKeyRetrieval=true&useSSL=false
      - SPRING.DATASOURCE.USERNAME=admin
      - SPRING.DATASOURCE.PASSWORD=@Dmin123
      - SPRING.JPA.PROPERTIES.HIBERNATE.DIALECT=org.hibernate.dialect.MySQL8Dialect

  orderdb:
    container_name: orderdb
    image: mysql
    command: mysqld --lower_case_table_names=1 --skip-ssl --character_set_server=utf8
    restart: on-failure
    ports:
      - "5009:3360"
    environment:
      - MYSQL_USER=admin
      - MYSQL_ALLOW_EMPTY_PASSWORD=true
      - MYSQL_PASSWORD=@Dmin123
      - MYSQL_DATABASE=orders



  gateway:
    image: gorgeous-sandwich.gateway:0.0.1-SNAPSHOT
    container_name: gateway
    depends_on:
      - sandwichmicroservice
      - promotionmicroservice
      - shopmicroservice
      - usermicroservice
    ports:
      - "5010:4000"

```

The strategy used in this file is to make use of the default network that is created when a dicker compose
file is created and group containers into a network. Furthermore, each microservice depends on its
assigned database, which is always built first due to such dependency. 
The gateway is the last service to be built due to its dependencies on all the microservices.

To run this docker compose file the following command must be executed:
```bash
docker compose -f Dockercompose.yml run gateway
```

