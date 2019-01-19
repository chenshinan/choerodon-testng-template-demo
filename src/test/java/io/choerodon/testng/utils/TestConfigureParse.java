package io.choerodon.testng.utils;

import io.choerodon.testng.config.domain.TestConfigure;
import org.testng.Reporter;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;

/**
 * @author dinghuang123@gmail.com
 * @since 2019/1/17
 */
public class TestConfigureParse {

    private static TestConfigure testConfigure = null;
    private static final String FILE_NAME = "configuration.yaml";

    private TestConfigureParse() {
    }

    public static TestConfigure getConfigure() {
        synchronized (TestConfigure.class) {
            if (testConfigure != null) {
                return testConfigure;
            } else {
                Yaml yaml = new Yaml();
                URL url = TestConfigureParse.class.getClassLoader().getResource(FILE_NAME);
                if (url == null) {
                    throw new IllegalArgumentException("The configuration file configure.yaml could not be found");
                } else {
                    try {
                        String filePath = url.getPath();
                        File file = new File(filePath);
                        if(file.exists()){
                            FileInputStream fileInputStream = new FileInputStream(file);
                            testConfigure = yaml.loadAs(fileInputStream, TestConfigure.class);
                        }else{
                            //jar包中读取
                            InputStream inputStream = TestConfigureParse.class.getClassLoader().getResourceAsStream(FILE_NAME);
                            testConfigure = yaml.loadAs(inputStream, TestConfigure.class);
                        }
                    } catch (FileNotFoundException e) {
                        Reporter.log("The configuration file configure.yaml could not be found" + e, true);
                    }
                }
                return testConfigure;
            }
        }

    }
}
