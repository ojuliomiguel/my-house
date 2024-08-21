package com.lobster.myhouse.infrastructure.IoC.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

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
        List<Family> filteredList = dataBase.data.subList(fromIndex, toIndex);
        if ("score".equalsIgnoreCase(filter.getSortBy())) {
            filteredList = filteredList.stream()
                    .sorted(Comparator.comparingDouble(Family::getScore).reversed())
                    .collect(Collectors.toList());
        }
        return filteredList;
    }

}
