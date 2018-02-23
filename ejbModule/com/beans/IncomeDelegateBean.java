package com.beans;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 * Stateless Session Bean which Queues an Income storage in the DB using DAO.
 * It also fetches the info back with Income info from DB.
 * 2016.02.01 version date
 * */
@Stateless
public class IncomeDelegateBean implements IncomeDelegate {
	private QueueConnectionFactory connectionFactory;
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue queue;
	private QueueSender queueSender;
	private ObjectMessage objectMessage;
	@EJB
	private IncomeDAO stub;

	@PostConstruct
	public void init() {
		try {
			InitialContext initContext = new InitialContext();
			connectionFactory = (QueueConnectionFactory) initContext.lookup("java:/ConnectionFactory");

			queueConnection = (QueueConnection) connectionFactory.createConnection();

			queueSession = queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

			queue = (Queue) initContext.lookup("java:/jms/queue/ExpiryQueue");

			queueSender = queueSession.createSender(queue);

			objectMessage = queueSession.createObjectMessage();
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void storeIncome(Income income) {
		System.out.println("IncomeDelegateBean.storeIncome()");
		try {
			objectMessage.setObject(income);
			queueSender.send(objectMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void destroy() {
		
		try {
			queueSender.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		try {
			queueSession.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		try {
			queueConnection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Income> viewAllIncome() {
		return this.stub.getAll();
	}

	@Override
	public Collection<Income> viewIncomeByCustomer(long cuctomerID) {
		return this.stub.getAllCustomerIncome(cuctomerID);
	}

	@Override
	public Collection<Income> viewIncomeByCompany(long companyID) {
		return this.stub.getAllCompanyIncome(companyID);
	}

}
