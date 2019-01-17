package io.choerodon;

import io.choerodon.testng.config.TestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.testng.TestNG;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Import(TestConfiguration.class)
public class TestNgApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestNgApplication.class, args);
		TestNG testng = new TestNG();
		List<String> suites = new ArrayList();
		//path to xml..
		suites.add("testng/testng.xml");
		testng.setTestSuites(suites);
		testng.run();
	}
}


