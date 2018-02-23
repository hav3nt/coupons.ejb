package com.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.helpers.OperationEnum;

import javax.persistence.Temporal;


/**
 * A class representing an Income info which will be stored or fetched from the DB
 * 2016.02.01 version date
 * */
@Entity
@NamedQueries({
	@NamedQuery(name="allIncome", query="SELECT i FROM Income AS i ORDER BY i.timeStamp DESC"),
	@NamedQuery(name="incomeByCustomer", query="SELECT i FROM Income AS i WHERE i.invokerID=:userID AND i.description=:description ORDER BY i.timeStamp DESC"),
	@NamedQuery(name="incomeByCompany", query="SELECT i FROM Income AS i WHERE i.invokerID=:userID AND (NOT i.description=:description) ORDER BY i.timeStamp DESC")
})
@XmlRootElement
public class Income implements Serializable {

	private static final long serialVersionUID = -4538741721025543055L;

	private Long id;
	
	private long invokerID;
	
	private Date timeStamp;
	
	private double amount;
	
	private OperationEnum description;

	public Income() {
	}
	
	public Income(Long id, long invokerID, Date timeStamp, double amount, OperationEnum description) {
		super();
		this.id = id;
		this.invokerID = invokerID;
		this.timeStamp = timeStamp;
		this.amount = amount;
		this.description = description;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="INVOKER_ID")
	public long getInvokerID() {
		return invokerID;
	}

	public void setInvokerID(long invokerID) {
		this.invokerID = invokerID;
	}

	@Column(name="TIME_STAMP")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name="OPERATION_TYPE")
	@Enumerated(EnumType.ORDINAL)
	public OperationEnum getDescription() {
		return description;
	}

	public void setDescription(OperationEnum description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Income [id=" + id + ", invokerID=" + invokerID + ", timeStamp=" + timeStamp + ", amount=" + amount
				+ ", description=" + description + "]";
	}
	
	
	
}
