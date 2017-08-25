package com.revature.sms.repositories;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.salesforce.beans.SalesforceBatch;
import com.revature.salesforce.beans.SalesforceBatchResponse;
import com.revature.salesforce.beans.SalesforceTrainee;
import com.revature.salesforce.beans.SalesforceTraineeResponse;
import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Batch;
import com.revature.sms.exceptions.ServiceNotAvailableException;
import com.revature.sms.salesforce.SalesforceTransformer;
import com.revature.sms.security.models.SalesforceUser;

/*
 * Most of this code adapted from Patrick Walsh's Caliber.
 */

@Component
public class SalesforceRepoImpl implements SalesforceRepo {
	private Logger log = Logger.getRootLogger();
	@Value("${sms.salesforce}")
	private boolean salesforce;

	@Value("${sms.sf.login}")
	private String salesforceInstanceUrl;
	@Value("/services/data/v39.0/query/")
	private String salesforceApiUrl;

	@Autowired
	private SalesforceTransformer transformer;

	//////////// SOQL - Salesforce Object Query Language //////////////

	/**
	 * Will change as of version 2.0 Salesforce API in August/September 2017
	 * timeframe Used to populate the dropdown list of importable batches.
	 */
	@Value("select id, name, batch_start_date__c, batch_end_date__c, "
			+ "batch_trainer__r.name, batch_trainer__r.email, Co_Trainer__r.name, Co_Trainer__r.email, "
			+ "Skill_Type__c, Location__c, Type__c from training__c "
			+ "where batch_trainer__r.name != null and batch_start_date__c >= THIS_YEAR")
	private String relevantBatches;

	/**
	 * Will change as of version 2.0 Salesforce API in August/September 2017
	 * timeframe Once user selects a batch to import, use this to load all the
	 * Trainee details. ResourceId *MUST* be surrounded in single quotes to
	 * function properly
	 */
	@Value("select id, name, training_status__c, phone, email, MobilePhone, "
			+ "Training_Batch__c , Training_Batch__r.name, " + "Training_Batch__r.batch_start_date__c, "
			+ "Training_Batch__r.batch_end_date__c, " + "Training_Batch__r.batch_trainer__r.name, "
			+ "rnm__Recruiter__r.name, account.name, " + "Training_Batch__r.Co_Trainer__r.name, "
			+ "eintern_current_project_completion_pct__c , " + "Training_Batch__r.Skill_Type__c, "
			+ "Training_Batch__r.Type__c from Contact " + "where training_batch__c = ")
	private String batchDetails;
	
	@Value("select id, name, training_status__c, phone, email, MobilePhone, "
			+ "Training_Batch__c , Training_Batch__r.name, " + "Training_Batch__r.batch_start_date__c, "
			+ "Training_Batch__r.batch_end_date__c, " + "Training_Batch__r.batch_trainer__r.name, "
			+ "rnm__Recruiter__r.name, account.name, " + "Training_Batch__r.Co_Trainer__r.name, "
			+ "eintern_current_project_completion_pct__c , " + "Training_Batch__r.Skill_Type__c, "
			+ "Training_Batch__r.Type__c from Contact " + "where training_status__c = 'Marketing'")
	private String benchAssociates;
	
	@Value("select id, name, batch_start_date__c, batch_end_date__c, "
			+ "batch_trainer__r.name, batch_trainer__r.email, Co_Trainer__r.name, Co_Trainer__r.email, "
			+ "Skill_Type__c, Location__c, Type__c from training__c "
			+ "where id= ")
	private String batch;
	
	//////////// DAO methods ////////////////
	@Override
	public List<Batch> getRelevantBatches(SalesforceUser user) {
		List<Batch> relevantBatchesList = new LinkedList<>();

		try {
			SalesforceBatchResponse response = new ObjectMapper().readValue(
					getFromSalesforce(relevantBatches, user).getEntity().getContent(), SalesforceBatchResponse.class);
			log.info("Found " + response.getTotalSize() + " batches: " + response);

			for (SalesforceBatch salesForceBatch : response.getRecords()) {
				relevantBatchesList.add(transformer.transformBatch(salesForceBatch));
			}
		} catch (IOException e) {
			log.error("Cannot get Salesforce batches:  " + e);
		}

		return relevantBatchesList;
	}
	
