package com.example.springshelldemo.core.model;

public abstract class ElementTestStep extends TestStep {
    String selector;

    public ElementTestStep(String name, String selector, long delayPeriod) {
        super(name, delayPeriod);
        this.selector = selector;
    }

}
