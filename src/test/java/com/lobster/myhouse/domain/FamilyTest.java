package com.lobster.myhouse.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.lobster.myhouse.domain.Scoring.MyPopularHouseScoring;
import com.lobster.myhouse.domain.Scoring.Scoring;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class FamilyTest {

    private Scoring scoring = new MyPopularHouseScoring();

    @Test
    void testCreate_FamilyWithNoDependents() {
        double expectedTotalIncome = 901.0;
        double expectedScore = 3.0;
        Family family = Family.create(901.0, Collections.emptyList(), scoring);
        assertEquals(expectedTotalIncome, family.getTotalIncome());
        assertTrue(family.getDependents().isEmpty());
        assertEquals(expectedScore, family.getScore());
    }

    @Test
    void testCreate_FamilyWithDependentsUnder18() {
        double expectedTotalIncome = 1200.0;
        double expectedScore = 5.0;
        double expectedDependents = 2.0;
        List<Dependent> dependents = Arrays.asList(
                new Dependent("John", 15),
                new Dependent("Doe", 16));
        Family family = Family.create(1200.0, dependents, scoring);
        assertNull(family.getId());
        assertEquals(expectedTotalIncome, family.getTotalIncome());
        assertEquals(expectedDependents, family.getDependents().size());
        assertEquals(expectedScore, family.getScore());
    }

}
