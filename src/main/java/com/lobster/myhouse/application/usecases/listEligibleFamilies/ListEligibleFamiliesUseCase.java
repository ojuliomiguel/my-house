package com.lobster.myhouse.application.usecases.listEligibleFamilies;

import java.util.List;

import com.lobster.myhouse.application.contracts.usecase.UseCaseContract;
import com.lobster.myhouse.domain.gateway.common.Filter;

public class ListEligibleFamiliesUseCase implements UseCaseContract<Filter, List<EligibleFamiliesUseCaseOutput>> {

    @Override
    public List<EligibleFamiliesUseCaseOutput> execute(Filter input) {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
