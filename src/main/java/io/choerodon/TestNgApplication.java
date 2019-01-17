package io.choerodon;

import io.choerodon.testng.config.TestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Import(TestConfiguration.class)
public class TestNgApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestNgApplication.class, args);
		TestNG testng = new TestNG();
		List<String> suites = new ArrayList();
		suites.add("testng.xml");//path to xml..
		testng.setTestSuites(suites);
		testng.run();


		System.out.println("112233");
	}
}


