package com.lobster.myhouse.domain.gateway;

import java.util.List;

import com.lobster.myhouse.domain.Family;

public interface RegisterFamiliesGateway {
    List<Family> execute(List<Family> families);
}
