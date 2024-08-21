package com.lobster.myhouse.domain.Scoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lobster.myhouse.domain.Dependent;

public class MyPopularHouseScoringTest {

    private MyPopularHouseScoring scoring;

    @BeforeEach
    public void setUp() {
        scoring = new MyPopularHouseScoring();
    }

    @Test
    public void testCalculate_ScoreWithIncomeLessThanOrEqual900AndNoDependents() {
        double totalIncome = 900;
        List<Dependent> dependents = Collections.emptyList();

        double result = scoring.caculate(totalIncome, dependents);

        assertEquals(5.0, result);
    }

    @Test
    public void testCalculate_ScoreWithIncomeBetween901And1500AndNoDependents() {
        double totalIncome = 1000;
        List<Dependent> dependents = Collections.emptyList();

        double result = scoring.caculate(totalIncome, dependents);

        assertEquals(3, result);
    }

    @Test
    public void testCalculate_ScoreWithIncomeLessThanOrEqual900AndThreeDependents() {
        double totalIncome = 900;
        List<Dependent> dependents = Arrays.asList(
                new Dependent("Dependent1", 10),
                new Dependent("Dependent2", 12),
                new Dependent("Dependent3", 15));

        double result = scoring.caculate(totalIncome, dependents);

        assertEquals(8, result);
    }

    @Test
    public void testCalculate_ScoreWithIncomeBetween901And1500AndThreeDependents() {
        double totalIncome = 1200;
        List<Dependent> dependents = Arrays.asList(
                new Dependent("Dependent1", 10),
                new Dependent("Dependent2", 12),
                new Dependent("Dependent3", 15));

        double result = scoring.caculate(totalIncome, dependents);

        assertEquals(6, result);
    }

    @Test
    public void testCalculate_ScoreWithIncomeMoreThan1500AndThreeDependents() {
        double totalIncome = 1600;
        List<Dependent> dependents = Arrays.asList(
                new Dependent("Dependent1", 10),
                new Dependent("Dependent2", 12),
                new Dependent("Dependent3", 15));

        double result = scoring.caculate(totalIncome, dependents);

        assertEquals(0, result);
    }

    @Test
    public void testCalculate_ScoreWithIncomeLessThanOrEqual900AndTwoDependents() {
        double totalIncome = 900;
        List<Dependent> dependents = Arrays.asList(
                new Dependent("Dependent1", 10),
                new Dependent("Dependent2", 12));

        double result = scoring.caculate(totalIncome, dependents);

        assertEquals(7, result);
    }

    @Test
    public void testCalculate_ScoreWithIncomeBetween901And1500AndOneDependent() {
        double totalIncome = 1400;
        List<Dependent> dependents = Arrays.asList(
                new Dependent("Dependent1", 10));

        double result = scoring.caculate(totalIncome, dependents);

        assertEquals(5, result);
    }

    @Test
    public void testCalculate_ScoreWithIncomeMoreThan1500AndNoDependents() {
        double totalIncome = 1600;
        List<Dependent> dependents = Collections.emptyList();

        double result = scoring.caculate(totalIncome, dependents);

        assertEquals(0, result);
    }

    @Test
    public void testCalculate_ScoreWithIncomeMoreThan1500AndOneDependent() {
        double totalIncome = 1600;
        List<Dependent> dependents = Arrays.asList(
                new Dependent("Dependent1", 10));

        double result = scoring.caculate(totalIncome, dependents);

        assertEquals(0, result);
    }
}
