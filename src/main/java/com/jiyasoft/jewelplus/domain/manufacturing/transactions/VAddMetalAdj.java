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
@Table(name = "vaddmetaladj")
public class VAddMetalAdj {

	@Id
	@GeneratedValue
	@Column(name = "VaddMetalAdjId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CostMtId")
	private CostingMt costingMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VAddMetalInvId")
	private VAddMetalInv vAddMetalInv;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtlRecMtId")
	private MetalPurchaseMt metalPurchaseMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtlRecDtId")
	private MetalPurchaseDt metalPurchaseDt;

	@Column(name = "MetalInvNo")
	private String metalInvNo;

	@Column(name = "MetalInvDate")
	private Date metalInvDate;

	@Column(name = "AdjustmentWt")
	private Double adjustmentWt;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	public String getInvDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(metalInvDate);
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

	public VAddMetalInv getvAddMetalInv() {
		return vAddMetalInv;
	}

	public void setvAddMetalInv(VAddMetalInv vAddMetalInv) {
		this.vAddMetalInv = vAddMetalInv;
	}

	

	public MetalPurchaseMt getMetalPurchaseMt() {
		return metalPurchaseMt;
	}

	public void setMetalPurchaseMt(MetalPurchaseMt metalPurchaseMt) {
		this.metalPurchaseMt = metalPurchaseMt;
	}

	public MetalPurchaseDt getMetalPurchaseDt() {
		return metalPurchaseDt;
	}

	public void setMetalPurchaseDt(MetalPurchaseDt metalPurchaseDt) {
		this.metalPurchaseDt = metalPurchaseDt;
	}

	public String getMetalInvNo() {
		return metalInvNo;
	}

	public void setMetalInvNo(String metalInvNo) {
		this.metalInvNo = metalInvNo;
	}

	public Date getMetalInvDate() {
		return metalInvDate;
	}

	public void setMetalInvDate(Date metalInvDate) {
		this.metalInvDate = metalInvDate;
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


}
