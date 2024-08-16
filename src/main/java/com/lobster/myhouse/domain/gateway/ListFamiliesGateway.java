package com.lobster.myhouse.domain.gateway;

import java.util.List;

import com.lobster.myhouse.domain.Family;
import com.lobster.myhouse.domain.gateway.common.Filter;

public interface ListFamiliesGateway {
    List<Family> execute(Filter filter);
}
