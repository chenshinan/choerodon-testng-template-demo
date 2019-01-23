package io.choerodon.testng.test;

import io.choerodon.testng.config.TestBase;
import io.choerodon.testng.utils.ReporterUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

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
    private static final Long projectId = 340L;
    @Test(description = "创建issue")
    public void queryWiki() {
        //测试数据
        ReporterUtil.inputData("传入issueId=16389");
        //预期结果
        ReporterUtil.expectData("查询成功");
        //准备数据
        Map<String, Object> jsonAsMap = new HashMap<String, Object>();
        jsonAsMap.put("priorityCode", "priority-58");
        jsonAsMap.put("priorityId", "58");
        jsonAsMap.put("projectId", projectId);
        jsonAsMap.put("sprintId", 1022);
        jsonAsMap.put("summary", "丁煌测试2");
        jsonAsMap.put("issueTypeId", "116");
        jsonAsMap.put("typeCode", "story");
        jsonAsMap.put("parentIssueId", "0");
        //状态码验证
        given().log().all().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("applyType", "agile")
                .body("{\"priorityCode\":\"priority-58\",\"priorityId\":58,\"projectId\":\"340\",\"sprintId\":1022,\"summary\":\"丁煌测试2\"," +
                        "\"issueTypeId\":116,\"typeCode\":\"story\",\"parentIssueId\":0}")
                .post("/agile/v1/projects/" + projectId + "/issues").then().assertThat().statusCode(201)
                .time(lessThan(200L)).and().log().ifError().log().body().body("applyType", equalTo("agile"));
    }
}
