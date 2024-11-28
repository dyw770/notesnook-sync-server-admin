FROM ubuntu:latest as SodiumBuilder
LABEL authors="dyw770"

WORKDIR "/app"

RUN apt update \
    && apt-get install -y wget gcc make \
    && wget https://download.libsodium.org/libsodium/releases/libsodium-1.0.20-stable.tar.gz \
    && tar -xzf libsodium-1.0.20-stable.tar.gz \
    && cd libsodium-stable \
    && ./configure \
    && make \
    && make check \
    && make install \
    && cd .. \
    && rm -rf libsodium-stable \
    && rm -rf libsodium-1.0.20-stable.tar.gz

FROM 3.9.9-eclipse-temurin-17 as JavaBuilder

COPY --from=SodiumBuilder /usr/local/lib/libsodium.so /usr/local/lib/

WORKDIR "/app/notesnook-sync-server-admin"

COPY * ./

RUN mvn clean package -DskipTests=true

FROM eclipse-temurin:17-jdk

WORKDIR "/app"

COPY --from=JavaBuilder /app/notesnook-sync-server-admin/target/notesnook-sync-server-admin-1.0.0-SNAPSHOT.jar \
    /app/notesnook-sync-server-admin.jar

ENTRYPOINT ["top", "-b"]