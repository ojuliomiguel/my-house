package com.lobster.myhouse.infrastructure.IoC.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.lobster.myhouse.domain.Family;
import com.lobster.myhouse.domain.gateway.RegisterFamiliesGateway;

@Component
public class InMemoryRegisterFamiliesAdapter implements RegisterFamiliesGateway {

    private final List<Family> familiesDatabase = new ArrayList<>();

    @Override
    public List<Family> execute(List<Family> families) {
        families.forEach(family -> {
            if (family.getId() == null || family.getId().isEmpty()) {
                family.setId(UUID.randomUUID().toString());
            }
        });
        familiesDatabase.addAll(families);
        return new ArrayList<>(families);
    }

}