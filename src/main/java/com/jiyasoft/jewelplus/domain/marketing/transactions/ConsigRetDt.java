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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;

@Entity
@Table(name ="consigretdt")
public class ConsigRetDt {

	@Id
	@GeneratedValue
	@Column(name ="DtId")
	private Integer id; 
	

	@Column(name = "Barcode")
	private String barcode;
	
	@Column(name ="CompValue")
	private Double compValue = 0.0;
	
	@Column(name ="Destination")
	private String destination;
	
	@Column(name ="DiscAmount")
	private Double discAmount = 0.0;
	
	@Column(name ="DiscPerc")
	private Double discPerc = 0.0;
	
	@Column(name ="FinalPrice")
	private Double finalPrice = 0.0;
	
	@Column(name ="GrossWt")
	private Double grossWt = 0.0;
	
	@Column(name ="HandlingValue")
	private Double handlingValue = 0.0;
	
	@Column(name ="LabValue")
	private Double labValue = 0.0;
	
	@Column(name ="LossValue")
	private Double lossValue = 0.0;
	
	@Column(name="LossWt")
	private Double lossWt = 0.0;
	
	
	@Column(name ="MetalValue")
	private Double metalValue = 0.0;

	@Column(name ="NetAmount")
	private Double netAmount = 0.0;
	
	@Column(name ="NetWt")
	private Double netWt = 0.0;
	
	@Column(name ="OrderRef")
	private String orderRef;
	
	@Column(name ="Other")
	private Double other = 0.0;
	
	@Column(name = "Pcs")
	private Double pcs = 0.0;
	
	@Column(name = "AdjQty")
	private Double adjQty = 0.0;
		
	@Column(name ="RLock")
	private Boolean rLock = false;
	
	@Column(name ="Remark")
	private String remark;
	
	@Column(name ="SetValue")
	private Double setValue = 0.0;
	
	@Column(name ="StoneValue")
	private Double stoneValue = 0.0;
	
	@Column(name ="TagPrice")
	private Double tagPrice = 0.0;
	
	@Column(name = "Fob")
	private Double fob = 0.0;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="StyleId")
	private Design design ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="MtId")
	private ConsigRetMt consigRetMt;
	

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;	

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;
	
	@Column(name ="RefTranType")
	private String refTranType;
	
	@Column(name ="RefConsigDtId")
	private Integer refConsigDtId;
	
	@Column(name ="HallMarking")
	private Double hallMarking = 0.0;
	
	@Column(name ="LazerMarking")
	private Double lazerMarking = 0.0;
	
	@Column(name ="Grading")
	private Double grading = 0.0;
	

	@Column(name ="Engraving")
	private Double engraving = 0.0;

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

	public Double getCompValue() {
		return compValue;
	}

	public void setCompValue(Double compValue) {
		this.compValue = compValue;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getDiscAmount() {
		return discAmount;
	}

	public void setDiscAmount(Double discAmount) {
		this.discAmount = discAmount;
	}

	public Double getDiscPerc() {
		return discPerc;
	}

	public void setDiscPerc(Double discPerc) {
		this.discPerc = discPerc;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Double getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(Double grossWt) {
		this.grossWt = grossWt;
	}

	public Double getHandlingValue() {
		return handlingValue;
	}

	public void setHandlingValue(Double handlingValue) {
		this.handlingValue = handlingValue;
	}

	public Double getLabValue() {
		return labValue;
	}

	public void setLabValue(Double labValue) {
		this.labValue = labValue;
	}

	public Double getLossValue() {
		return lossValue;
	}

	public void setLossValue(Double lossValue) {
		this.lossValue = lossValue;
	}

	public Double getLossWt() {
		return lossWt;
	}

	public void setLossWt(Double lossWt) {
		this.lossWt = lossWt;
	}

	public Double getMetalValue() {
		return metalValue;
	}

	public void setMetalValue(Double metalValue) {
		this.metalValue = metalValue;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getNetWt() {
		return netWt;
	}

	public void setNetWt(Double netWt) {
		this.netWt = netWt;
	}

	public String getOrderRef() {
		return orderRef;
	}

	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public Double getPcs() {
		return pcs;
	}

	public void setPcs(Double pcs) {
		this.pcs = pcs;
	}

	public Double getAdjQty() {
		return adjQty;
	}

	public void setAdjQty(Double adjQty) {
		this.adjQty = adjQty;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getSetValue() {
		return setValue;
	}

	public void setSetValue(Double setValue) {
		this.setValue = setValue;
	}

	public Double getStoneValue() {
		return stoneValue;
	}

	public void setStoneValue(Double stoneValue) {
		this.stoneValue = stoneValue;
	}

	public Double getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(Double tagPrice) {
		this.tagPrice = tagPrice;
	}

	public Double getFob() {
		return fob;
	}

	public void setFob(Double fob) {
		this.fob = fob;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public ConsigRetMt getConsigRetMt() {
		return consigRetMt;
	}

	public void setConsigRetMt(ConsigRetMt consigRetMt) {
		this.consigRetMt = consigRetMt;
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


	public String getRefTranType() {
		return refTranType;
	}

	public void setRefTranType(String refTranType) {
		this.refTranType = refTranType;
	}

	public Integer getRefConsigDtId() {
		return refConsigDtId;
	}

	public void setRefConsigDtId(Integer refConsigDtId) {
		this.refConsigDtId = refConsigDtId;
	}

	public Double getHallMarking() {
		return hallMarking;
	}

	public void setHallMarking(Double hallMarking) {
		this.hallMarking = hallMarking;
	}

	public Double getLazerMarking() {
		return lazerMarking;
	}

	public void setLazerMarking(Double lazerMarking) {
		this.lazerMarking = lazerMarking;
	}

	public Double getGrading() {
		return grading;
	}

	public void setGrading(Double grading) {
		this.grading = grading;
	}

	public Double getEngraving() {
		return engraving;
	}

	public void setEngraving(Double engraving) {
		this.engraving = engraving;
	}
	
	
	
	
}
