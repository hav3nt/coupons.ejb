package com.mdbs;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.beans.Income;
import com.beans.IncomeDAO;
/**
 * Message Driven Bean to un-synchronously Income storage
 * (it is just for experimenting, as real world income storage should be synchronized).
 * 2016.02.01 version date
 * */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExpiryQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class IncomeMDB implements MessageListener {

	@EJB
	private IncomeDAO stub;

	public IncomeMDB() {
	}

	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				Object object = objectMessage.getObject();
				if (object instanceof Income) {
					Income income = (Income) objectMessage.getObject();
					stub.storeIncome(income);
				}else {
					throw new JMSException("The Object in the Message is not of type Income");
				}
			} else {
				throw new JMSException("Bad parametere: the Message is not of type ObjectMessage");

			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
