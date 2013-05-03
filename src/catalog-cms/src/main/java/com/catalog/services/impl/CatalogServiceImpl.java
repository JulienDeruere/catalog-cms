package com.catalog.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.catalog.dao.impl.CatalogDao;
import com.catalog.domain.Catalog;
import com.catalog.domain.Image;
import com.catalog.domain.Item;
import com.catalog.domain.Section;
import com.catalog.domain.Type;
import com.catalog.services.CatalogService;
import com.catalog.services.ImageService;
import com.catalog.services.json.ItemSerializer;
import com.catalog.services.json.ItemsSerializer;
import com.catalog.services.json.SectionsSerializer;
import com.catalog.services.json.ShowRoomUtils;
import com.catalog.services.json.TypeSerializer;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    private final static Logger LOGGER = Logger.getLogger(CatalogServiceImpl.class);

    @Autowired
    private CatalogDao catalogDao;

    @Autowired
    private ImageService imageService;

    @Override
    public Catalog get(Long id) {
	return catalogDao.findById(id);
    }

    @Override
    public List<Catalog> getAll() {
	return catalogDao.findAll();
    }

    @Override
    public void add(Catalog catalog) {
	catalogDao.persist(catalog);
    }

    @Override
    public void edit(Catalog catalog) {
	catalogDao.merge(catalog);
    }

    public File exportCatalogAsJson(Catalog catalog) {
	catalog = get(catalog.getId());

	// Setup the jackson mapper and configure custom serializers
	ObjectMapper jacksonMapper = new ObjectMapper();
	jacksonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	jacksonMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

	SimpleModule jacksonModule = new SimpleModule();
	jacksonModule.addSerializer(new TypeSerializer());
	jacksonModule.addSerializer(new SectionsSerializer());
	jacksonModule.addSerializer(new ItemsSerializer());
	jacksonModule.addSerializer(new ItemSerializer());
	jacksonMapper.registerModule(jacksonModule);

	List<Path> zipFiles = new ArrayList<>();

	try {
	    Path srSectionsFile = Paths.get("srSections.json");
	    jacksonMapper.writeValue(srSectionsFile.toFile(), catalog);
	    zipFiles.add(srSectionsFile);

	    for (Section section : catalog.getSections()) {
		addImage(zipFiles, section.getImage(), imageService.getPath(section));
		String sectionId = Long.toString(section.getId());
		Path itemsAsJson = Paths.get("s_" + sectionId + ".json");
		jacksonMapper.writeValue(itemsAsJson.toFile(), section);
		zipFiles.add(itemsAsJson);

		for (Item item : section.getItems()) {
		    addImage(zipFiles, item.getImage(), imageService.getPath(item));
		    String itemId = Long.toString(item.getId());
		    Path itemAsJson = Paths.get("i_" + itemId + ".json");
		    jacksonMapper.writeValue(itemAsJson.toFile(), item);
		    zipFiles.add(itemAsJson);
		}
	    }
	} catch (IOException e) {
	    LOGGER.error(e);
	}

	Path zipFile = Paths.get(catalog.getName() + "_" + catalog.getId() + "_" + System.nanoTime() + ".zip");

	ShowRoomUtils.appendFilesToZip(zipFile, zipFiles);
	ShowRoomUtils.cleanUpFiles(zipFiles);

	return zipFile.toFile();
    }

    private void addImage(List<Path> imageFiles, Image image, String path) {
	if (image.getType() == Type.LOCAL) {
	    Path imagePath = ShowRoomUtils.saveImageAsPath(image.getContents(), path);
	    imageFiles.add(imagePath);
	}
    }
}