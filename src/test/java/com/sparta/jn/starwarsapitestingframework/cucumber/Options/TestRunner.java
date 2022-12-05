package com.sparta.jn.starwarsapitestingframework.cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/sparta/jn/starwarsapitestingframework/features",
        plugin = "json:target/jsonReports/cucumber-report.json",
        glue = {"com/sparta/jn/starwarsapitestingframework/stepDefinitions"})

public class TestRunner {
}
