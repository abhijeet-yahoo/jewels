package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "partymast")
public class Party {

	@Id
	@GeneratedValue
	@Column(name = "PartyId")
	private Integer id;

	@Column(name = "PartyNm")
	private String name;

	@Column(name = "PartyCode")
	private String partyCode;

	@Column(name = "Email")
	private String email;

	@Column(name = "Phone")
	private String phone;

	@OneToMany(mappedBy = "party")
	private List<Address> addressList;

	@Column(name = "CreditLimit")
	private Double creditLimit = 0.0;

	@Column(name = "Discount")
	private Double discount = 0.0;

	@Column(name = "DiscountPercent")
	private Double discountPercent = 0.0;

	@Column(name = "DefaultFlag")
	private Boolean defaultFlag = false;

	@Column(name = "ExportClient")
	private Boolean exportClient = false;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	

	@Column(name = "MailingName")
	private String mailingName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LedgerGroupId")
	private LedgerGroup ledgerGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SalesmanId")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Type")
	private LookUpMast type;
	
	@Column(name = "SrNo")
	private Integer srNo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CountryId") private CountryMaster country;
	  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="StateId") private StateMaster stateMast;
	 

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartyRegionId")
	private LookUpMast partyRegion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CustomerTypeId")
	private LookUpMast customerType;
	
	public String getCreditDays() {
		return creditDays;
	}

	public void setCreditDays(String creditDays) {
		this.creditDays = creditDays;
	}

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



	@Column(name = "NonEditable")
	private Boolean nonEditable = false;

	
	@Column(name = "ParentParty")
	private String parentParty;
	
	
	
	@Column(name = "DiaWtType")
	private String diaWtType;
	
	
	@Column(name = "DiaRateType")
	private String diaRateType;
	
	
	@Column(name = "LabRateType")
	private String labRateType;
	
	@Column(name = "MetalWtAddPerc")
	private Double metalWtAddPerc=0.0;
	
	@Column(name = "MetalWtType")
	private String metalWtType;
	
	@Column(name = "SetRateType")
	private String setRateType;
	
	@Column(name = "HdlgRateType")
	private String hdlgRateType;
	
	
	
	
	// getters and setters

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

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Boolean getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public Boolean getExportClient() {
		return exportClient;
	}

	public void setExportClient(Boolean exportClient) {
		this.exportClient = exportClient;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
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

	public LookUpMast getType() {
		return type;
	}

	public void setType(LookUpMast type) {
		this.type = type;
	}

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
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

	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
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

	public Boolean getNonEditable() {
		return nonEditable;
	}

	public void setNonEditable(Boolean nonEditable) {
		this.nonEditable = nonEditable;
	}

	public String getParentParty() {
		return parentParty;
	}

	public void setParentParty(String parentParty) {
		this.parentParty = parentParty;
	}

	public String getDiaWtType() {
		return diaWtType;
	}

	public void setDiaWtType(String diaWtType) {
		this.diaWtType = diaWtType;
	}

	public String getDiaRateType() {
		return diaRateType;
	}

	public void setDiaRateType(String diaRateType) {
		this.diaRateType = diaRateType;
	}

	public String getLabRateType() {
		return labRateType;
	}

	public void setLabRateType(String labRateType) {
		this.labRateType = labRateType;
	}

	public Double getMetalWtAddPerc() {
		return metalWtAddPerc;
	}

	public void setMetalWtAddPerc(Double metalWtAddPerc) {
		this.metalWtAddPerc = metalWtAddPerc;
	}

	public String getMetalWtType() {
		return metalWtType;
	}

	public void setMetalWtType(String metalWtType) {
		this.metalWtType = metalWtType;
	}

	public String getSetRateType() {
		return setRateType;
	}

	public void setSetRateType(String setRateType) {
		this.setRateType = setRateType;
	}

	public String getHdlgRateType() {
		return hdlgRateType;
	}

	public void setHdlgRateType(String hdlgRateType) {
		this.hdlgRateType = hdlgRateType;
	}

	public LookUpMast getPartyRegion() {
		return partyRegion;
	}

	public void setPartyRegion(LookUpMast partyRegion) {
		this.partyRegion = partyRegion;
	}

	public LookUpMast getCustomerType() {
		return customerType;
	}

	public void setCustomerType(LookUpMast customerType) {
		this.customerType = customerType;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

	
}
