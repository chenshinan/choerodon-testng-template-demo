package io.choerodon.testng.test;

import io.choerodon.testng.config.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

/**
 * api测试采用了rest-assured框架
 *
 * @author dinghuang123@gmail.com
 * @see <a href="https://testerhome.com/topics/7060</a> 具体用法
 * @since 2019/1/17
 */
@Test
public class ApiTest2 extends TestBase {

    @Test(description = "登录后查询用户")
    public void querySelf() {
        //状态码验证
        Response response = given().accept(ContentType.JSON).
                contentType(ContentType.JSON).
                get("/iam/v1/users/self").then().extract().response();
        if (response.getStatusCode() == 200) {
            System.out.println("访问成功");
        }
        System.out.println(response.asString());
    }
}
