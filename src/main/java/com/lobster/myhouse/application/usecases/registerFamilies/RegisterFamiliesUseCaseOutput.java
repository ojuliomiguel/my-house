package com.lobster.myhouse.application.usecases.registerFamilies;

import java.util.List;

public record RegisterFamiliesUseCaseOutput(
        String id,
        double totalIncome,
        double totalScore,
        List<Dependent> dependents,
        String status) {

    public record Dependent(
            String name,
            int age) {
    }
}
