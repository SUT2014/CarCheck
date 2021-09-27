/*
 *
 *  * Copyright (c) 2021.  Kumaran Devaneson
 *  * All rights reserved
 *
 */

package com.github.SUT2014.CarCheck.Test.Cucumber.TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",
        glue={"com.github.SUT2014.CarCheck.Test.StepDefinition"},
        plugin = {"pretty","html:target/reports/cucumber.html"}
)
public class RunCukesTest {

}

