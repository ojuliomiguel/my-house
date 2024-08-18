package com.lobster.myhouse.application.contracts.usecase;

public interface UseCaseContract<IN, OUT> {
    OUT execute(IN input);
}
