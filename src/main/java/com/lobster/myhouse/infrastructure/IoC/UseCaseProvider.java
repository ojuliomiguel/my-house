package com.lobster.myhouse.infrastructure.IoC;

import com.lobster.myhouse.application.contracts.usecase.UseCaseContract;
import com.lobster.myhouse.application.usecases.registerFamilies.RegisterFamiliesUseCase;
import com.lobster.myhouse.application.usecases.registerFamilies.RegisterFamiliesUseCaseInput;
import com.lobster.myhouse.application.usecases.registerFamilies.RegisterFamiliesUseCaseOutput;
import com.lobster.myhouse.domain.Scoring.Scoring;
import com.lobster.myhouse.domain.gateway.RegisterFamiliesGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UseCaseProvider {

    @Bean
    public UseCaseContract<List<RegisterFamiliesUseCaseInput>, List<RegisterFamiliesUseCaseOutput>> registerFamiliesUseCase(
            RegisterFamiliesGateway registerFamiliesGateway, Scoring scoring) {
        return new RegisterFamiliesUseCase(registerFamiliesGateway, scoring);
    }
}
