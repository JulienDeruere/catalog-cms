package com.catalog.services.json;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class CatalogFileUtils {

    private static final Logger LOGGER = Logger.getLogger(ShowRoomUtils.class);

    public static Path saveImageAsPath(byte[] imageAsBytes, String imagePath) {
	Path image = Paths.get(imagePath);
	try {
	    Files.write(image, imageAsBytes);
	} catch (IOException e) {
	    LOGGER.error(e);
	}
	return image;
    }

    public static void cleanUpFiles(Collection<Path> paths) {
	for (Path path : paths)
	    try {
		Files.deleteIfExists(path);
	    } catch (IOException e) {
		LOGGER.error(e);
	    }
    }

    public static Path appendFilesToZip(Path zipFile, List<Path> filesToZip) {
	Map<String, String> env = new HashMap<>();
	env.put("create", "true");
	URI uri = URI.create("jar:file:" + zipFile.toUri().getPath());
	try (FileSystem zipFS = FileSystems.newFileSystem(uri, env)) {
	    for (Path path : filesToZip)
		Files.copy(path, zipFS.getPath(path.toString()));
	} catch (IOException e) {
	    LOGGER.error(e.getMessage());
	}
	return zipFile;
    }
}