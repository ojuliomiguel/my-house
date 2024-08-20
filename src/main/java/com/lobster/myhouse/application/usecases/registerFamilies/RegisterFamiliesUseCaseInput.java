package com.lobster.myhouse.application.usecases.registerFamilies;

import java.util.List;

public record RegisterFamiliesUseCaseInput(
        double totalIncome,
        List<RegisterFamiliesUseCaseInput.Dependent> dependents) {
    public record Dependent(
            String name,
            int age) {
    }
}