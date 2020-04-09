package com.hobbymatcher.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static String getUploadFolder() {
		Properties prop = new Properties();
		try {
			prop.load(FileUtil.class.getClassLoader().getResourceAsStream("fileupload.properties"));
		} catch (IOException exp) {
			exp.printStackTrace();
		}
		return prop.getProperty("uploadpath", "");
	}

	public static String transferFile(MultipartFile file) {
		if (file != null) {
			File root = new File(getUploadFolder());

			if (!root.exists()) {
				root.mkdirs();
			}

			// String orgName = file.getOriginalFilename();
			String newName = UUID.randomUUID().toString();
			// + orgName; we don't need to store file original name
			File targetPath = new File(root, newName);

			try {
				file.transferTo(targetPath);
				return newName;
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}

		return null;
	}

	public static String getName(File file) {
		// remove uuid from file name
		// return file.getName().substring(36);
		return Integer.toString((int) (Math.random() * Integer.MAX_VALUE));
	}
}
