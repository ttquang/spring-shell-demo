package com.example.springshelldemo.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestSuite extends TestElement {
    List<TestCase> testCases = new ArrayList<>();

    ExecutionEnvironment environment;

    public TestSuite(String name) {
        super(name);
        this.constructPropertyHandler(PropertyLevel.TEST_SUITE, new HashMap<>());
    }

    public void addTestCase(TestCase testCase) {
        testCase.propertyHandler.nextHandler = this.propertyHandler;
        testCase.testSuite = this;
        this.testCases.add(testCase);
    }

    public void runWith(ExecutionEnvironment environment) {
        this.environment = environment;
        for (TestCase testCase : testCases) {
            testCase.run();
            System.out.println("TestCase[" + testCase.name + "]:PASSED");
        }
        System.out.println("TestSuite[" + name + "]:PASSED");
    }

    public void run() {
        throw new UnsupportedOperationException();
    }

    public void delegate(TestStep testStep) {
        this.environment.delegate(testStep);
    }

    @Override
    public String toString() {
        return "TestSuite[" + name + "]";
    }
}
