package com.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.helpers.OperationEnum;
/**
 * Stateless session Bean to store and get Income info from the DB.
 * 2016.02.01 version date
 * */
@Stateless
//@DeclareRoles({"ADMIN","COMPANY","CUSTOMER"})
//@DenyAll
public class IncomeDAOBean implements IncomeDAO {
	@PersistenceContext(unitName="coupons")
	private EntityManager entityManager;
	
	@Override
	public Income storeIncome(Income income) {
		entityManager.persist(income);
		return income;
	}

//	@RolesAllowed({"ADMIN"})
	@Override
	public List<Income> getAll() {
		TypedQuery<Income> query=entityManager.createNamedQuery("allIncome", Income.class);
		return query.getResultList();
	}

//	@RolesAllowed({"ADMIN"})
	@Override
	public List<Income> getAllCustomerIncome(long cuctomerID) {
		TypedQuery<Income> query=entityManager.createNamedQuery("incomeByCustomer", Income.class);
		query=query.setParameter("userID", cuctomerID);
		query=query.setParameter("description",OperationEnum.CUSTOMER_PURCHASE);
		return query.getResultList();
	}

//	@RolesAllowed({"COMPANY"})
	@Override
	public List<Income> getAllCompanyIncome(long companyID) {
		TypedQuery<Income> query=entityManager.createNamedQuery("incomeByCompany", Income.class);
		query=query.setParameter("userID", companyID);
		query=query.setParameter("description",OperationEnum.CUSTOMER_PURCHASE);
		return query.getResultList();
	}

}
