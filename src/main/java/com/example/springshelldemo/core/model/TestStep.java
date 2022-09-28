package com.example.springshelldemo.core.model;

import java.util.HashMap;

public abstract class TestStep extends TestElement {
    TestCase testCase;

    long delayPeriod;

    public TestStep(String name, long delayPeriod) {
        super(name);
        this.delayPeriod = delayPeriod;
        this.constructPropertyHandler(PropertyLevel.TEST_STEP, new HashMap<>());
    }

    public void run() {
        testCase.delegate(this);
    }

    @Override
    public String toString() {
        return "TestStep[" + name + "]," +  testCase.toString();
    }
}
