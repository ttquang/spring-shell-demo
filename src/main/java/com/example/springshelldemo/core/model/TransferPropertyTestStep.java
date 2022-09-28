package com.example.springshelldemo.core.model;

public class TransferPropertyTestStep extends PropertyTestStep {
    String selector;

    public TransferPropertyTestStep(String name, String key, String selector, long delayPeriod) {
        super(name, key, delayPeriod);
        this.selector = selector;
    }

}
