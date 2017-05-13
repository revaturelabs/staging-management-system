package com.revature.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableCaching
public class DAOImpl implements DAOService {

	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public void AddClient(ClientInfo ci) {
		
		// create session object
		Session session = sf.getCurrentSession();
		//add client to db
		session.saveOrUpdate(ci);
		
		//System.out.println("done inserting client");
		
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public void AddAssociate(AssociateInfo ai) {
		
		// create session object
		Session session = sf.getCurrentSession();
		//add client to db
		session.saveOrUpdate(ai);
				
		//System.out.println("done inserting asso");
				
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public void AddBatch(BatchInfo bi) {
		// create session object
		Session session = sf.getCurrentSession();
		//add client to db
		session.saveOrUpdate(bi);
				
		//System.out.println("done inserting batch");
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public ClientInfo GetClientFromDB(long id) {
		ClientInfo client = null;
		
		Session session = sf.getCurrentSession();

		Criteria criteria = session.createCriteria(ClientInfo.class);
		criteria.add((Restrictions.like("ClientID", id)));
		client = (ClientInfo) criteria.uniqueResult();
		return client;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public AssociateInfo GetAssociateFromDB(long id) {
		
		AssociateInfo associate = null;
		
		Session session = sf.getCurrentSession();

		Criteria criteria = session.createCriteria(AssociateInfo.class);
		criteria.add((Restrictions.like("AssociateID", id)));
		associate = (AssociateInfo) criteria.uniqueResult();
		return associate;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public BatchInfo GetBatchFromDB(String id) {
		BatchInfo batch = null;
		
		Session session = sf.getCurrentSession();

		Criteria criteria = session.createCriteria(BatchInfo.class);
		criteria.add((Restrictions.like("TrainingName", id)));
		batch = (BatchInfo) criteria.uniqueResult();
		return batch;
	}


	@Override
	public ArrayList<AssociateInfo> GetAllAssociatesDB() {
		
		return null;
	}


	@Override
	public ArrayList<BatchInfo> GetAllBatchesDB() {
		return null;
	}


	@Override
	public ArrayList<ClientInfo> GetAllClientsDB() {
		return null;
	}

}
