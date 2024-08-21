package com.lobster.myhouse.application.usecases.listEligibleFamilies;

public record EligibleFamiliesUseCaseInput(
        Integer pageSize,
        Integer pageNumber) {
}