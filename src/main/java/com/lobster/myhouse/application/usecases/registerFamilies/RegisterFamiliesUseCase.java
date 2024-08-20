package com.lobster.myhouse.application.usecases.registerFamilies;

import java.util.List;
import java.util.stream.Collectors;

import com.lobster.myhouse.application.contracts.usecase.UseCaseContract;
import com.lobster.myhouse.domain.Dependent;
import com.lobster.myhouse.domain.Family;
import com.lobster.myhouse.domain.Scoring.Scoring;
import com.lobster.myhouse.domain.gateway.RegisterFamiliesGateway;


public class RegisterFamiliesUseCase
                implements UseCaseContract<List<RegisterFamiliesUseCaseInput>, List<RegisterFamiliesUseCaseOutput>> {

        private RegisterFamiliesGateway registerFamilies;
        private Scoring scoring;

        public RegisterFamiliesUseCase(RegisterFamiliesGateway registerFamilies, Scoring scoring) {
                this.scoring = scoring;
                this.registerFamilies = registerFamilies;
        }

        @Override
        public List<RegisterFamiliesUseCaseOutput> execute(List<RegisterFamiliesUseCaseInput> input) {
                List<Family> families = input.stream()
                                .map(this::createFamily)
                                .collect(Collectors.toList());
                registerFamilies.execute(families);
                return families.stream()
                                .map(f -> new RegisterFamiliesUseCaseOutput(
                                                f.getId(),
                                                f.getTotalIncome(),
                                                f.getScore(),
                                                f.getDependents().stream()
                                                                .map(d -> new RegisterFamiliesUseCaseOutput.Dependent(
                                                                                d.getName(),
                                                                                d.getAge()))
                                                                .collect(Collectors.toList()),
                                                "REGISTERED"))
                                .collect(Collectors.toList());
        }

        private Family createFamily(RegisterFamiliesUseCaseInput input) {
                List<Dependent> domainDependents = input.dependents().stream()
                                .map(d -> new Dependent(d.name(), d.age()))
                                .collect(Collectors.toList());
                return Family.create(input.totalIncome(), domainDependents, this.scoring);
        }

        public void setStrategy(Scoring scoring) {
                this.scoring = scoring;
        }
}
