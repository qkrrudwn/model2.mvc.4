package com.model2.mvc.service.user;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;



public interface UserDao {
	
	// INSERT
	public void addUser(User user) throws Exception ;

	// SELECT ONE
	public User getUser(String userId) throws Exception ;

	// SELECT LIST
	public List<User> getUserList(Search search) throws Exception ;

	// UPDATE
	public void updateUser(User user) throws Exception ;
	
	
	public int getTotalCount(Search search) throws Exception ;
	
}