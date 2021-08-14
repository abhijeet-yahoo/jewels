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
@Table(name ="branchmaster")
public class BranchMaster {

	@Id
	@GeneratedValue
	@Column(name = "BranchId")
	private Integer id;
	
	@Column(name = "BranchName")
	private String name;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "CreatedBy" , updatable = false)
	private String createdBy;
	
	@Column(name = "CreatedDt" , updatable = false)
	private Date createdDate;
	
	@Column(name = "ModiBy" , insertable = false)
	private String modiBy;
	
	@Column(name = "ModiDt" , insertable = false)
	private Date modiDate;
	
	@Column(name = "Deactive")
	private boolean deactive =false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
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

	@Column(name = "Gst")
	private String gst;

	@Column(name = "PanNo")
	private String panNo;
	
	@Column(name = "FaxNo")
	private String faxNo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CountryId") private CountryMaster country;
	  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="StateId") private StateMaster stateMast;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public boolean isDeactive() {
		return deactive;
	}

	public void setDeactive(boolean deactive) {
		this.deactive = deactive;
	}

	public Date getDeactiveDt() {
		return deactiveDt;
	}

	public void setDeactiveDt(Date deactiveDt) {
		this.deactiveDt = deactiveDt;
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

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
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

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	
	
}
