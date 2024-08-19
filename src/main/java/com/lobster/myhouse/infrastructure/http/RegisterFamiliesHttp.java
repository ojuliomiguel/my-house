package com.lobster.myhouse.infrastructure.http;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lobster.myhouse.application.contracts.usecase.UseCaseContract;
import com.lobster.myhouse.application.usecases.RegisterFamiliesUseCaseInput;
import com.lobster.myhouse.application.usecases.RegisterFamiliesUseCaseOutput;

@RestController
@RequestMapping("/families")
public class RegisterFamiliesHttp {

    private final UseCaseContract<List<RegisterFamiliesUseCaseInput>, List<RegisterFamiliesUseCaseOutput>> registerFamiliesUseCase;

    public RegisterFamiliesHttp(
            UseCaseContract<List<RegisterFamiliesUseCaseInput>, List<RegisterFamiliesUseCaseOutput>> registerFamiliesUseCase) {
        this.registerFamiliesUseCase = registerFamiliesUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> execute(@RequestBody List<RegisterFamiliesUseCaseInput> input) {
        var result = this.registerFamiliesUseCase.execute(input);
        return ResponseEntity.ok(result);
    }

}
