package com.actionbazaar.buslogic.api;

import javax.ejb.Local;

import com.actionbazaar.persistence.Bidder;
import com.actionbazaar.persistence.User;

@Local
public interface UserService {
	User getUser(long id);
	void addUser(User user);
}
