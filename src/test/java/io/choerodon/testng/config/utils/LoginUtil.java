package io.choerodon.testng.config.utils;

import com.alibaba.fastjson.JSONObject;
import io.choerodon.testng.config.domain.TestConfigure;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author shinan.chen
 * @author dinghuang123@gmail.com
 * @since 2019/1/19
 */
public class LoginUtil {

    private static final String LOGIN_KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    private static final String LOGIN_PATH = "/oauth/login";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String SESSION = "SESSION";
    private static final String COOKIE = "Cookie";
    private static final String RESPONSE_TYPE = "response_type";
    private static final String CLIENT_ID = "client_id";
    private static final String STATE = "state";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String AUTHORIZE_PATH = "/oauth/oauth/authorize";
    private static final String TOKEN = "token";
    private static final String CLIENT = "client";
    private static final String LOCATION = "Location";
    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_TYPE = "&token_type=";
    private static final String ACCESS_TOKEN = "access_token=";
    private static final String STATES = "&state=";

    /**
     * Api测试授权认证，将获取到的token设置到全局请求中
     */
    public static void login() {
        System.out.println("Parse profile.....");
        TestConfigure testConfigure = TestConfigureParse.getConfigure();
        System.out.println(testConfigure.toString());
        Assert.assertNotEquals(testConfigure.getApiGateway(), null, "The apiGateWay cannot be empty");
        RestAssured.baseURI = testConfigure.getApiGateway();
        try {
            System.out.println("Start login.....");
            Response loginResponse = given()
                    .formParam(USER_NAME, testConfigure.getUsername())
                    .formParam(PASSWORD, encodePassword(testConfigure.getPassword()))
                    .expect().statusCode(302).when()
                    .post(LOGIN_PATH).then().extract().response();
            final String cookie = loginResponse.getCookie(SESSION);
            System.out.println("Get cookie successfully:" + cookie);
            Response tokenResponse = given()
                    .header(COOKIE, SESSION + "=" + cookie)
                    .param(RESPONSE_TYPE, TOKEN)
                    .param(CLIENT_ID, CLIENT)
                    .param(STATE, "").redirects().follow(true)
                    .param(REDIRECT_URI, "http://choerodon.io")
                    .redirects().follow(false)
                    .expect().statusCode(302).when()
                    .get(AUTHORIZE_PATH).then().extract().response();
            final String token = tokenResponse.getHeader(LOCATION).split(TOKEN_TYPE)[1].split(STATES)[0] + " " +
                    tokenResponse.getHeader(LOCATION).split(ACCESS_TOKEN)[1].split(TOKEN_TYPE)[0];
            System.out.println("Token token success：" + token);
            Filter filter = new Filter() {
                public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
                    requestSpec.header(AUTHORIZATION, token);
                    //将输入参数加入到报告中
                    Map<String,Object> requestData =  new HashMap<String, Object>();
                    requestData.put("method",requestSpec.getMethod());
                    requestData.put("queryParams",requestSpec.getQueryParams());
                    requestData.put("pathParams",requestSpec.getPathParams());
                    requestData.put("uri",requestSpec.getURI());
                    requestData.put("body",requestSpec.getBody());
                    ReporterUtil.inputData(JSONObject.toJSONString(requestData));
                    return ctx.next(requestSpec, responseSpec);
                }
            };
            //token设置到全局请求中
            RestAssured.filters(filter);
            System.out.println("Login authentication successful");
        } catch (Exception e) {
            System.out.println("Login authentication failed");
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 密码发送前校验
     *
     * @param password
     * @return
     */
    private static String encodePassword(String password) {
        Assert.assertNotEquals(password, "", "The password cannot be empty");
        Assert.assertNotEquals(password, null, "The password cannot be empty");
        StringBuilder output = new StringBuilder();
        char chr1, chr2, chr3;
        int enc1, enc2, enc3, enc4;
        for (int i = 0; i < password.length(); ) {
            chr1 = password.charAt(i++);
            chr2 = i < password.length() ? password.charAt(i++) : null;
            chr3 = i < password.length() ? password.charAt(i++) : null;
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (String.valueOf(chr2).equals("")) {
                enc3 = enc4 = 64;
            } else if (String.valueOf(chr3).equals("")) {
                enc4 = 64;
            }
            output.append(LOGIN_KEY.charAt(enc1)).append(LOGIN_KEY.charAt(enc2)).append(LOGIN_KEY.charAt(enc3)).append(LOGIN_KEY.charAt(enc4));
        }
        return output.toString();
    }
}
