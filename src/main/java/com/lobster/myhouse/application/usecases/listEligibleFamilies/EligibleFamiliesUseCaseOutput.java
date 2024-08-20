package com.lobster.myhouse.application.usecases.listEligibleFamilies;

import java.util.List;

public record EligibleFamiliesUseCaseOutput(
        String id,
        double totalIncome,
        double totalScore,
        List<Dependent> dependents) {

    public record Dependent(
            String name,
            int age) {
    }
}
