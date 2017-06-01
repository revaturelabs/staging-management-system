package com.revature.services;

import com.revature.entities.Associate;
import com.revature.entities.Checkin;
import com.revature.entities.Checkin;
import com.revature.entities.Manager;
import com.revature.exceptions.AlreadyCheckedInException;
import com.revature.exceptions.AlreadyCheckedOutException;
import com.revature.exceptions.NotCheckedInException;

/**
 * Created by mnikitin on 6/1/17.
 */
public interface CheckinService {
    public void approveCheckin(Manager approvingManager, Checkin checkin);
    public boolean hasCheckedInToday(Associate associate);
    public void checkIn(Associate associate) throws AlreadyCheckedInException;
    public void checkOut(Associate associate) throws AlreadyCheckedOutException, NotCheckedInException;
}
