package com.skp.casntemplt.demo;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber", "junit:target/junit-report.xml" },features = "src/test/resources/features/employeeMstest.feature")
public class ProjectCasndrTmpltApplicationTests {

	/*
	 * @Test public void contextLoads() { }
	 */

}
