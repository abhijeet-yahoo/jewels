package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

@Entity
@Table(name ="vouissmt")
public class VouIssMt {
	@Id
	@GeneratedValue
	@Column(name ="MtId")
	private Integer id;
	
	@Column(name ="FinYear")
	private String finYear;
	
	@Column(name ="InvDate")
	private Date invDate;
	
	@Column(name="InvNo")
	private String invNo ;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ModeOfDispatch", referencedColumnName = "LookupId")
	private LookUpMast modeOfDispatch;
	
	@Column(name ="NameOfMode")
	private String nameOfMode;
	
	@Column(name ="InsuranceAmount")
	private Double insuranceAmount = 0.0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="InsuredBy", referencedColumnName = "LookupId")
	private LookUpMast insuredBy;
	
	@Column(name ="MemoValidFor")
	private String memoValidFor;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;	

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	public Integer getMtId() {
		return id;
	}

	public void setMtId(Integer mtId) {
		this.id = mtId;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
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

	public LookUpMast getModeOfDispatch() {
		return modeOfDispatch;
	}

	public void setModeOfDispatch(LookUpMast modeOfDispatch) {
		this.modeOfDispatch = modeOfDispatch;
	}

	public String getNameOfMode() {
		return nameOfMode;
	}

	public void setNameOfMode(String nameOfMode) {
		this.nameOfMode = nameOfMode;
	}

	public Double getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(Double insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public LookUpMast getInsuredBy() {
		return insuredBy;
	}

	public void setInsuredBy(LookUpMast insuredBy) {
		this.insuredBy = insuredBy;
	}

	public String getMemoValidFor() {
		return memoValidFor;
	}

	public void setMemoValidFor(String memoValidFor) {
		this.memoValidFor = memoValidFor;
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

	public Boolean getDeactive() {
		return deactive;
	}

	public void setDeactive(Boolean deactive) {
		this.deactive = deactive;
	}

	public Date getDeactiveDt() {
		return deactiveDt;
	}

	public void setDeactiveDt(Date deactiveDt) {
		this.deactiveDt = deactiveDt;
	}

	/*
	 * public String getInvDateStr() { SimpleDateFormat dateFormat = new
	 * SimpleDateFormat("dd/MM/yyyy"); return dateFormat.format(invDate); }
	 */
	

	
	
}
