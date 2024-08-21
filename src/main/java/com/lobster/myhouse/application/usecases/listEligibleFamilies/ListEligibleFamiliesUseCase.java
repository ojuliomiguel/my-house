package com.lobster.myhouse.application.usecases.listEligibleFamilies;

import java.util.List;
import java.util.stream.Collectors;
import com.lobster.myhouse.application.contracts.usecase.UseCaseContract;
import com.lobster.myhouse.domain.Family;
import com.lobster.myhouse.domain.gateway.ListFamiliesGateway;
import com.lobster.myhouse.domain.gateway.common.Filter;

public class ListEligibleFamiliesUseCase
        implements UseCaseContract<EligibleFamiliesUseCaseInput, List<EligibleFamiliesUseCaseOutput>> {

    private final ListFamiliesGateway listFamilies;

    public ListEligibleFamiliesUseCase(ListFamiliesGateway listFamilies) {
        this.listFamilies = listFamilies;
    }

    @Override
    public List<EligibleFamiliesUseCaseOutput> execute(EligibleFamiliesUseCaseInput input) {
        var filter = new Filter.Builder()
                .sortBy("score");
        if (input.pageNumber() != null) {
            filter.pageNumber(input.pageNumber());
        }
        if (input.pageSize() != null) {
            filter.pageSize(input.pageSize());
        }
        List<Family> families = listFamilies.execute(filter.build());
        return families.stream()
                .map(this::fromFamily)
                .collect(Collectors.toList());
    }

    private EligibleFamiliesUseCaseOutput fromFamily(Family family) {
        List<EligibleFamiliesUseCaseOutput.Dependent> dependentOutputs = family.getDependents().stream()
                .map(d -> new EligibleFamiliesUseCaseOutput.Dependent(d.getName(), d.getAge()))
                .collect(Collectors.toList());
        return new EligibleFamiliesUseCaseOutput(
                family.getId(),
                family.getTotalIncome(),
                family.getScore(),
                dependentOutputs);
    }

}
