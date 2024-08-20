package com.lobster.myhouse.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lobster.myhouse.application.usecases.registerFamilies.RegisterFamiliesUseCase;
import com.lobster.myhouse.application.usecases.registerFamilies.RegisterFamiliesUseCaseInput;
import com.lobster.myhouse.application.usecases.registerFamilies.RegisterFamiliesUseCaseOutput;
import com.lobster.myhouse.domain.Scoring.Scoring;
import com.lobster.myhouse.domain.gateway.RegisterFamiliesGateway;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

class RegisterFamiliesUseCaseTest {

    @Mock
    private RegisterFamiliesGateway registerFamiliesGateway;

    @Mock
    private Scoring scoring;

    private RegisterFamiliesUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new RegisterFamiliesUseCase(registerFamiliesGateway, scoring);
    }

    @Test
    void testExecute() {
        RegisterFamiliesUseCaseInput.Dependent dependent1 = new RegisterFamiliesUseCaseInput.Dependent("John", 10);
        RegisterFamiliesUseCaseInput.Dependent dependent2 = new RegisterFamiliesUseCaseInput.Dependent("Jane", 15);
        RegisterFamiliesUseCaseInput input = new RegisterFamiliesUseCaseInput(2000.0,
                Arrays.asList(dependent1, dependent2));

        when(scoring.caculate(anyDouble(), anyList())).thenReturn(7.5);

        List<RegisterFamiliesUseCaseOutput> result = useCase.execute(Arrays.asList(input));

        assertNotNull(result);
        assertEquals(1, result.size());
        RegisterFamiliesUseCaseOutput output = result.get(0);

       
        assertEquals(2000.0, output.totalIncome());
        assertEquals(7.5, output.totalScore());
        assertEquals(2, output.dependents().size());
        assertEquals("REGISTERED", output.status());

        verify(registerFamiliesGateway, times(1)).execute(anyList());
        verify(scoring, times(1)).caculate(anyDouble(), anyList());
    }

    @Test
    void testExecuteWithMultipleFamilies() {
        RegisterFamiliesUseCaseInput input1 = new RegisterFamiliesUseCaseInput(2000.0,
                Arrays.asList(new RegisterFamiliesUseCaseInput.Dependent("John", 10)));
        RegisterFamiliesUseCaseInput input2 = new RegisterFamiliesUseCaseInput(3000.0,
                Arrays.asList(new RegisterFamiliesUseCaseInput.Dependent("Jane", 15)));

        when(scoring.caculate(anyDouble(), anyList())).thenReturn(7.5).thenReturn(8.0);

        List<RegisterFamiliesUseCaseOutput> result = useCase.execute(Arrays.asList(input1, input2));

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(registerFamiliesGateway, times(1)).execute(argThat(families -> families.size() == 2));
        verify(scoring, times(2)).caculate(anyDouble(), anyList());
    }

    @Test
    void testExecuteWithNoDependents() {
        RegisterFamiliesUseCaseInput input = new RegisterFamiliesUseCaseInput(2000.0, Collections.emptyList());

        when(scoring.caculate(anyDouble(), isNull())).thenReturn(5.0);

        List<RegisterFamiliesUseCaseOutput> result = useCase.execute(Arrays.asList(input));

        assertNotNull(result);
        assertEquals(1, result.size());
        RegisterFamiliesUseCaseOutput output = result.get(0);

        assertTrue(output.dependents().isEmpty());

        verify(registerFamiliesGateway, times(1)).execute(anyList());
        verify(scoring, times(1)).caculate(anyDouble(), anyList());
    }
}