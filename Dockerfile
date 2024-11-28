FROM ubuntu:latest as Builder
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

ENTRYPOINT ["top", "-b"]