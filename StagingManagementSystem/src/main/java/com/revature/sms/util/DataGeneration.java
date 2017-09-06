package com.revature.sms.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Batch;
import com.revature.sms.entities.Checkin;
import com.revature.sms.entities.Client;
import com.revature.sms.entities.ClientQuestion;
import com.revature.sms.entities.Interview;
import com.revature.sms.entities.InterviewQuestion;
import com.revature.sms.entities.InterviewStatuses;
import com.revature.sms.entities.Job;
import com.revature.sms.entities.Manager;
import com.revature.sms.entities.Marketer;
import com.revature.sms.services.AssociateService;
import com.revature.sms.services.BatchService;
import com.revature.sms.services.CheckinService;
import com.revature.sms.services.ClientQService;
import com.revature.sms.services.ClientService;
import com.revature.sms.services.InterviewQuestionService;
import com.revature.sms.services.InterviewStatusService;
import com.revature.sms.services.InterviewsService;
import com.revature.sms.services.JobService;
import com.revature.sms.services.ManagerService;
import com.revature.sms.services.MarketerService;

@Service
public class DataGeneration {
	/**
	 * This class holds the shared variables to be modified during the
	 * simulation process.
	 * 
	 * @author jozse
	 */
	public class SimulationState {
		LocalDateTime batchEndDate;
		LocalDateTime availibleForHireDate; // Hiring date is from a week before
											// batch end date to confirmed date.
		LocalDateTime jobStartDate;
		LocalDateTime confirmDate;
		LocalDateTime genToDate;
		LocalDateTime currentDate;
		LocalDateTime stagingEndDate;

		AssociateP associate;
		ClientP client;
		InterviewStatuses interviewStatus;
		Marketer marketer;

		SimulationState(Batch batch, LocalDateTime genToDate) {
			batchEndDate = batch.getEndDate();
			availibleForHireDate = batchEndDate.minusDays(7).withHour(8).withMinute(0);
			currentDate = availibleForHireDate;
			this.genToDate = genToDate;
		}

		@Override
		public String toString() {
			return "SimulationState [batchEndDate=" + batchEndDate + ", availibleForHireDate=" + availibleForHireDate
					+ ", jobStartDate=" + jobStartDate + ", confirmDate=" + confirmDate + ", genToDate=" + genToDate
					+ ", currentDate=" + currentDate + ", stagingEndDate=" + stagingEndDate + ", associate=" + associate
					+ ", client=" + client + ", interviewStatus=" + interviewStatus + ", marketer=" + marketer + "]";
		}

		/**
		 * Intended to indicate when a client should be dropped.
		 * 
		 * @return - true if current date is more than 5 months after
		 *         batchEndDate.
		 */
		boolean cutLosses() {
			return currentDate.compareTo(batchEndDate.plusMonths(5)) > 0;
		}

		/**
		 * Intended to indicate if an associate has not completed training.
		 * 
		 * @return - if an associate is still in training.
		 */
		boolean weekBeforeBatchEndDate() {
			return currentDate.compareTo(batchEndDate) >= 0;
		}

		/**
		 * @return - returns true if date has reached or surpassed genToDate.
		 */
		boolean terminateOn(LocalDateTime date) {
			return currentDate.compareTo(date) >= 0;
		}

		/**
		 * @return - returns true if currentDate has reached or surpassed
		 *         genToDate.
		 */
		boolean terminate() {
			return terminateOn(genToDate);
		}

		/**
		 * Called to create an interview, assumes associate, client, marketer,
		 * interviewStatus and currentDate have been set appropriately.
		 */
		public void saveInterview() {
			Interview i = new Interview(0l, associate, client, marketer, interviewStatus, currentDate);
			interviewsService.add(i);
		}

		/**
		 * @return - the status generated by current client for current
		 *         associate. (Assumes associate and client are not null)
		 */
		public InterviewStatuses evaluateAssociate() {
			return client.evaluateAssociate(associate);
		}

		/**
		 * Sets jobStartDate and stagingEndDate to currentDate plus two weeks.
		 * (Assumes currentDate is not null)
		 */
		public void setJobStart() {
			LocalDateTime date = currentDate.plusWeeks(2);
			jobStartDate = date;
			stagingEndDate = date;
		}

		/**
		 * Simple function isolated to allow for increased randomization of
		 * future implementations.
		 * 
		 * @return - a 50/50 shot at returning 1 or 2 years.
		 */
		public LocalDateTime getProjEndDate() {
			return currentDate.plusYears(rand.nextBoolean() ? 1 : 2);
		}

