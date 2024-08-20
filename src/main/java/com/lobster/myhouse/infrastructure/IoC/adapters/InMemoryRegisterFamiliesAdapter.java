package com.lobster.myhouse.infrastructure.IoC.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lobster.myhouse.domain.Family;
import com.lobster.myhouse.domain.gateway.RegisterFamiliesGateway;
import com.lobster.myhouse.infrastructure.IoC.DataBase;

@Component
public class InMemoryRegisterFamiliesAdapter implements RegisterFamiliesGateway {

    @Autowired
    private DataBase dataBase;
   
    @Override
    public List<Family> execute(List<Family> families) {
        families.forEach(family -> {
            if (family.getId() == null || family.getId().isEmpty()) {
                family.setId(UUID.randomUUID().toString());
            }
        });
        dataBase.data.addAll(families);
        return new ArrayList<>(families);
    }

}