package io.choerodon.testng.demo.assured;

import io.choerodon.testng.config.TestBase;
import io.choerodon.testng.config.domain.TestConfigure;
import io.choerodon.testng.config.utils.ReporterUtil;
import io.choerodon.testng.config.utils.TestConfigureParse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * api测试采用了rest-assured框架
 *
 * @author shinan.chen
 * @see <a href="https://testerhome.com/topics/7060</a> 具体用法
 * @since 2019/1/17
 */
@Test
public class ApiTest2 extends TestBase {
    private TestConfigure testConfigure = TestConfigureParse.getConfigure();

    @Test(description = "登录后查询用户")
    public void querySelf() {
        //测试数据
        ReporterUtil.inputData("User: " + testConfigure.getUsername() + ", Password: " + testConfigure.getPassword());
        //预期结果
        ReporterUtil.expectData("用户登录成功");
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
