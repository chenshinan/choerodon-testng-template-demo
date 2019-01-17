FROM registry.cn-hangzhou.aliyuncs.com/choerodon-tools/javabase:0.5.0
COPY . /app
WORKDIR /app
RUN chmod +x run.sh
ENTRYPOINT ["./run.sh"]
