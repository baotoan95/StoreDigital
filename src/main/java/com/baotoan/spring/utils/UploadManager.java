package com.baotoan.spring.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadManager {
	public static boolean uploadFile(String name, MultipartFile file, String store) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				File dir = new File(store);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				System.out.println(serverFile.getPath());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("File is empty");
			return false;
		}
	}
}
