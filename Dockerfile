FROM --platform=$BUILDPLATFORM ubuntu:24.10 AS sodium-builder

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

FROM --platform=$BUILDPLATFORM node:20.18.1 AS vue-builder

WORKDIR /app

ADD ./admin-ui /app/admin-ui

RUN cd admin-ui \
    && npm install \
    && npm run build \
    && tar -czvf /app/dist.tar.gz -C dist/ .


FROM --platform=$BUILDPLATFORM maven:3.9.9-eclipse-temurin-17 AS java-builder

WORKDIR /app

ADD ./admin-server /app/admin-server

COPY --from=vue-builder /app/dist.tar.gz /app

RUN  mkdir -p /app/admin-server/src/main/resources/static \
     && tar -C /app/admin-server/src/main/resources/static -xzvf /app/dist.tar.gz \
     && cd admin-server \
     && ls -al /app/admin-server/src/main/resources/static \
     && mvn clean package -DskipTests=true \
     && app_file="$(mvn help:evaluate -Dexpression="project.build.finalName" -q -DforceStdout).jar" \
     && cp target/$app_file notesnook-sync-server-admin.jar

FROM --platform=$BUILDPLATFORM eclipse-temurin:17-jdk

LABEL authors="dyw770"
LABEL org.opencontainers.image.title="Notesnook Sync Server Admin"
LABEL org.opencontainers.image.source=https://github.com/dyw770/notesnook-sync-server-admin
LABEL org.opencontainers.image.url=https://github.com/dyw770/notesnook-sync-server-admin
LABEL org.opencontainers.image.description="Notesnook Sync Server Admin"


ENV ADMIN_USERNAME=admin
ENV ADMIN_PASSWORD=admin

WORKDIR /app

COPY --from=sodium-builder /usr/local/lib/libsodium.so /usr/local/lib/

COPY --from=java-builder /app/admin-server/notesnook-sync-server-admin.jar \
    /app/notesnook-sync-server-admin.jar

ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "notesnook-sync-server-admin.jar", "--spring.profiles.active=prod"]