package com.lobster.myhouse.infrastructure.IoC.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lobster.myhouse.domain.Family;
import com.lobster.myhouse.domain.gateway.ListFamiliesGateway;
import com.lobster.myhouse.domain.gateway.common.Filter;
import com.lobster.myhouse.infrastructure.IoC.DataBase;

@Component
public class InMemoryListFamiliesAdapter implements ListFamiliesGateway {

    @Autowired
    private DataBase dataBase;

    @Override
    public List<Family> execute(Filter filter) {
        int fromIndex = filter.getPageNumber() * filter.getPageSize();
        int toIndex = Math.min(fromIndex + filter.getPageSize(), dataBase.data.size());

        if (fromIndex >= dataBase.data.size()) {
            return new ArrayList<>();
        }

        return dataBase.data.subList(fromIndex, toIndex);
    }

}
