package com.catalog.services.impl;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.dao.impl.ImageDao;
import com.catalog.domain.Image;
import com.catalog.domain.Item;
import com.catalog.domain.Section;
import com.catalog.domain.Type;
import com.catalog.services.ImageService;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public Image get(Long id) {
	return imageDao.findById(id);
    }
    
    @Override
    public void edit(Image image) {
	imageDao.merge(image);
    }
    
    @Override
    public void init(Image image, UploadedFile file) {
	image.setType(Type.LOCAL);
	String fileName = file.getFileName();
	int mid = fileName.lastIndexOf(".");
	String extention = fileName.substring(mid + 1, fileName.length());
	image.setExtention(extention);
	image.setMimeType(file.getContentType());
	image.setContents(file.getContents());
    }
    
    @Override
    public String getPath(Item item) {
	return "i_image_" + item.getId() + "." + item.getImage().getExtention();
    }
    
    @Override
    public String getPath(Section section) {
	return "s_image_" + section.getId() + "." + section.getImage().getExtention();
    }
}