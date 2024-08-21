package com.lobster.myhouse.domain;

import java.util.List;

import com.lobster.myhouse.domain.Scoring.Scoring;

public class Family {
    private String id;
    private double totalIncome;
    private List<Dependent> dependents;
    private double score;

    public double getScore() {
        return score;
    }

    private Family(String id, double totalIncome, List<Dependent> dependents, Scoring scoring) {
        this.id = id;
        this.totalIncome = totalIncome;
        this.dependents = dependents;
        this.score = scoring.caculate(totalIncome, dependents);
    }

    public static Family create(double totalIncome, List<Dependent> dependents, Scoring scoring) {
        return new Family(null, totalIncome, dependents, scoring);
    }

    public static Family restore(String id, double totalIncome, List<Dependent> dependents, Scoring scoring) {
        return new Family(id, totalIncome, dependents, scoring);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }
}
