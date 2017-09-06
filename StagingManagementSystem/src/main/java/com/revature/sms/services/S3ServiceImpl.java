package com.revature.sms.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3ServiceImpl implements S3Service{

	@Value("${sms.bucket.name}")
	private String bucketName;
	
	@Override
	public void uploadFile(MultipartFile file) throws IOException {
		/*
		 * this is for EC2 but need to change bucket name
		 */
		/*AmazonS3 s3client = AmazonS3ClientBuilder.standard()
	              .withCredentials(new InstanceProfileCredentialsProvider(false))
	              .build();*/
		/*
		 * code end
		 */
		
		//this is for credentials
		BasicAWSCredentials cred = new BasicAWSCredentials("AKIAJKPTF4KIZRRLDUIA", "s3/UB79GdnrvsXyc34gfy++PDeA7seqU7XzkU/dh");
		AmazonS3 s3client = new AmazonS3Client(cred/*new ProfileCredentialsProvider()*/);
		//credentials end
		
		
		try {
            System.out.println("Uploading a new object to S3 from a file\n");
            //File file = new File(uploadFileName);
            //System.out.println("UPLOADED FILE AS STRING: "+uploadedFile+"\n\n");
            
            ObjectMetadata metadata = new ObjectMetadata();
            s3client.putObject(new PutObjectRequest(
            		bucketName, file.getOriginalFilename(), file.getInputStream(), metadata)/*.withCannedAcl(CannedAccessControlList.PublicRead)*/);

         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
		
		//*******************************************************************************
		
		
	}

}
