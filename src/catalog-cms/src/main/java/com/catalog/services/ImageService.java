package com.catalog.services;

import org.primefaces.model.UploadedFile;

import com.catalog.domain.Image;
import com.catalog.domain.Item;
import com.catalog.domain.Section;

public interface ImageService {

    public Image get(Long id);
    
    public void edit(Image image);

    public void init(Image image, UploadedFile file);

    public String getPath(Item item);

    public String getPath(Section section);
}