package com.example.springshelldemo.core.exception;


import com.example.springshelldemo.core.model.TestStep;

public class StepRuntimeException extends RuntimeException {

    public StepRuntimeException() {}

    public StepRuntimeException(TestStep testStep) {
        super(testStep.toString());
    }

}
