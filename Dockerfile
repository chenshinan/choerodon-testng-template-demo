FROM registry.cn-hangzhou.aliyuncs.com/choerodon-tools/javabase:0.5.0
COPY app-tests.jar run.sh /app/
WORKDIR /app
RUN chmod +x run.sh
ENTRYPOINT ["./run.sh"]
