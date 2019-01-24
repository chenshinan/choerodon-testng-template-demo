package io.choerodon.testng.config;

import io.choerodon.testng.config.utils.LoginUtil;
import org.testng.annotations.BeforeClass;

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
