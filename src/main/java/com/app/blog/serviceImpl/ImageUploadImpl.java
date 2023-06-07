package com.app.blog.serviceImpl;

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

import com.app.blog.services.ImageUploadService;
@Service
public class ImageUploadImpl implements ImageUploadService {

	@Override
	public String uploaImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stubname
	
		
		//get the file name
		String name=file.getOriginalFilename();
		//randome name genrATE
		
//		String randomID=UUID.randomUUID().toString();
//		String filename1=randomID.concat(name.substring(name.lastIndexOf(".")));
		//fullPath

		//System.out.println("File name"+filename1);
		String filepath=path+File.pathSeparator+name;
		
		//create a folder if notcreated
		File f=new File(path);
		System.out.println(f);
		
		if(!f.exists()) {
	
			System.out.println("create=="+f.mkdir());
		}
		

			Files.copy(file.getInputStream(), Paths.get(filepath));
		
		
		
		return name;
	}
	@Override
	 public InputStream getResource(String path, String fileName) throws FileNotFoundException{
	     String fullPath = path + fileName;
	     System.out.println("path=="+fullPath);
	     InputStream is = new FileInputStream(fullPath);	
	     System.out.println("full"+is);
	     // db logic to return inpustream
	     return is;
	                                
	 }

}
