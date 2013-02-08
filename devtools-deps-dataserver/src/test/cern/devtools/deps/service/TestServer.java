package cern.devtools.deps.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServer {
	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext("classpath:/config/ctx-test-service-oracledev.xml");

	}
}
