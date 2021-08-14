package com.jiyasoft.jewelplus.domain.manufacturing.transactions;


import java.text.SimpleDateFormat;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HSNMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

@Entity
@Table(name ="repairretmt")
public class RepairRetMt {
	
	@Id
	@GeneratedValue
	@Column(name ="MtId")
	private Integer id;
	
	@Column(name ="FinYear")
	private String finYear;
	
	@Column(name = "InvNo")
	private String invNo;

	@Column(name = "InvDate")
	private Date invDate;
	
	@Column(name ="Priority")
	private String priority;
	
	@Column(name ="RLock")
	private Boolean rLock = false;
	
	@Column(name ="RefNo")
	private String refNo;
	
	@Column(name = "Remark")
	private String remark;
	
	@Column(name ="SrNo")
	private Integer srNo;

	@Column(name = "VouType")
	private Integer vouType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="PartyId")
	private Party party;

	@Column(name ="NameOfMode")
	private String nameOfMode;
	

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;	

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="LocationId")
	private Department location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="hsnId")
	private HSNMast hsnMast;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ModeOfTransport", referencedColumnName = "LookupId")
	private LookUpMast modeOfTransport;
	
	
	public String getInvDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(invDate);
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFinYear() {
		return finYear;
	}


	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}


	public String getInvNo() {
		return invNo;
	}


	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}


	public Date getInvDate() {
		return invDate;
	}


	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public Boolean getrLock() {
		return rLock;
	}


	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}


	public String getRefNo() {
		return refNo;
	}


	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getSrNo() {
		return srNo;
	}


	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}


	public Integer getVouType() {
		return vouType;
	}


	public void setVouType(Integer vouType) {
		this.vouType = vouType;
	}


	public Party getParty() {
		return party;
	}


	public void setParty(Party party) {
		this.party = party;
	}


	public String getNameOfMode() {
		return nameOfMode;
	}


	public void setNameOfMode(String nameOfMode) {
		this.nameOfMode = nameOfMode;
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


	public Date getModiDate() {
		return modiDate;
	}


	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}


	public Department getLocation() {
		return location;
	}


	public void setLocation(Department location) {
		this.location = location;
	}


	public HSNMast getHsnMast() {
		return hsnMast;
	}


	public void setHsnMast(HSNMast hsnMast) {
		this.hsnMast = hsnMast;
	}


	public LookUpMast getModeOfTransport() {
		return modeOfTransport;
	}


	public void setModeOfTransport(LookUpMast modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	
	
	
	
}
