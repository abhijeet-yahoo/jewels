package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ledgermast")
public class Ledger {

	// general Details

	@Id
	@GeneratedValue
	@Column(name = "LedgerId")
	private Integer id;

	@Column(name = "LedgerNm")
	private String name;
	
	@Column(name = "LedgerCode")
	private String code;

	@Column(name = "MailingName")
	private String mailingName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LedgerGroupId")
	private LedgerGroup ledgerGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Type")
	private LookUpMast type;
	
	@Column(name = "SrNo")
	private Integer srNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CountryId") 
	private CountryMaster country;
	  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="StateId") 
	private StateMaster stateMast;
	 

	@Column(name = "ContactPerson")
	private String contactPerson;

	@Column(name = "Address", columnDefinition = "TEXT")
	private String address;

	@Column(name = "Area")
	private String area;

	@Column(name = "Zone")
	private String zone;

	@Column(name = "City")
	private String city;

	@Column(name = "Zip")
	private String zip;



	@Column(name = "OfficePhone")
	private String officePhone;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "WebSite")
	private String webSite;

	@Column(name = "Mobile")
	private String mobile;

	@Column(name = "EmailAddress")
	private String emailAddress;

	@Column(name = "SalesMan")
	private String salesMan;


	@Column(name = "CreditDays")
	private String creditDays ;
	
	public String getCreditDays() {
		return creditDays;
	}

	public void setCreditDays(String creditDays) {
		this.creditDays = creditDays;
	}

	@Column(name = "CreditLimit")
	private Double creditLimit = 0.0;

	@Column(name = "RateOfInterest")
	private Double rateOfInterest = 0.0;

	@Column(name = "PerDays")
	private Integer perDays = 0;

	@Column(name = "Notes", columnDefinition = "TEXT")
	private String notes;
	
	//Transporter Details
	
	@Column(name = "Transporter")
	private String transporter;
	
	@Column(name = "TranBooking")
	private String tranBooking;
	
	@Column(name = "TranDest")
	private String tranDest;
	
	//Document Attachment
	
	@Column(name = "AttachedFileNm1")
	private String attachedFileNm1;
	
	@Column(name = "AttachedFileNm2")
	private String attachedFileNm2;
	
	@Column(name = "AttachedFileNm3")
	private String attachedFileNm3;
	
	@Column(name = "AdditionalData")
	private String additionalData;
	

	// Tax-details

	@Column(name = "PartyType")
	private String partyType;

	@Column(name = "Gst")
	private String gst;

	@Column(name = "Cgst")
	private String cgst;

	@Column(name = "Igst")
	private String igst;

	@Column(name = "PanNo")
	private String panNo;

	@Column(name = "TanNo")
	private String tanNo;

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

	@Column(name = "NonEditable")
	private Boolean nonEditable = false;

	// getters - setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailingName() {
		return mailingName;
	}

	public void setMailingName(String mailingName) {
		this.mailingName = mailingName;
	}

	public LedgerGroup getLedgerGroup() {
		return ledgerGroup;
	}

	public void setLedgerGroup(LedgerGroup ledgerGroup) {
		this.ledgerGroup = ledgerGroup;
	}


	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	


	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}



	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public Integer getPerDays() {
		return perDays;
	}

	public void setPerDays(Integer perDays) {
		this.perDays = perDays;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getTanNo() {
		return tanNo;
	}

	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
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


	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	/*public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}*/

	public Boolean getNonEditable() {
		return nonEditable;
	}

	public void setNonEditable(Boolean nonEditable) {
		this.nonEditable = nonEditable;
	}

	public String getTransporter() {
		return transporter;
	}

	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}

	public String getTranBooking() {
		return tranBooking;
	}

	public void setTranBooking(String tranBooking) {
		this.tranBooking = tranBooking;
	}

	public String getTranDest() {
		return tranDest;
	}

	public void setTranDest(String tranDest) {
		this.tranDest = tranDest;
	}

	public String getAttachedFileNm1() {
		return attachedFileNm1;
	}

	public void setAttachedFileNm1(String attachedFileNm1) {
		this.attachedFileNm1 = attachedFileNm1;
	}

	public String getAttachedFileNm2() {
		return attachedFileNm2;
	}

	public void setAttachedFileNm2(String attachedFileNm2) {
		this.attachedFileNm2 = attachedFileNm2;
	}

	public String getAttachedFileNm3() {
		return attachedFileNm3;
	}

	public void setAttachedFileNm3(String attachedFileNm3) {
		this.attachedFileNm3 = attachedFileNm3;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	public LookUpMast getType() {
		return type;
	}

	public void setType(LookUpMast type) {
		this.type = type;
	}

	public CountryMaster getCountry() {
		return country;
	}

	public void setCountry(CountryMaster country) {
		this.country = country;
	}

	public StateMaster getStateMast() {
		return stateMast;
	}

	public void setStateMast(StateMaster stateMast) {
		this.stateMast = stateMast;
	}


	
	

	
}
