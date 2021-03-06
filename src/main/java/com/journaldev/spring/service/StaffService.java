package com.journaldev.spring.service;

import java.util.List;

import com.journaldev.spring.model.Staff;

public interface StaffService {

	public void addStaff(Staff u);
	public void updateStaff(Staff u);
	public List<Staff> listStaff();
	public Staff getStaffById(int id);
	public void removeStaff(int id);
	
}
