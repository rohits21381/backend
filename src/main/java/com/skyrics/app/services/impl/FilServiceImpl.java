package com.skyrics.app.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skyrics.app.services.FileService;

@Service
public class FilServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException{
		
		//file name
		String name = file.getOriginalFilename();
		
		//random name generated file 
		String randomId = UUID.randomUUID().toString();
		String randomName = randomId.concat(name.substring(name.lastIndexOf(".")));
		//full path
		String filePath= path+File.separator+randomName;
		
		//created folder if not created
		File file2 = new File(path);
		if (!file2.exists()) {
			file2.mkdir();
		}
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return randomName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fillPath = path+File.separator+fileName;
		FileInputStream fileInputStream = new FileInputStream(fillPath);
		return fileInputStream;
	}

}
