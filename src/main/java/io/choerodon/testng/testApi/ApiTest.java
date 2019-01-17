package io.choerodon.testng.testApi;

import io.choerodon.testng.config.ConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * @author shinan.chen
 * @since 2019/1/8
 */

public class ApiTest {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ConfigProperty configProperty;

    @Test(description = "登录后测试userSelf")
    public void userSelf() {
        System.out.println("world");
        Reporter.log("输入参数：" + 1);
        String url = configProperty.apiGateway + "/iam/v1/users/self";
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class);
        System.out.println(responseEntity.getBody());
    }
}
