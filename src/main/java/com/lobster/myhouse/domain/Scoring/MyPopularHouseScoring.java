package com.lobster.myhouse.domain.Scoring;

import java.util.List;

import com.lobster.myhouse.domain.Dependent;

public class MyPopularHouseScoring implements Scoring {

    @Override
    public double caculate(double totalIncome, List<Dependent> dependents) {
        double score = 0;
        if (totalIncome <= 900) {
            score += 5;
        } else if (totalIncome <= 1500) {
            score += 3;
        }
        long eligibleDependents = dependents.stream()
                .filter(dependent -> dependent.getAge() < 18)
                .count();
        if (eligibleDependents >= 3) {
            score += 3;
        } else if (eligibleDependents >= 1) {
            score += 2;
        }
        return score;
    }

}