		/**
		 * constructs a probability(P) based on probabilityOfInterviewType,
		 * associate.clientProbabilityMultiplier, currentDate, and batchEndDate.
		 * Generates a random int [0, 100) and if the random int is between [0,
		 * P) returns true;
		 * 
		 * @param probabilityOfInterviewType
		 *            - probability associated with the type of interview in
		 *            question.
		 * @return - true P% of the time.
		 */
		public boolean hasInterview(double probabilityOfInterviewType) {
			int rollDiceInterview = rand.nextInt(100);

			// Halve the probability if it is before batch endDate.
			double probabilityOfInterview = probabilityOfInterviewType * associate.clientProbabilityMultiplier
					* (0 < currentDate.compareTo(batchEndDate) ? .5 : 1.0);

			boolean interview = rollDiceInterview < probabilityOfInterview;
			log.debug("priority interview diceRoll/probabilityOfInterview/boolean: " + rollDiceInterview + "/"
					+ probabilityOfInterview + "/" + interview);
			return interview;
		}
	}

	@Autowired
	CheckinService checkinService;
	@Autowired
	ClientQService clientQService;
	@Autowired
	InterviewsService interviewsService;
	@Autowired
	InterviewStatusService interviewStatusService;
	@Autowired
	InterviewQuestionService interviewQuestionService;
	@Autowired
	BatchService batchService;
	@Autowired
	JobService jobService;
	@Autowired
	ClientService clientService;
	@Autowired
	ManagerService managerService;
	@Autowired
	AssociateService associateService;
	@Autowired
	MarketerService marketerService;

	// Dependent Stages
	ArrayList<Checkin> checkins = new ArrayList<>();
	ArrayList<ClientQuestion> clientQs = new ArrayList<>();
	ArrayList<Interview> interviews = new ArrayList<>();
	ArrayList<Job> jobs = new ArrayList<>();

	ArrayList<ClientP> priorityClients = new ArrayList<>();
	ArrayList<ClientP> regularClients = new ArrayList<>();
	ArrayList<Associate> associates = new ArrayList<>();
	ArrayList<InterviewQuestion> interviewQuestions = new ArrayList<>();
	ArrayList<Manager> managers = new ArrayList<>();
	ArrayList<Marketer> marketers = new ArrayList<>();

	Random rand = new Random();

	double probabilityOfPriorityInterview = 60;
	double probabilityOfRegularInterview = 30;

	Logger log = Logger.getRootLogger();

	/**
	 * A client object with hiring probabilities attached.
	 * 
	 * @author jozse
	 *
	 */
	class ClientP extends Client {
		double probabilityOfHiring;
		double probabilityOfLiking;
		double probabilityOfNotInterested;

		ClientP(Client c) {
			super(c.getId(), c.getName(), c.isPriority(), c.isActive());

			probabilityOfHiring = rand.nextInt(20) + 60; // Hiring probability
															// is between 60 and
															// 80.
			probabilityOfLiking = rand.nextInt(10); // Liking probability is
													// between 0 and 10.
			probabilityOfNotInterested = 100 - ((double) probabilityOfHiring + probabilityOfLiking);

			log.debug("Client probibility hiring/liking/interested: " + probabilityOfHiring + "/" + probabilityOfLiking
					+ "/" + probabilityOfNotInterested);
		}

		/**
		 * Spring does not know about this class so this creates a super class
		 * to avoid errors.
		 * 
		 * @return - super instance
		 */
		Client getClient() {
			return new Client(this.getId(), this.getName(), this.isPriority(), this.isActive());
		}

		/**
		 * Gives based on the hiring probability of this client and the
		 * clientProbabilityMultiplier or the associate, determines if associate
		 * is hired liked or confirmed.
		 * 
		 * @param a
		 *            - associate to be evaluated
		 * @return - interviewStatus based on hiring probabilities.
		 */
		InterviewStatuses evaluateAssociate(AssociateP a) {
			int rollDice = rand.nextInt(100);

			if (rollDice < probabilityOfHiring * a.clientProbabilityMultiplier)
				return interviewStatusService.findByStatus("CONFIRMED");

			if (rollDice > 100 - (probabilityOfLiking * a.clientProbabilityMultiplier))
				return interviewStatusService.findByStatus("LIKED");

			return interviewStatusService.findByStatus("NOT_INTERESTED");
		}
	}

	/**
	 * An associate object probabilities attached.
	 * 
	 * @author jozse
	 *
	 */

	class AssociateP extends Associate {
		double clientProbabilityMultiplier;
		int immuneSystemHealth;

