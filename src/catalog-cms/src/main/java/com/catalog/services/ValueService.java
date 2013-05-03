package com.catalog.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.catalog.domain.Description;
import com.catalog.domain.Value;

public interface ValueService {

    @Transactional
    public void add(Value value);
    
    public Value get(Long id);
    
    @Transactional
    public void add(Description description);
    
    public Description getDescription(Long id);
    
    public List<Value> findValue(List<Value> values, String query);
    
    public Description findDescription(List<Description> descriptions, Value value);
    
    public String findDescriptionAsString(List<Description> descriptions, Value value);
}