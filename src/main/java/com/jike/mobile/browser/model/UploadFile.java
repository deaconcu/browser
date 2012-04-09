package com.jike.mobile.browser.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadFile {
	
	Logger log = LoggerFactory.getLogger(UploadFile.class);
	
	//field
	
	File path;
	String contentType;
	String fileName;
	
	//constructor
	
	public UploadFile(File path, String contentType, String fileName) {
		this.path = path;
		this.contentType = contentType;
		this.fileName = fileName;
	}
	
	//setter & getter
	
	public File getPath() {
		return path;
	}
	public void setPath(File path) {
		this.path = path;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//method

	public boolean validate() {
		if(path == null || path.equals("")) return false;
		else return true;
	}
	
	public void upload(String dest) throws IOException {
		upload(dest, fileName);
	}
	
	public void upload(String dest, String fileName) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(path));
		}
		catch (FileNotFoundException e) {
			log.error("Input file not fount: " + path);
			throw e;
		}
		
		OutputStream outputStream = null; 
		try {
			outputStream = new BufferedOutputStream(new FileOutputStream(dest + fileName));
		}
		catch (FileNotFoundException e) {
			log.error("Output file cannot be created: " + dest + fileName);
			throw e;
		}
		
		byte[] buffer = new byte[1024];
		int length = 0;
		try {
			while((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
		}
		catch (IOException e) {
			log.error("File I/O exception");
			throw e;
		}
		finally {
			try {
				if(inputStream != null) inputStream.close();
			}
			catch (IOException e) {
				log.error("Input file cannot close: " + path);
				throw e;
			}
			try {
				if(outputStream != null) outputStream.close();
			}
			catch (IOException e) {
				log.error("Output file cannot close" + dest + fileName);
				throw e;
			}
		}
	}
}























