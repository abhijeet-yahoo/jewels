package com.jiyasoft.jewelplus.domain.marketing.transactions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;

@Entity
@Table(name="stocktran")
public class StockTran {
	@Id
	@GeneratedValue
	@Column(name ="StkTranId")
	private Integer id;
	
	
	@Column(name ="Barcode")
	private String barcode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationId")
	private Department location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartyId")
	private Party party;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EmployeeId")
	private Employee employee;
	
	@Column(name ="TranType")
	private String tranType;
	
	@Column(name ="RefTranId")
	private Integer refTranId;
	
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	
	@Column(name = "CurrStk")
	private Boolean currStk;
	
	@Column(name = "IssueDate")
	private Date issueDate;
	
	@Column(name ="TranDate")
	private Date tranDate;
	
	@Column(name ="RefStkTranId")
	private Integer refStkTranId;
	
	@Column(name = "Remark")
	private String remark;

	@Column(name ="CurrStatus")
	private String currStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Mtid")
	private StockMt stockMt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Department getLocation() {
		return location;
	}

	public void setLocation(Department location) {
		this.location = location;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public Integer getRefTranId() {
		return refTranId;
	}

	public void setRefTranId(Integer refTranId) {
		this.refTranId = refTranId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModiBy() {
		return modiBy;
	}

	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;
	}

	public Date getModiDt() {
		return modiDt;
	}

	public void setModiDt(Date modiDt) {
		this.modiDt = modiDt;
	}


	public Boolean getCurrStk() {
		return currStk;
	}

	public void setCurrStk(Boolean currStk) {
		this.currStk = currStk;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public Integer getRefStkTranId() {
		return refStkTranId;
	}

	public void setRefStkTranId(Integer refStkTranId) {
		this.refStkTranId = refStkTranId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCurrStatus() {
		return currStatus;
	}

	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public StockMt getStockMt() {
		return stockMt;
	}

	public void setStockMt(StockMt stockMt) {
		this.stockMt = stockMt;
	}
	
	

}
