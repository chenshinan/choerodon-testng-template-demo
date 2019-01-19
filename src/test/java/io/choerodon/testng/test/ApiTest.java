package io.choerodon.testng.test;

import io.choerodon.testng.config.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

/**
 * api测试采用了rest-assured框架
 *
 * @author dinghuang123@gmail.com
 * @see <a href="https://testerhome.com/topics/7060</a> 具体用法
 * @since 2019/1/17
 */
@Test
public class ApiTest extends TestBase {

    @BeforeClass
    public void setUp() {
    }

    @Test(description = "查询Wiki")
    public void queryWiki() {
        //状态码验证
        Response response = given().accept(ContentType.JSON).
                contentType(ContentType.JSON).
                get("/agile/v1/projects/340/wiki_relation/issue/16389").then().extract().response();
        if (response.getStatusCode() == 201) {
            System.out.println("访问成功");
        }
        Reporter.log(response.asString());
        //验证
        response.then().body("wikiHost", equalTo("http://xwiki.staging.saas.hand-china.com"));
        //格式化打印JSON数据
        response.getBody().prettyPrint();
        //判断响应时间是否少于预期值。
        response.then().time(lessThan(2000L), SECONDS);

    }

    @Test(description = "查询Wiki")
    public void queryOther() {
        //状态码验证
        Response response = given().accept(ContentType.JSON).
                contentType(ContentType.JSON).
                get("/iam/v1/users/self").then().extract().response();
        if (response.getStatusCode() == 200) {
            System.out.println("访问成功");
        }
    }
}