		AssociateP(Associate a) {
			super(a.getId(),"", a.getCredential(), a.getName(), a.getPortfolioLink(), a.getLatestPanelStatus(), a.getBatch(), a.getProject(),
					a.getLockedTo(), a.getPortfolioStatus(), a.getAssociateStatus(), a.getSkills(), a.getJobs(),a.getCertifications());

			int qualityOfAssociate = rand.nextInt(100);

			if (qualityOfAssociate < 20) // 20 percent chance of being half as
											// hirable as the average associate.
				clientProbabilityMultiplier = .5;
			else if (qualityOfAssociate > 99) // 1 percent chance of being one
												// eight as hirable as the
												// average associate.
				clientProbabilityMultiplier = .125;
			else
				clientProbabilityMultiplier = 1; // The average associate
													// corresponds with the
													// client probabilities.

			int qualityImmuneSystem = rand.nextInt(100);

			if (qualityImmuneSystem < 20) // 20 percent chance of being absent 3
											// out of 100 days.
				immuneSystemHealth = 97;
			else if (qualityImmuneSystem > 99) // 1 percent chance of being
												// absent 1 in 10 days.
				immuneSystemHealth = 90;
			else
				immuneSystemHealth = 100; // 79 percent chance of never being
											// absent.

			log.debug("Associate ClientProbabilityMultiplier: " + clientProbabilityMultiplier);
		}

		/**
		 * Spring does not know about this class so this creates a super class
		 * to avoid errors.
		 * 
		 * @return - super instance
		 */

		Associate getAssocaite() {
			this.checkAssociateStatus();
			this.checkPortfolioStatus();
			return new Associate(getId(), "", getCredential(), getName(), getPortfolioLink(), getLatestPanelStatus(),getBatch(), getProject(),
					getLockedTo(), getPortfolioStatus(), getAssociateStatus(), getSkills(), getJobs(),getCertifications());
		}
		
		 //* Randomly returns true if an associate is determined to be health.
		 
		public boolean isHealthy() {
			int diceRoll = rand.nextInt(100);
			return diceRoll < immuneSystemHealth;
		}

	}

	/**
	 * generates data up to todays date.
	 */
	public void generate() {
		generate(LocalDateTime.now());
	}

	/**
	 * Generates data up to genToDate.
	 * 
	 * @param genToDate
	 *            - date to generate up until.
	 */
	public void generate(LocalDateTime genToDate) {
		associates.addAll(associateService.getAll());
		interviewQuestions.addAll(interviewQuestionService.getAll());
		managers.addAll(managerService.getAll());
		marketers.addAll(marketerService.getAllMarketers());

		sperateClientsByType();

		// Simulate staging for each associate.
		for (Associate a : associates) {
			SimulationState state = new SimulationState(a.getBatch(), genToDate);
			state.associate = new AssociateP(a); // Create a probability
													// associate.
			simulateStaging(state);
		}
	}

	/**
	 * Creates a list of priority and regular clients.
	 */
	private void sperateClientsByType() {
		Set<Client> allClients = clientService.getAll();
		for (Client c : allClients) {
			if (c.isPriority())
				priorityClients.add(new ClientP(c));
			else
				regularClients.add(new ClientP(c));
		}
	}

	/**
	 * Creates realistic interview, interviewQuestion, checkin, and job data.
	 * 
	 * @param state
	 *            - contains information corresponding to the state of the
	 *            simulation process.
	 */
	private void simulateStaging(SimulationState state) {
		while (state.confirmDate == null && !state.terminate()) {
			if (state.cutLosses()) { // If associate does not get hired after 5
										// months.
				log.warn("Associate didint get a job in 5 months!!!");
				state.stagingEndDate = state.currentDate; // They got fired.
				break;
			}

			int nextPossibleInterview = rand.nextInt(4) + 2; // next Possible
																// date i
																// between 2 and
																// 4 days away
																// averaging 1
																// opportunity
																// EVERY 3.
			state.currentDate = state.currentDate.plusDays(nextPossibleInterview);

			// If client has priority interview simulate process for that
			// interview, else roll the dice for regular client interview.
			if (state.hasInterview(probabilityOfPriorityInterview)) {
				int daysToDecide = simulateInterview(state, .6);
				state.currentDate = state.currentDate.plusDays(daysToDecide);
			}

			if (state.confirmDate == null && state.hasInterview(probabilityOfRegularInterview)) {
				simulateInterview(state, .3);
			}
		}

		createCheckins(state);
		associateService.update(state.associate.getAssocaite());
	}

