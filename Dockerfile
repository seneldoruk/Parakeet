FROM eclipse-temurin:17
RUN apt-get update && apt-get -y upgrade
RUN apt-get install -y inotify-tools
ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME