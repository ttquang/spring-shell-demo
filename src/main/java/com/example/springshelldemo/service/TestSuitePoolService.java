package com.example.springshelldemo.service;

import com.example.springshelldemo.core.model.TestSuite;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TestSuitePoolService {
    Logger log = Logger.getLogger(TestSuitePoolService.class.getName());

    private List<TestSuite> testSuitePool = new ArrayList<>();

    public void addTestSuite(TestSuite testSuite) {
        testSuitePool.add(testSuite);
    }

    public TestSuite get(int index) {
        return testSuitePool.get(index);
    }

    public List<TestSuite> getAll() {
        return testSuitePool;
    }
}
