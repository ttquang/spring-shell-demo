package com.example.springshelldemo.core.model;

import com.example.springshelldemo.core.exception.StepRuntimeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCase extends TestElement {
    TestSuite testSuite;
    List<TestStep> testSteps = new ArrayList<>();

    public TestCase(String name) {
        super(name);
        this.constructPropertyHandler(PropertyLevel.TEST_CASE, new HashMap<>());
    }

    public void addTestStep(TestStep testStep) {
        testStep.propertyHandler.nextHandler = this.propertyHandler;
        testStep.testCase = this;
        this.testSteps.add(testStep);
    }

    public void run() {
        for (TestStep testStep : testSteps) {
            try {
                testStep.run();
            } catch (StepRuntimeException ex) {
                ex.printStackTrace();
                throw new StepRuntimeException(testStep);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new StepRuntimeException(testStep);
            } finally {
//                System.out.println(propertyHandler);
            }
        }
    }

    public void delegate(TestStep testStep) {
        testSuite.delegate(testStep);
    }

    @Override
    public String toString() {
        return "TestCase[" + name + "]," +  testSuite.toString();
    }
}
