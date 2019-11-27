package com.bitly.functional;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "de.monochromata.cucumber.report.PrettyReports:target/cucumber" })
public class RunCucumberTest {
}
