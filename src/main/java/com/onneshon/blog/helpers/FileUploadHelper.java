package com.onneshon.blog.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadHelper {

	private String UPLOAD_DIR  ;
	
	public boolean uploadUserImage(MultipartFile image) {
		
		UPLOAD_DIR = "src/main/resources/static/customImages";
		try {
			InputStream inputStream = image.getInputStream();
			byte data[] = new byte[inputStream.available()];
			inputStream.read(data);
			
			FileOutputStream fos = new FileOutputStream(image.getOriginalFilename());
			fos.close();
			fos.flush();
			
			return true;
			
		}catch (Exception e) {
			System.out.println("FILE UPLOAD FAIL"+e);
			return false;
		}		
	}
	
	
}
