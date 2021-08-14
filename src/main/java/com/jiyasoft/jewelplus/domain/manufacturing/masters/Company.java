package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companymast")
public class Company {
	
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;

	@Column(name = "CompanyName")
	private String name;

	@Column(name = "CompId")
	private Integer compId;

	@Column(name = "FinYear")
	private String finYear;

	@Column(name = "AddressOne")
	private String addressOne;

	@Column(name = "AddressTwo")
	private String addressTwo;

	@Column(name = "AddressThree")
	private String addressThree;

	@Column(name = "PhoneNo")
	private String phoneNo;

	@Column(name = "Email")
	private String email;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "PanNo")
	private String panNo;

	@Column(name = "GSTN")
	private String gstn;

	@Column(name = "SchemaNm")
	private String schemaNm;

	@Column(name = "Inventory")
	private Boolean inventory = false;

	@Column(name = "BankNm")
	private String bankNm;

	@Column(name = "BankAccountNo")
	private String bankAccountNo;

	@Column(name = "BankBranch")
	private String bankBranch;

	@Column(name = "IFSCCode")
	private String ifscCode;

	@Column(name = "SwiftCode")
	private String swiftCode;

	@Column(name = "Pincode")
	private String pincode;

	@Column(name = "State")
	private String state;

	@Column(name = "IsGroup")
	private Boolean isGroup = false;

	@Column(name = "ParentGroupId")
	private Integer partentGroupId;

	@Column(name = "RightClickFlg")
	private Boolean rightClickFlg = false;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

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

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getAddressThree() {
		return addressThree;
	}

	public void setAddressThree(String addressThree) {
		this.addressThree = addressThree;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getGstn() {
		return gstn;
	}

	public void setGstn(String gstn) {
		this.gstn = gstn;
	}

	public String getSchemaNm() {
		return schemaNm;
	}

	public void setSchemaNm(String schemaNm) {
		this.schemaNm = schemaNm;
	}

	public Boolean getInventory() {
		return inventory;
	}

	public void setInventory(Boolean inventory) {
		this.inventory = inventory;
	}

	public String getBankNm() {
		return bankNm;
	}

	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public Integer getPartentGroupId() {
		return partentGroupId;
	}

	public void setPartentGroupId(Integer partentGroupId) {
		this.partentGroupId = partentGroupId;
	}

	public Boolean getRightClickFlg() {
		return rightClickFlg;
	}

	public void setRightClickFlg(Boolean rightClickFlg) {
		this.rightClickFlg = rightClickFlg;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	

}
