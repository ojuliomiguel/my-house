package com.lobster.myhouse.infrastructure.http;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EligibleFamiliesDTO {
    @Builder.Default
    private final int pageSize = 10;  
    @Builder.Default
    private final int pageNumber = 0; 
}