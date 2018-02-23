package com.beans;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface IncomeDelegate {

	public void storeIncome(Income income);
	public Collection<Income> viewAllIncome();
	public Collection<Income> viewIncomeByCustomer(long cuctomerID);
	public Collection<Income> viewIncomeByCompany(long companyID);
}
