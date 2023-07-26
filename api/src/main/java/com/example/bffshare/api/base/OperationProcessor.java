package com.example.bffshare.api.base;

public interface OperationProcessor <I extends OperationInput, T extends OperationResult >{
    T process(I operationInput) throws Exception;
}
