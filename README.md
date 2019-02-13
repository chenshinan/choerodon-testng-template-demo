# choerodon-testng-template

This is a choerodon testng template，for api test

## TestNg Configuration

* src/test/resources/testng.xml: TestNg main configuration
* src/test/resources/suite: TestNg more suite source

## Project/Environment Configuration

* resultGateway: TestNg result xml return save location gateway
* apiGateWay: TestNg test execute location gateway
* projectId: TestNg test execute projectId
* loginName: TestNg test execute loginName
* password: TestNg test execute password

## Programming advice

This template use rest-assured framework for api test，before request send to set expect，then result will catch 'expect value', it also can catch request params as 'test value'.
```$xslt
Response response = given()
       ...
       .expect().statusCode(201)//期望预期结果201
       ...
       .post(url)//发起请求
```
If you need custom 'expect value' and 'test value', you can set log before request, it will cover the default.
```$xslt
//测试数据
ReporterUtil.inputData("输入参数");
//预期结果
ReporterUtil.expectData("预期结果");
```