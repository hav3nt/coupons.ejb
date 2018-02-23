package com.beans;

import java.util.List;

import javax.ejb.Local;

@Local
public interface IncomeDAO {
	public Income storeIncome (Income income);
	public List<Income> getAll();
	public List<Income> getAllCustomerIncome(long cuctomerID);
	public List<Income> getAllCompanyIncome(long companyID);
	
}
