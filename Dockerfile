FROM ubuntu:24.10 AS sodium-builder

WORKDIR /app

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

FROM maven:3.9.9-eclipse-temurin-17 AS java-builder

WORKDIR /app/notesnook-sync-server-admin

COPY . /app/notesnook-sync-server-admin

RUN  mvn clean package -DskipTests=true \
     && app_file="$(mvn help:evaluate -Dexpression="project.build.finalName" -q -DforceStdout).jar" \
     && cp target/$app_file notesnook-sync-server-admin.jar

FROM eclipse-temurin:17-jdk

LABEL authors="dyw770"

ENV ADMIN_USERNAME=admin
ENV ADMIN_PASSWORD=admin

WORKDIR /app

COPY --from=sodium-builder /usr/local/lib/libsodium.so /usr/local/lib/

COPY --from=java-builder /app/notesnook-sync-server-admin/notesnook-sync-server-admin.jar \
    /app/notesnook-sync-server-admin.jar

ENTRYPOINT ["java", "-jar", "notesnook-sync-server-admin.jar", "--spring.profiles.active=prod"]