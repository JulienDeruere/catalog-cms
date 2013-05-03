package com.catalog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.catalog.dao.impl.DescriptionDao;
import com.catalog.dao.impl.ValueDao;
import com.catalog.domain.Description;
import com.catalog.domain.Value;
import com.catalog.services.ValueService;

@Service("valueService")
public class ValueServiceImpl implements ValueService {

    @Autowired
    private ValueDao valueDao;

    @Autowired
    private DescriptionDao descriptionDao;

    @Override
    public void add(Value value) {
	valueDao.persist(value);
    }

    @Override
    public Value get(Long id) {
	return valueDao.findById(id);
    }

    @Override
    public void add(Description description) {
	descriptionDao.persist(description);
    }

    @Override
    public Description getDescription(Long id) {
	return descriptionDao.findById(id);
    }

    @Override
    public List<Value> findValue(List<Value> values, String query) {
	List<Value> results = new ArrayList<>();
	if (Strings.isNullOrEmpty(query))
	    return values;
	for (Value value : values)
	    if (value.getKey().startsWith(query))
		results.add(value);
	return results;
    }

    @Override
    public Description findDescription(List<Description> descriptions, Value value) {
	for (Description desc : descriptions)
	    if (desc.getValue().equals(value))
		return desc;
	return null;
    }

    @Override
    public String findDescriptionAsString(List<Description> descriptions, Value value) {
	Description desc = findDescription(descriptions, value);
	if (desc != null)
	    return desc.descriptionAsString();
	return null;
    }
}