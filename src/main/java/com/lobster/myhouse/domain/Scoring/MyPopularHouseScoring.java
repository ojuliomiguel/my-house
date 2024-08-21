package com.lobster.myhouse.domain.Scoring;

import java.util.List;

import com.lobster.myhouse.domain.Dependent;

public class MyPopularHouseScoring implements Scoring {

    private int MAX_INCOME = 1500;
    private int INCOME_UP_TO = 900;

    private double HIGH_INCOME_SCORE = 5;
    private double MEDIUM_INCOME_SCORE = 3;

    private int MAX_DEPENDENT_AGE = 18;

    @Override
    public double caculate(double totalIncome, List<Dependent> dependents) {
        if (this.hasIncomeOutsideOfRange(totalIncome)) {
            return 0;
        }
        double score = calculateIncomeScore(totalIncome);
        double familyBonusScore = calculateFamilyBonusScore(dependents);
        return score + familyBonusScore;
    }

    private boolean hasIncomeOutsideOfRange(double totalIncome) {
        return totalIncome > MAX_INCOME;
    }

    private double calculateIncomeScore(double totalIncome) {
        if (totalIncome <= INCOME_UP_TO) {
            return HIGH_INCOME_SCORE;
        } else if (totalIncome <= MAX_INCOME) {
            return MEDIUM_INCOME_SCORE;
        }
        return 0;
    }

    private double calculateFamilyBonusScore(List<Dependent> dependents) {
        int MIN_DEPENDENTS_FOR_MAX_BONUS = 3;
        int MIN_DEPENDENTS_FOR_MIN_BONUS = 2;
        int MAX_FAMILY_BONUS = 3;
        int MIN_FAMILY_BONUS = 2;
        int NO_FAMILY_BONUS = 0;
        long numFamiliesWithUpToThreeDependents = dependents.stream()
                .filter(dependent -> dependent.getAge() <= MAX_DEPENDENT_AGE)
                .count();
        long numFamiliesWithOneOrTwoDependents = dependents.stream()
                .filter(dependent -> dependent.getAge() < MAX_DEPENDENT_AGE)
                .count();
        if (numFamiliesWithUpToThreeDependents >= MIN_DEPENDENTS_FOR_MAX_BONUS) {
            return MAX_FAMILY_BONUS;
        } else if (numFamiliesWithOneOrTwoDependents > 0
                && numFamiliesWithOneOrTwoDependents <= MIN_DEPENDENTS_FOR_MIN_BONUS) {
            return MIN_FAMILY_BONUS;
        }
        return NO_FAMILY_BONUS;
    }

}
