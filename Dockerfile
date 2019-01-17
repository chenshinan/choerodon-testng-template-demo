FROM maven:3.6.0-jdk-8-slim
COPY . /app
WORKDIR /app
RUN chmod +x run.sh
ENTRYPOINT ["./run.sh"]
