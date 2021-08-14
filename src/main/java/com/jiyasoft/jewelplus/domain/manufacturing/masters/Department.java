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
import javax.validation.constraints.Size;

@Entity
@Table(name = "departmentmast")
public class Department {

	@Id
	@GeneratedValue
	@Column(name = "DeptId")
	private Integer id;

	@Size(min = 3, message = "Department Name must be atleast 3 characters")
	@Column(name = "DeptNm")
	private String name;

	@Column(name = "Wip")
	private Boolean wip;

	@Column(name = "Inv")
	private Boolean inv;

	@Column(name = "Costing")
	private Boolean costing;
	
	@Column(name = "NonEditable")
	private Boolean nonEditable =false;
	
	@Column(name = "VouTypeFlg")
	private Boolean vouTypeFlg =false;
	
	@Column(name = "CastBal")
	private Boolean castBal =false;

	@Column(name = "DiaIssueFlag")
	private Boolean diaIssueFlag;
	
	@Column(name = "extraWt")
	private Double extraWt = 0.0;

	@Column(name = "LossPerc")
	private Double lossPerc = 0.0;

	@Column(name = "StoneProd")
	private Boolean stoneProd;
	
	@Column(name = "LossBookDept")
	private Boolean lossBookDept =false;

	@Column(name = "LooseMetalStk")
	private Boolean looseMetalStk;

	@Column(name = "StoneStk")
	private Boolean stoneStk;

	@Column(name = "ComponentStk")
	private Boolean componentStk;

	@Column(name = "ExpectedRecovery")
	private Double expectedRecovery;

	@Column(name = "Process")
	private String process;

	@Column(name = "PcsProd")
	private Boolean pcsProd;

	@Column(name = "AllowZeroWt")
	private Boolean allowZeroWt =false;

	@Column(name = "Code")
	private String code;
	
	@Column(name = "ProcessSeq")
	private Integer processSeq;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="BranchId")
	private BranchMaster branchMaster;
	
	@Column(name = "BarcodeFlg")
	private Boolean barcodeFlg =false;



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

	public Boolean getWip() {
		return wip;
	}

	public void setWip(Boolean wip) {
		this.wip = wip;
	}

	public Boolean getInv() {
		return inv;
	}

	public void setInv(Boolean inv) {
		this.inv = inv;
	}

	public Boolean getCosting() {
		return costing;
	}

	public void setCosting(Boolean costing) {
		this.costing = costing;
	}

	public Boolean getDiaIssueFlag() {
		return diaIssueFlag;
	}

	public void setDiaIssueFlag(Boolean diaIssueFlag) {
		this.diaIssueFlag = diaIssueFlag;
	}

	public Double getLossPerc() {
		return lossPerc;
	}

	public void setLossPerc(Double lossPerc) {
		this.lossPerc = lossPerc;
	}

	public Boolean getStoneProd() {
		return stoneProd;
	}

	public void setStoneProd(Boolean stoneProd) {
		this.stoneProd = stoneProd;
	}

	public Boolean getLooseMetalStk() {
		return looseMetalStk;
	}

	public void setLooseMetalStk(Boolean looseMetalStk) {
		this.looseMetalStk = looseMetalStk;
	}

	public Double getExpectedRecovery() {
		return expectedRecovery;
	}

	public void setExpectedRecovery(Double expectedRecovery) {
		this.expectedRecovery = expectedRecovery;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Boolean getPcsProd() {
		return pcsProd;
	}

	public void setPcsProd(Boolean pcsProd) {
		this.pcsProd = pcsProd;
	}

	public Boolean getAllowZeroWt() {
		return allowZeroWt;
	}

	public void setAllowZeroWt(Boolean allowZeroWt) {
		this.allowZeroWt = allowZeroWt;
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

	public Boolean getStoneStk() {
		return stoneStk;
	}

	public void setStoneStk(Boolean stoneStk) {
		this.stoneStk = stoneStk;
	}

	public Boolean getComponentStk() {
		return componentStk;
	}

	public void setComponentStk(Boolean componentStk) {
		this.componentStk = componentStk;
	}

	public Boolean getNonEditable() {
		return nonEditable;
	}

	public void setNonEditable(Boolean nonEditable) {
		this.nonEditable = nonEditable;
	}

	public Integer getProcessSeq() {
		return processSeq;
	}

	public void setProcessSeq(Integer processSeq) {
		this.processSeq = processSeq;
	}

	public Boolean getLossBookDept() {
		return lossBookDept;
	}

	public void setLossBookDept(Boolean lossBookDept) {
		this.lossBookDept = lossBookDept;
	}

	public Boolean getCastBal() {
		return castBal;
	}

	public void setCastBal(Boolean castBal) {
		this.castBal = castBal;
	}

	public Double getExtraWt() {
		return extraWt;
	}

	public void setExtraWt(Double extraWt) {
		this.extraWt = extraWt;
	}

	public BranchMaster getBranchMaster() {
		return branchMaster;
	}

	public void setBranchMaster(BranchMaster branchMaster) {
		this.branchMaster = branchMaster;
	}

	public Boolean getVouTypeFlg() {
		return vouTypeFlg;
	}

	public void setVouTypeFlg(Boolean vouTypeFlg) {
		this.vouTypeFlg = vouTypeFlg;
	}

	public Boolean getBarcodeFlg() {
		return barcodeFlg;
	}

	public void setBarcodeFlg(Boolean barcodeFlg) {
		this.barcodeFlg = barcodeFlg;
	}
	
	


}
