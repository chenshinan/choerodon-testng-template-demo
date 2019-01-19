package io.choerodon.testng.config;

import io.choerodon.testng.utils.LoginUtil;
import io.choerodon.testng.utils.TestConfigureParse;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import io.choerodon.testng.config.domain.TestConfigure;

import static io.restassured.RestAssured.given;

/**
 * testng基础类
 *
 * @author dinghuang123@gmail.com
 * @since 2019/1/16
 */
public class TestBase {

    private static Boolean isLogin = false;

    @BeforeClass
    public void beforeClass() {
        if (!isLogin) {
            LoginUtil.login();
            isLogin = true;
        }
    }
}
