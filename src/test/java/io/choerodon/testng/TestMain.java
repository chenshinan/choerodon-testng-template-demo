package io.choerodon.testng;

import org.testng.TestNG;

/**
 * @author shinan.chen
 * @since 2019/1/16
 */
public class TestMain {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setXmlPathInJar("/testng.xml1111");
        testNG.run();
    }
}
