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

@Entity
@Table(name = "vaddcompadj")
public class VAddCompAdj {

	@Id
	@GeneratedValue
	@Column(name = "Vaddcompadj")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CostMtId")
	private CostingMt costingMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CompInvId")
	private VAddCompInv vAddCompInv;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CompPurcDtId")
	private ComponentPurchaseDt componentPurchaseDt;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CompRecMtId")
	private CompInwardMt compInwardMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CompRecDtId")
	private CompInwardDt compInwardDt;

	@Column(name = "CompInvNo")
	private String compInvNo;

	@Column(name = "CompInvDate")
	private Date compInvDate;

	@Column(name = "AdjustmentWt")
	private Double adjustmentWt;
	
	@Column(name = "AdjustmentPcs")
	private Double adjustmentPcs;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	public String getInvDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(compInvDate);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CostingMt getCostingMt() {
		return costingMt;
	}

	public void setCostingMt(CostingMt costingMt) {
		this.costingMt = costingMt;
	}

	public VAddCompInv getvAddCompInv() {
		return vAddCompInv;
	}

	public void setvAddCompInv(VAddCompInv vAddCompInv) {
		this.vAddCompInv = vAddCompInv;
	}

	public CompInwardMt getCompInwardMt() {
		return compInwardMt;
	}

	public void setCompInwardMt(CompInwardMt compInwardMt) {
		this.compInwardMt = compInwardMt;
	}

	public CompInwardDt getCompInwardDt() {
		return compInwardDt;
	}

	public void setCompInwardDt(CompInwardDt compInwardDt) {
		this.compInwardDt = compInwardDt;
	}

	public String getCompInvNo() {
		return compInvNo;
	}

	public void setCompInvNo(String compInvNo) {
		this.compInvNo = compInvNo;
	}

	public Date getCompInvDate() {
		return compInvDate;
	}

	public void setCompInvDate(Date compInvDate) {
		this.compInvDate = compInvDate;
	}

	public Double getAdjustmentWt() {
		return adjustmentWt;
	}

	public void setAdjustmentWt(Double adjustmentWt) {
		this.adjustmentWt = adjustmentWt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
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

	public ComponentPurchaseDt getComponentPurchaseDt() {
		return componentPurchaseDt;
	}

	public void setComponentPurchaseDt(ComponentPurchaseDt componentPurchaseDt) {
		this.componentPurchaseDt = componentPurchaseDt;
	}

	public Double getAdjustmentPcs() {
		return adjustmentPcs;
	}

	public void setAdjustmentPcs(Double adjustmentPcs) {
		this.adjustmentPcs = adjustmentPcs;
	}

	
}
