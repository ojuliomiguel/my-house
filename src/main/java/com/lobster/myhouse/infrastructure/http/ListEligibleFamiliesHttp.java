package com.lobster.myhouse.infrastructure.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lobster.myhouse.domain.gateway.common.Filter;
import com.lobster.myhouse.infrastructure.IoC.adapters.InMemoryListFamiliesAdapter;

@RestController
@RequestMapping("/families/eligibles")
public class ListEligibleFamiliesHttp {

    private final InMemoryListFamiliesAdapter inMemoryListFamiliesAdapter;

    public ListEligibleFamiliesHttp(InMemoryListFamiliesAdapter inMemoryListFamiliesAdapter) {
        this.inMemoryListFamiliesAdapter = inMemoryListFamiliesAdapter;
    }

    @GetMapping
    public ResponseEntity<Object> execute() {
        Filter filter = new Filter.Builder()
                .build();
        var result = this.inMemoryListFamiliesAdapter.execute(filter);
        return ResponseEntity.ok(result);
    }

}
