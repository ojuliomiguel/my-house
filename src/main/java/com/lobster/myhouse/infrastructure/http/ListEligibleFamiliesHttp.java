package com.lobster.myhouse.infrastructure.http;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lobster.myhouse.application.contracts.usecase.UseCaseContract;
import com.lobster.myhouse.application.usecases.listEligibleFamilies.EligibleFamiliesUseCaseInput;
import com.lobster.myhouse.application.usecases.listEligibleFamilies.EligibleFamiliesUseCaseOutput;

@RestController
@RequestMapping("/families/eligibles")
public class ListEligibleFamiliesHttp {

    private final UseCaseContract<EligibleFamiliesUseCaseInput, List<EligibleFamiliesUseCaseOutput>> listEligibleFamiliesUseCase;

    public ListEligibleFamiliesHttp(
            UseCaseContract<EligibleFamiliesUseCaseInput, List<EligibleFamiliesUseCaseOutput>> listEligibleFamiliesUseCase) {
        this.listEligibleFamiliesUseCase = listEligibleFamiliesUseCase;
    }

    @GetMapping
    public ResponseEntity<Object> execute(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
        EligibleFamiliesUseCaseInput input = new EligibleFamiliesUseCaseInput(pageSize, pageNumber);
        var result = this.listEligibleFamiliesUseCase.execute(input);
        return ResponseEntity.ok(result);
    }

}
