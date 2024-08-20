package com.lobster.myhouse.infrastructure.IoC;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lobster.myhouse.domain.Family;

@Service
@Scope("singleton")
public class DataBase {
    public List<Family> data = new ArrayList<>();
}
