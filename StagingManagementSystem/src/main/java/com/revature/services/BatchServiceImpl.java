package com.revature.services;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Associate;
import com.revature.entities.Batch;
import com.revature.entities.BatchType;
import com.revature.entities.Trainer;
import com.revature.repositories.AssociateRepo;
import com.revature.repositories.BatchRepo;
import com.revature.repositories.BatchTypeRepo;
import com.revature.repositories.CredentialRepo;
import com.revature.repositories.TrainerRepo;

@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	AssociateRepo associateRepo;

	@Autowired
	BatchRepo batchRepo;

	@Autowired
	BatchTypeRepo batchTypeRepo;

	@Autowired
	TrainerRepo trainerRepo;

	@Autowired
	CredentialRepo credentialRepo;

	public BatchServiceImpl(BatchRepo batchRepo, BatchTypeRepo batchTypeRepo, TrainerRepo trainerRepo,
			AssociateRepo associateRepo, CredentialRepo credentialRepo) {
		super();
		this.batchRepo = batchRepo;
		this.batchTypeRepo = batchTypeRepo;
		this.trainerRepo = trainerRepo;
		this.associateRepo = associateRepo;
		this.credentialRepo = credentialRepo;
	}

	@Override
	public void addAssociateToBatch(Batch batch, Associate associate) {
		batch.getAssociates().add(associate);
		batchRepo.saveAndFlush(batch);
	}

	@Override
	public void add(Batch batch) {
		batchRepo.saveAndFlush(batch);
	}

	@Override
	public void addBatchTypes(Set<BatchType> batchTypes) {
		batchTypes.forEach((BatchType batchType) -> {
			batchTypeRepo.saveAndFlush(batchType);
		});
	}

	@Override
	public void addMockBatches(Set<Batch> batches) {
		List<Trainer> trainers = trainerRepo.findAll();
		int trainerIndex = 0;
		List<BatchType> batchTypes = batchTypeRepo.findAll();

		// Initialize a date to being the first monday of 2017
		int week = 1;
		int year = 2017;
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		LocalDateTime ldt = LocalDateTime.now().withYear(year).with(weekFields.weekOfYear(), week)
				.with(weekFields.dayOfWeek(), 2);

		for (Batch batch : batches) {
			// Assign start and end dates to the batch
			batch.setStartDate(ldt);
			batch.setEndDate(ldt.plusDays(70));

			// Add 7 days to the start date for next batch to use
			ldt = ldt.plusDays(7);

			// Assign the batch a trainer and increment so the next batch gets
			// the next trainer
			Set<Trainer> batchTrainers = new HashSet<>();
			batchTrainers.add(trainers.get(trainerIndex));
			batch.setTrainers(batchTrainers);
			if (trainerIndex == trainers.size() - 1) {
				trainerIndex = 0;
			} else {
				trainerIndex++;
			}

			// assing the batch a random type
			Random rand = new Random();
			int value = rand.nextInt(batchTypes.size());
			batch.setBatchType(batchTypes.get(value));

			batch.getAssociates().forEach((Associate associate) -> {
				String portfolio = associate.getPortfolioLink();
				if (portfolio.length() > 256) {
					associate.setPortfolioLink(portfolio.substring(0, 256));
				}
				associate.setCredential(credentialRepo.saveAndFlush(associate.getCredential()));
				associate = associateRepo.saveAndFlush(associate);
			});
			batchRepo.saveAndFlush(batch);
		}

	}

}
