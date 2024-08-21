package com.lobster.myhouse.infrastructure.http;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<Object> execute(@RequestBody(required = false) EligibleFamiliesDTO request) {
        EligibleFamiliesDTO dto = request != null ? request : EligibleFamiliesDTO.builder().build();
        EligibleFamiliesUseCaseInput input = new EligibleFamiliesUseCaseInput(dto.getPageSize(), dto.getPageNumber());
        var result = this.listEligibleFamiliesUseCase.execute(input);
        return ResponseEntity.ok(result);
    }

}