	/**
	 * Simulates the interview process based on client and associate
	 * probabilities.
	 *
	 * @param state
	 * @param convergenceFactor
	 * @param
	 * @return
	 */
	private int simulateInterview(SimulationState state, double convergenceFactor) {
		// For priority clients revature awaits their decision before more
		// interviews.
		int daysToDecide = logRythmicConvergence(0, 7, .5);

		int clientIndex = logRythmicConvergence(0, priorityClients.size(), convergenceFactor);
		state.client = priorityClients.get(clientIndex);

		if (!state.terminate()) // If today is the last day of generation do not
								// evaluate Associate.
			state.interviewStatus = state.evaluateAssociate();
		else
			state.interviewStatus = interviewStatusService.findByStatus("MAPPED");

		state.marketer = marketers.get(rand.nextInt(marketers.size()));

		// Save Interview
		state.saveInterview();

		submitInterviewQuestions(state);

		// Create Job.
		if ("CONFIRMED".equals(state.interviewStatus.getValue())) {
			state.currentDate.plusDays(daysToDecide);
			createJob(state);
			return 0;
		}
		return daysToDecide;
	}

	/**
	 * Creates and saves a job.
	 * 
	 * @param state
	 *            - simulation state info.
	 */
	private void createJob(SimulationState state) {
		// projectedEndDate and EndDate are the same for more realistic data
		// randomize end date and buyoutDate.
		state.setJobStart();

		LocalDateTime projectedEndDate = state.getProjEndDate();
		state.confirmDate = state.currentDate;
		// Should randomize actual endDate by creating a bias in the client.

		Job j = new Job(0l, state.associate, state.client, state.jobStartDate, projectedEndDate, projectedEndDate, null,
				state.confirmDate);
		jobService.add(j);

		state.associate.setLockedTo(state.client);
	}

	/**
	 * Checkin creation between the batchEndDate and the stagingEndDate.
	 * 
	 * @param state
	 *            - simulation state info.
	 */
	private void createCheckins(SimulationState state) {
		LocalDateTime currDate = state.batchEndDate;
		LocalDateTime endDate = state.stagingEndDate;

		if (endDate == null)
			endDate = LocalDateTime.now();

		while (currDate.compareTo(endDate) <= 0 && currDate.compareTo(state.genToDate) <= 0) {
			DayOfWeek day = currDate.getDayOfWeek();
			if (day != DayOfWeek.SUNDAY && day != DayOfWeek.SATURDAY && state.associate.isHealthy()) {
				Checkin checkin = new Checkin(0l, currDate.withHour(9), currDate.withHour(17), null, state.associate);

				checkinService.add(checkin);
				log.debug("Created checkin: " + checkin);
			}
			currDate = currDate.plusDays(1);
		}
	}

	/**
	 * Submits a random number of interview questions 1-4 for the given client
	 * 
	 * @param state
	 *            - simulation state info.
	 */
	private void submitInterviewQuestions(SimulationState state) {
		// Allow associate to submit questions
		int probAssociateSubQs = rand.nextInt(5); // one in 5 interviews has
													// questions posted.
		if (probAssociateSubQs == 3) {
			int numberOfQuestions = rand.nextInt(4); // between 1 and 4
														// questions are
														// submitted.
			ArrayList<Integer> chosenQuestions = new ArrayList<Integer>();
			while (chosenQuestions.size() < numberOfQuestions) {
				Integer qIndex = rand.nextInt(interviewQuestions.size());
				if (!chosenQuestions.contains(qIndex)) {
					chosenQuestions.add(qIndex);
					InterviewQuestion iq = interviewQuestions.get(qIndex);
					clientQService.add(new ClientQuestion(0l, state.client, iq, state.associate));
					log.debug("Question at index " + qIndex + ": " + iq);
				}
			}
		}
	}

	/**
	 * This converges on 1, each iteration adds convergenceFactor * (1 -
	 * totalProbability) ex. convergenceFactor = .5 iteration 1) return
	 * if(rollDice < .5 iteration 2) return if(rollDice < .75 iteration 3)
	 * return if(rollDice < .87.5 iteration 4) return if(rollDice < .93.75
	 * 
	 * @param start
	 *            - index to start at.
	 * @param end
	 *            - index to end at
	 * @param convergenceFactor
	 *            - double between [0,1)
	 * @return random value between start and end.
	 */
	private int logRythmicConvergence(int start, int end, double convergenceFactor) {
		double totalProbability = convergenceFactor;
		int tval = start;
		while (totalProbability < 1) {
			tval++;
			int rollDice = rand.nextInt(100);

			log.debug("Accept if: " + totalProbability * 100 + " > " + rollDice);
			if (totalProbability * 100 > rollDice)
				return (tval % (start - end)) + start;

			totalProbability += (1 - totalProbability) * convergenceFactor;
		}
		log.warn("Convergence did not yeald result, This should never happen.");
		return (tval % (start - end)) + start;
	
}
}