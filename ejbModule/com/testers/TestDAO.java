package com.testers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.beans.Income;
import com.beans.IncomeDAO;
import com.helpers.OperationEnum;
/**
 * Stateless WebService to publish SOAP endpoints for clients to manipulate Income data in the DB and get SOAP response.
 * 2016.02.01 version date
 * */
@Stateless
@WebService
public class TestDAO {

	@EJB
	private IncomeDAO daoStub;

	@WebMethod
	public Income storeIncome(@WebParam(name="invokerID") String invokerID,@WebParam(name="timestamp") String timestamp,@WebParam(name="amount") String amount,@WebParam(name="description") String description){
		String expectedPattern = "yyyy-MM-dd";
	    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		try {
			Income income=new Income(null, Long.parseLong(invokerID), formatter.parse(timestamp), Double.parseDouble(amount), OperationEnum.valueOf(description));
			daoStub.storeIncome(income);
			return income;
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Income[] getAll() {
		return daoStub.getAll().toArray(new Income[0]);
	}

	public Income[] getByCustomer(@WebParam(name="customerId") long customerId) {
		return daoStub.getAllCustomerIncome(customerId).toArray(new Income[0]);
	}

	public Income[] getByCompany(@WebParam(name="companyId") long companyId) {
		return daoStub.getAllCompanyIncome(companyId).toArray(new Income[0]);
	}

}
