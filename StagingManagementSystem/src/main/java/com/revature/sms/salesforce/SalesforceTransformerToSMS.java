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
import com.revature.sms.entities.Location;
import com.revature.sms.entities.Panel;
import com.revature.sms.entities.Trainer;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.AssociatesStatusRepo;
import com.revature.sms.repositories.BatchRepo;
import com.revature.sms.repositories.BatchTypeRepo;
import com.revature.sms.repositories.PanelRepo;
import com.revature.sms.repositories.TrainerRepo;

@Component
public class SalesforceTransformerToSMS implements SalesforceTransformer{

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
	
	@Override
	public Batch transformBatch(SalesforceBatch salesforceBatch) {
		Batch batch = bRepo.getBySalesforceId(salesforceBatch.getId());
		if(batch==null)
		{
			batch = new Batch();
			batch.setSalesforceId(salesforceBatch.getId());
			batch.setLocation(new Location());
		}
		batch.setBatchType(batchHelper(salesforceBatch.getSkillType()));
		batch.setStartDate(LocalDateTime.ofInstant(salesforceBatch.getBatchStartDate().toInstant(), ZoneId.systemDefault()));
		batch.setEndDate(LocalDateTime.ofInstant(salesforceBatch.getBatchEndDate().toInstant(), ZoneId.systemDefault()));
		batch.getLocation().setName(salesforceBatch.getLocation());
		
		Set<Trainer> trainers = new HashSet<Trainer>();
		trainers.add(transformTrainer(salesforceBatch.getTrainer()));
		trainers.add(transformTrainer(salesforceBatch.getCotrainer()));
		batch.setTrainers(trainers);
		return batch;
	}

	@Override
	public Trainer transformTrainer(BatchTrainer batchTrainer) {
		if(batchTrainer==null)
			return new Trainer();
		Trainer trainer = tRepo.getByName(batchTrainer.getName());
		if(trainer==null)
		{
			trainer = new Trainer();
			trainer.setName(batchTrainer.getName());
		}
		trainer.setActive(true);
		return trainer;
	}

	@Override
	public Associate transformTrainee(SalesforceTrainee salesforceTrainee) {
		Associate associate = aRepo.getBySalesforceId(salesforceTrainee.getId());
		if(associate==null)
		{
			associate=new Associate();
			associate.setSalesforceId(salesforceTrainee.getId());
		}
		associate.setName(salesforceTrainee.getName());
		associate.setAssociateStatus(statusHelper(associate, salesforceTrainee.getTrainingStatus()));
		associate.setBatch(bRepo.getBySalesforceId(salesforceTrainee.getBatchId()));
		return associate;
	}
	
	private AssociatesStatus statusHelper(Associate associate, String trainingStatus) {
		//Retrieve new status from database
		String status;
		switch(trainingStatus)
		{
			case "Signed": status = "TRAINING"; break;
			case "Training": status = "TRAINING"; break;
			case "Marketing": status = "STAGING"; break;
			case "Confirmed": status = "PROJECT"; break;
			case "Employed": status = "PROJECT"; break;
			case "Bench": status = "BENCH"; break;
			default: status = "UNKNOWN"; break;
		}
		AssociatesStatus as = asRepo.getByStatus(status);
		if(as == null)
		{
			as = new AssociatesStatus();
			as.setStatus(status);
		}
		//Check if there was an old status
		AssociatesStatus currentStatus = associate.getAssociateStatus();
		if(currentStatus!=null)
		{
			//We have to update a few things
			if(status.equals("BENCH")&&currentStatus.getStatus().equals("PROJECT"))
			{
				//set portfolio status to false
				//TODO associate.setPortfolioStatus(portfolioStatus);
				//create a new panel
				Panel panel = new Panel();
				panel.setAssociate(associate);
				pRepo.save(panel);
			}
		}
		return as;
	}

	private BatchType batchHelper(String skillType)
	{
		BatchType bt = btRepo.getByValue(skillType);
		if(bt == null)
		{
			bt= new BatchType();
			bt.setValue(skillType);
		}
		return bt;
	}

}
