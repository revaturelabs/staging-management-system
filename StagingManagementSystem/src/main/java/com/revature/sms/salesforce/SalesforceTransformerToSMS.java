package com.revature.sms.salesforce;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.salesforce.beans.BatchTrainer;
import com.revature.salesforce.beans.SalesforceBatch;
import com.revature.salesforce.beans.SalesforceTrainee;
import com.revature.sms.entities.Associate;
import com.revature.sms.entities.AssociatesStatus;
import com.revature.sms.entities.Batch;
import com.revature.sms.entities.BatchType;
import com.revature.sms.entities.Credential;
import com.revature.sms.entities.Location;
import com.revature.sms.entities.Panel;
import com.revature.sms.entities.Trainer;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.AssociatesStatusRepo;
import com.revature.sms.repositories.BatchRepo;
import com.revature.sms.repositories.BatchTypeRepo;
import com.revature.sms.repositories.CredentialRepo;
import com.revature.sms.repositories.LocationRepo;
import com.revature.sms.repositories.PanelRepo;
import com.revature.sms.repositories.SalesforceRepo;
import com.revature.sms.repositories.TrainerRepo;
import com.revature.sms.security.models.SalesforceUser;

@Component
public class SalesforceTransformerToSMS implements SalesforceTransformer {

	@Autowired
	private BatchTypeRepo btRepo;
	@Autowired
	private BatchRepo bRepo;
	@Autowired
	private TrainerRepo tRepo;
	@Autowired
	private AssociateRepo aRepo;
	@Autowired
	private AssociatesStatusRepo asRepo;
	@Autowired
	private PanelRepo pRepo;
	@Autowired
	private LocationRepo lRepo;
	@Autowired
	private CredentialRepo cRepo;
	@Autowired
	private SalesforceRepo sRepo;

	@Override
	public Batch transformBatch(SalesforceBatch salesforceBatch) {
		Batch batch = bRepo.getBySalesforceId(salesforceBatch.getId());
		if (batch == null) {
			batch = new Batch();
			batch.setSalesforceId(salesforceBatch.getId());
			batch.setLocation(locationHelper(salesforceBatch.getLocation()));
		}
		batch.setBatchType(batchHelper(salesforceBatch.getSkillType()));
		batch.setStartDate(
				LocalDateTime.ofInstant(salesforceBatch.getBatchStartDate().toInstant(), ZoneId.systemDefault()));
		batch.setEndDate(
				LocalDateTime.ofInstant(salesforceBatch.getBatchEndDate().toInstant(), ZoneId.systemDefault()));

		Set<Trainer> trainers = new HashSet<Trainer>();
		trainers.add(transformTrainer(salesforceBatch.getTrainer()));
		trainers.add(transformTrainer(salesforceBatch.getCotrainer()));
		batch.setTrainers(trainers);
		return batch;
	}

	@Override
	public Trainer transformTrainer(BatchTrainer batchTrainer) {
		if (batchTrainer == null)
			return null;
		Trainer trainer = tRepo.getByName(batchTrainer.getName());
		if (trainer == null) {
			trainer = new Trainer();
			trainer.setName(batchTrainer.getName());
		}
		trainer.setActive(true);

		return tRepo.save(trainer);
	}

	@Override
	public Associate transformTrainee(SalesforceTrainee salesforceTrainee, SalesforceUser user) {
		Associate associate = aRepo.getBySalesforceId(salesforceTrainee.getId());
		if (associate == null) {
			associate = new Associate();
			associate.setSalesforceId(salesforceTrainee.getId());
			Credential cred = new Credential();
			cred.setUsername(salesforceTrainee.getEmail());
			cred.setPassword("password");
			cRepo.save(cred);
			associate.setCredential(cred);
		}
		associate.setName(salesforceTrainee.getName());
		associate.setAssociateStatus(statusHelper(associate, salesforceTrainee.getTrainingStatus()));
		associate.setBatch(bRepo.getBySalesforceId(salesforceTrainee.getBatchId()));
		if (associate.getBatch() == null) {
			Batch b = sRepo.getBatch(salesforceTrainee.getBatchId(), user);
			bRepo.save(b);
			associate.setBatch(b);
		}
		return associate;
	}

	@Override
	public Associate transformBenchTrainee(SalesforceTrainee salesforceTrainee, SalesforceUser user) {
		salesforceTrainee.setTrainingStatus("Bench");
		Associate associate = aRepo.getBySalesforceId(salesforceTrainee.getId());
		if (associate == null) {
			associate = new Associate();
			associate.setSalesforceId(salesforceTrainee.getId());
			Credential cred = new Credential();
			cred.setUsername(salesforceTrainee.getEmail());
			cred.setPassword("password");
			cRepo.save(cred);
			associate.setCredential(cred);
		}
		associate.setName(salesforceTrainee.getName());
		associate.setAssociateStatus(statusHelper(associate, salesforceTrainee.getTrainingStatus()));
		associate.setBatch(bRepo.getBySalesforceId(salesforceTrainee.getBatchId()));
		if (associate.getBatch() == null) {
			Batch b = sRepo.getBatch(salesforceTrainee.getBatchId(), user);
			if(b!=null)
				bRepo.save(b);
			associate.setBatch(b);
		}
		return associate;
	}

	private AssociatesStatus statusHelper(Associate associate, String trainingStatus) {
		// Retrieve new status from database
		String status;
		switch (trainingStatus) {
		case "Signed":
			status = "TRAINING";
			break;
		case "Training":
			status = "TRAINING";
			break;
		case "Marketing":
			status = "STAGING";
			break;
		case "Confirmed":
			status = "PROJECT";
			break;
		case "Employed":
			status = "PROJECT";
			break;
		default:
			status = "UNKNOWN";
			break;
		}

		// Check if there was an old status
		AssociatesStatus currentStatus = associate.getAssociateStatus();
		if (currentStatus != null) {
			// We have to update a few things
			if (status.equals("STAGING") && currentStatus.getStatus().equals("PROJECT")) {
				// status should actually be BENCH
				status = "BENCH";
				// set portfolio status to false
				// TODO associate.setPortfolioStatus(portfolioStatus);
				// create a new panel
				Panel panel = new Panel();
				panel.setAssociate(associate);
				panel = pRepo.save(panel);
			}
		}
		
		AssociatesStatus as = asRepo.getByStatus(status);
		if (as == null) {
			as = new AssociatesStatus();
			as.setStatus(status);
			as = asRepo.save(as);
		}
		return as;
	}

	private BatchType batchHelper(String skillType) {
		BatchType bt = btRepo.getByValue(skillType);
		if (bt == null) {
			bt = new BatchType();
			bt.setValue(skillType);

			bt = btRepo.save(bt);
		}
		return bt;
	}

	private Location locationHelper(String location) {

		String name;
		if (location != null) {
			String[] pieces = location.split("\\|");
			if (pieces.length > 1) {
				name = pieces[0];
			} else {
				String[] pieces2 = location.split(",");
				name = pieces2[0];
			}
		} else {
			name = "Unknown";
		}
		Location l = lRepo.findByName(name);
		if (l == null) {
			l = new Location();
			l.setName(name);

			l = lRepo.save(l);
		}
		return l;
	}
}
