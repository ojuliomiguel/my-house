package com.lobster.myhouse.domain.Scoring;

import java.util.List;

import com.lobster.myhouse.domain.Dependent;

public interface Scoring {

    public double caculate(double totalIncome, List<Dependent> dependents);
}