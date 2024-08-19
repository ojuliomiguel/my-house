package com.lobster.myhouse.infrastructure.IoC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lobster.myhouse.domain.Scoring.MyPopularHouseScoring;
import com.lobster.myhouse.domain.Scoring.Scoring;

@Configuration
public class ScoringProvider {
    @Bean
    public Scoring scoring() {
        return new MyPopularHouseScoring();
    }
}
