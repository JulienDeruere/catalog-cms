package com.catalog.dao.impl;

import org.springframework.stereotype.Repository;

import com.catalog.dao.GenericObjectDao;
import com.catalog.domain.Image;

@Repository
public class ImageDao extends GenericObjectDao<Image> {

    public ImageDao() {
	super(Image.class);
    }
}
