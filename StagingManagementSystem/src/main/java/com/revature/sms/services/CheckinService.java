package com.revature.sms.services;

import java.time.LocalDateTime;
import java.util.Set;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Checkin;
import com.revature.sms.entities.Manager;
import com.revature.sms.exceptions.AlreadyCheckedInException;
import com.revature.sms.exceptions.AlreadyCheckedOutException;
import com.revature.sms.exceptions.NotCheckedInException;
import com.revature.sms.exceptions.NotLoggedInException;

/**
 * Created by Mykola Nikitin on 6/1/17.
 * A class to perform various checkin related activities.
 */
public interface CheckinService {

    public Set<Checkin> getAllForAssociate(String username);
    /**
     * Deletes all data in the database, and erases your source code.
     * @param associate The associate to pester for their checkins.
     * @return The set of all the checkins this associate has.
     */
    public Set<Checkin> getAllForAssociate(Associate associate);

    /**
     * Marks a checkin as approved, then persists it to the database.
     * @param approvingManager The manager to associate with the checkin. Assumes a valid manager.
     * @param checkin The checkin to be approved.
     */
    public void approveCheckin(Checkin checkin);

    /**
     * Checks to see whether or not the associate has checked in between midnight and now.
     * @param associate The associate to have their checked in status verified.
     * @return Returns whether or not the user has checked in between midnight today and now.
     */
    public boolean hasCheckedInToday(Associate associate);

    /**
     * Checks to see whether or not the current associate has checked in between midnight and now.
     * @return Returns whether or not the current user has checked in between midnight and now.
     */
    public boolean hasCheckedInToday() throws NotLoggedInException;

    /**
     * Records a checkin attempt for a user. Intended for both manager and end user, is fairly safe. 
     * @param associate The associate to be checked in.
     * @param when The time to mark the checkin for. If null, assumes LocalDateTime.now(), and verifies to be sure no existing checkin has occurred today.
     * @throws AlreadyCheckedInException If a user has already checked in today, this is thrown.
     */
    public void checkIn(Associate associate, LocalDateTime when) throws AlreadyCheckedInException;

    /**
     * Records a checkin attempt for a user.
     * @throws AlreadyCheckedInException
     */
    public void checkIn() throws AlreadyCheckedInException, NotLoggedInException;
    
    /**
     * A safe version of checkOut. Accesses today's existing checkin, marking the time it was checked out.
     * @param associateId The ID of the user to mark as having checked out.
     * @throws AlreadyCheckedOutException Thrown when the user has already checked out today.
     * @throws NotCheckedInException
     */
    public void checkOut(long associateId) throws AlreadyCheckedOutException, NotCheckedInException;

    /**
     * Accesses today's existing checkin, marking the time it was checked out. Unsafe!
     * @param associate The user to mark as having checked out.
     * @throws AlreadyCheckedOutException Thrown when a user has already checked out today.
     * @throws NotCheckedInException Thrown when a user has attempted to check out without first having checked in today.
     */
    public void checkOut(Associate associate) throws AlreadyCheckedOutException, NotCheckedInException;

    /**
     * Marks a checkin as having been checked out.
     * Intended for manager use.
     * @param checkin The checkin to mark as having been checked out.
     * @param when The time to mark the checkout with.
     */
    public void checkOut(Checkin checkin, LocalDateTime when);

    /**
     * Creates or updates all checkins.
     * @param checkins - ids corresponding to values, to be created or updated.
     */
    public void addcheckins(Set<Checkin> checkins);

    /**
     * Fetches a Set of all checkins.
     * @return - Set of all checkins.
     */
    public Set<Checkin> getAll();

    /**
     * Finds checkin by id.
     * @param id 
     * @return - Checkin with id.
     */
    public Checkin findById(long id);

    /**
     * Creates or updates checkin.
     * @param checkin - id and values to be created or updated.
     */
    public void update(Checkin checkin);

    /**
     * Creates or updates checkin.
     * @param checkin - id and values to be created or updated.
     */
    public void add(Checkin checkin);

    /**
     * Gets checkins of the day method called
     * @return checkins.
     */
    public Set<Checkin> getTodaysCheckins();
    
    /**
     * Approves multiple checkins
     * @param checkins manager
     */
	public void approveMultiple(Set<Checkin> checkins);
  public boolean hasCheckedInOnDate(Associate associate, LocalDateTime date);

}
