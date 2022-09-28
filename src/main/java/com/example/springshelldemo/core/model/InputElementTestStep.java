package com.example.springshelldemo.core.model;

public class InputElementTestStep extends ElementTestStep {
    String value;

    public InputElementTestStep(String name, String selector, String value, long delayPeriod) {
        super(name, selector, delayPeriod);
        this.value = value;
    }

}
