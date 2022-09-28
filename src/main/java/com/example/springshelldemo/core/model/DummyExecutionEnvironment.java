package com.example.springshelldemo.core.model;

import org.openqa.selenium.WebDriver;

public class DummyExecutionEnvironment extends ExecutionEnvironment{

    public DummyExecutionEnvironment(WebDriver webDriver) {
        super(webDriver);
    }

    public void delegate(TestStep testStep) {
        System.out.println(testStep);
    }

}
