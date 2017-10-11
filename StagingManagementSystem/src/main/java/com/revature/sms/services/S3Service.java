package com.revature.sms.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

	public void uploadFile(MultipartFile file) throws IOException;
	
}
