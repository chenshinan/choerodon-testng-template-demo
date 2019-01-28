package io.choerodon.testng.demo.assured;

import io.choerodon.testng.config.TestBase;
import io.choerodon.testng.config.domain.TestConfigure;
import io.choerodon.testng.config.utils.TestConfigureParse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


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
    private TestConfigure testConfigure = TestConfigureParse.getConfigure();
    private final Long projectId = Long.valueOf(testConfigure.getProjectId());

    @Test(description = "创建issue")
    public void createIssue() {
        //准备数据
//        Map<String, Object> jsonAsMap = new HashMap<String, Object>();
//        jsonAsMap.put("priorityCode", "priority-58");
//        jsonAsMap.put("priorityId", "58");
//        jsonAsMap.put("projectId", projectId);
//        jsonAsMap.put("sprintId", 1033);
//        jsonAsMap.put("summary", "dinghuangTest");
//        jsonAsMap.put("issueTypeId", "116");
//        jsonAsMap.put("typeCode", "story");
//        jsonAsMap.put("parentIssueId", "0");
        //状态码验证
        Response response = given()
                //打印请求的所有数据
                .log().all()
                //请求头，也可以用.header("","")
                .accept(ContentType.JSON).contentType(ContentType.JSON)
                //请求参数
                .queryParam("applyType", "agile")
                //请求体，可以直接放json字符串，也可以.body(jsonAsMap)
                .body("{\"priorityCode\":\"priority-58\",\"priorityId\":58,\"projectId\":\"" + projectId + "\",\"sprintId\":1033,\"summary\":\"丁煌测试2\",\"issueTypeId\":116,\"typeCode\":\"story\",\"parentIssueId\":0}")
                //【注意】在发请求之前去判断期望值，这种方式可以写入测试用例的预期结果中，推荐这种写法
                .expect().statusCode(201)
                //请求时间断言，>2080L失败
                .time(lessThan(2080L))
                //响应体中json字符串断言，查找返回json中的applyType对应的值是否等于agile
                .and().body("applyType", equalTo("agile"))
                //支持路径匹配，高级用法例如：断言价格低于10的书籍的标题是“世纪的谚语”和“白鲸记”
//                .body("store.book.findAll { it.price < 10 }.title", hasItems("Sayings of the Century", "Moby Dick"))
                //断言所有作者名称的长度之和大于50
                //.body("store.book.author.collect { it.length() }.sum()", greaterThan(50))
                .and().body("activeSprint.sprintId", equalTo(null))
                .when()
                //请求路径
                .post("/agile/v1/projects/" + projectId + "/issues").then()
                //可选选项，如果不获取请求体就不用导出请求体
                .extract().response();
        //获取创建成功的问题id
        Long issueId = Long.parseLong(response.path("issueId").toString());
        //获取所有书中价格小于10的标题作为列表结果
//        List<String> bookTitles = from(response.asString()).getList("store.book.findAll { it.price < 10 }.title");
        //将结果与对象自动映射
        //List<Map<String, Object>> products = get("/products").as(new TypeRef<List<Map<String, Object>>>() {});
        //通过对象断言
//        assertThat(products, hasSize(2));
//        assertThat(products.get(0).get("id"), equalTo(2));
//        assertThat(products.get(0).get("name"), equalTo("An ice sculpture"));
    }
}
