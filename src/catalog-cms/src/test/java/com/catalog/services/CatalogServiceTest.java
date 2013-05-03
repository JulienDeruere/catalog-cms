package com.catalog.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.catalog.domain.Catalog;
import com.catalog.domain.Image;
import com.catalog.domain.Item;
import com.catalog.domain.Section;
import com.catalog.domain.Type;
import com.catalog.domain.Value;

public class CatalogServiceTest extends AbstractSpringServiceTest {

    @Autowired
    private CatalogService catalogService;

    @Test
    public void testExportCatalogAsJson() throws Exception {
	// create a catalog
	Catalog cata = new Catalog("MyAwesomeCatalog");

	// reading a pictuer from the ressources
	URL url = this.getClass().getResource("/chaise.jpg");
	File myPicture = new File(url.getFile());

	InputStream is = new FileInputStream(myPicture);

	long length = myPicture.length();
	byte[] bytes = new byte[(int) length];

	int offset = 0;
	int numRead = 0;
	while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
	    offset += numRead;
	}

	if (offset < bytes.length) {
	    throw new IOException("Could not completely read file " + myPicture.getName());
	}
	is.close();
	// create a domain object image
	Image image = new Image(Type.LOCAL, "chaise.jpg", bytes);

	List<String> desc = new ArrayList<>();
	desc.add("great thing");
	desc.add("another dummy desc");
	desc.add("6\"");

	Value value = new Value("spec");
	List<Value> values = new ArrayList<>();
	values.add(value);

	List<Item> items = new ArrayList<>();
	for (int i = 1; i <= 1; i++) {
	    Item item = new Item("MonSuperItem", Type.LOCAL, image);
	    item.setId(Long.valueOf(i * 2));
	    items.add(item);
	}

	List<Section> sections = new ArrayList<>();
	List<Section> sousSections = new ArrayList<>();
	for (int i = 1; i <= 1; i++) {
	    Section section = new Section("TheGreatSection", Type.LOCAL, image);
	    section.setId(Long.valueOf(i * 2));
	    section.setItems(items);

	    Section sousSection = new Section("sous Section", Type.LOCAL, image);
	    sousSection.setId(Long.valueOf(i * 4));
	    sousSection.setItems(items);
	    sousSections.add(sousSection);

	    section.setSubSections(sousSections);

	    sections.add(section);
	}

	cata.setSections(sections);
	catalogService.add(cata);
	// exporting as a zip
	File zippedCatalog = catalogService.exportCatalogAsJson(cata);
	Assert.assertTrue(zippedCatalog.exists());
	Assert.assertNotSame(0, zippedCatalog.length());
    }

    @Test
    public void getAll() {
	List<Catalog> catalogs = catalogService.getAll();
	Assert.assertNotSame(0, catalogs.size());
    }

    @Test
    public void add() {
	List<Catalog> catalogs = catalogService.getAll();
	Catalog catalog = new Catalog("Test");
	catalogService.add(catalog);
	List<Catalog> catalogs2 = catalogService.getAll();
	Assert.assertEquals(catalogs.size() + 1, catalogs2.size());
    }
}
