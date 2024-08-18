package com.lobster.myhouse.infrastructure.http;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterFamiliesDto {

    private double totalIncome;
    private List<Dependent> dependents;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dependent {
        private String name;
        private int age;
    }
}
