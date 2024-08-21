package com.lobster.myhouse.domain.Scoring;

import java.util.List;

import com.lobster.myhouse.domain.Dependent;

public class MyPopularHouseScoring implements Scoring {

    private int MAX_INCOME = 1500;
    private int INCOME_UP_TO = 900;

    @Override
    public double caculate(double totalIncome, List<Dependent> dependents) {
        double score = 0;
        if (this.hasIncomeOutsideOfRange(totalIncome)) {
            return score;
        }
        if (totalIncome <= this.INCOME_UP_TO) {
            score += 5;
        } else if (totalIncome <= this.MAX_INCOME) {
            score += 3;
        }
        long familiesUpToThreeDependents = dependents.stream()
                .filter(dependent -> dependent.getAge() <= 18)
                .count();

        long familiesWithOneOrTwoDependents = dependents.stream()
                .filter(dependent -> dependent.getAge() < 18)
                .count();
        if (familiesUpToThreeDependents >= 3) {
            score += 3;
        } else if (familiesWithOneOrTwoDependents > 0 && familiesWithOneOrTwoDependents <= 2) {
            score += 2;
        }
        return score;
    }

    private boolean hasIncomeOutsideOfRange(double totalIncome) {
        return totalIncome > this.MAX_INCOME;
    }

}