	@Override
	public Batch getBatch(String resourceId, SalesforceUser user) {
		String query = batch+"'"+resourceId+"'";
		Batch b = null;

		try {
			SalesforceBatchResponse response = new ObjectMapper().readValue(
					getFromSalesforce(query, user).getEntity().getContent(), SalesforceBatchResponse.class);
			log.info("Found " + response.getTotalSize() + " batches: " + response);

			if(response.getRecords().length==1)
				b= transformer.transformBatch(response.getRecords()[0]);
			else
				log.error("Too many batches");

		} catch (IOException e) {
			log.error("Cannot get Salesforce batches:  " + e);
		}

		return b;
	}
	@Override
	public List<Associate> getBatchTrainees(String resourceId, SalesforceUser user) {
		String query = batchDetails + "'" + resourceId + "'";
		List<Associate> trainees = new LinkedList<>();
		
		try {
			SalesforceTraineeResponse response = new ObjectMapper().readValue(getFromSalesforce(query, user).getEntity().getContent(), SalesforceTraineeResponse.class);
			log.info(response);
			for(SalesforceTrainee trainee : response.getRecords()){
				trainees.add(transformer.transformTrainee(trainee, user));
			}
			
		} catch (IOException e) {
			log.error("Cannot get batch details from Salesforce: cause " + e);
			throw new ServiceNotAvailableException();
		}
		return trainees;
	}
	
	@Override
	public List<Associate> getBenchTrainees(SalesforceUser user) {
		String query = benchAssociates;
		List<Associate> trainees = new LinkedList<>();
		
		try {
			InputStream is = getFromSalesforce(query,user).getEntity().getContent();
			log.trace(is);
			SalesforceTraineeResponse response = new ObjectMapper().readValue(is, SalesforceTraineeResponse.class);
			log.info("Checking for benched trainees.");
			log.info(response);
			for(SalesforceTrainee trainee : response.getRecords()){
				trainees.add(transformer.transformBenchTrainee(trainee, user));
			}
			
		} catch (IOException e) {
			log.error("Cannot get batch details from Salesforce: cause " + e);
			throw new ServiceNotAvailableException();
		}
		return trainees;
	}
	//////////// API Helper Methods //////////////

	/**
	 * Helper method to call HTTP GET request to Salesforce REST API
	 * 
	 * @param soql
	 * @return
	 */
	private HttpResponse getFromSalesforce(String soql, SalesforceUser user) {
		try {
			String instanceUrl = salesforceInstanceUrl.substring("https://".length());
			HttpClient httpClient = HttpClientBuilder.create().build();
			String url = new URIBuilder(salesforceInstanceUrl).setScheme("https").setHost(instanceUrl)
					.setPath(salesforceApiUrl).setParameter("q", soql).build().toString();
			HttpGet getRequest = new HttpGet(url);
			getRequest.setHeader("Authorization", "Bearer " + getAccessToken(user));
			return httpClient.execute(getRequest);
		} catch (IOException | URISyntaxException e) {
			log.error("Unable to fetch Salesforce data: cause " + e);
			throw new ServiceNotAvailableException();
		}
	}

	/**
	 * Helper method to return the Salesforce access_token being managed by
	 * Spring Security
	 * 
	 * @return
	 */
	private String getAccessToken(SalesforceUser user) {
		if (!salesforce)
			return "00D0n0000000Q1l!AQQAQGCIRGGBiQitAaZKeja8rvjTAq.Sstul_2RRs4tgHOc7W.MzUm4W99HkTWxyuSWCgZTYdpH9hQ2QGF_p9IHrQwssXVhU";
		else
			return user.getSalesforceToken().getAccessToken();
	}

	/**
	 * Get all the batches in the current year and future years. Access data
	 * using the Salesforce REST API. Returns as String in case the result is
	 * not actually a batch. Used to debug environment issues.
	 * 
	 * @return
	 */
	public String getSalesforceResponseString(SalesforceUser user) {
		try {
			return new ObjectMapper()
					.readValue(getFromSalesforce(relevantBatches, user).getEntity().getContent(), JsonNode.class).asText();
		} catch (IOException e) {
			log.error("Cannot get Salesforce batches:  " + e);
			return null;
		}
	}
}
