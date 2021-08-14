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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "designcost")
public class DesignCost {

	@Id
	@GeneratedValue
	@Column(name = "DesignCostId")
	private Integer id;

	@Column(name = "FinalPrice")
	private Double finalPrice = 0.0;

	@Column(name = "Fob")
	private Double fob = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StyleId")
	private Design design;

	@Column(name = "HandlingValue")
	private Double handlingValue = 0.0;

	@Column(name = "LabourValue")
	private Double labourValue = 0.0;

	@Column(name = "LossValue")
	private Double LossValue = 0.0;

	@Column(name = "LossWeight")
	private Double lossWeight = 0.0;

	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "MetalValue")
	private Double metalValue = 0.0;

	@Column(name = "PerGramRate")
	private Double perGramRate = 0.0;

	@Column(name = "SetValue")
	private Double setValue = 0.0;

	@Column(name = "StnValue")
	private Double stnValue = 0.0;

	@Column(name = "CompValue")
	private Double compValue = 0.0;
	
	@Column(name = "AddedPerc")
	private Double addedPerc = 0.0;
	
	@Column(name = "TagPerc")
	private Double tagPerc = 0.0;
	
	@Column(name = "DispPerc")
	private Double dispPerc = 0.0;
	
	@Column(name = "HandlingPerc")
	private Double handlingPerc = 0.0;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "CreatedDt", updatable = false)
	private Date createDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Double getFob() {
		return fob;
	}

	public void setFob(Double fob) {
		this.fob = fob;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public Double getHandlingValue() {
		return handlingValue;
	}

	public void setHandlingValue(Double handlingValue) {
		this.handlingValue = handlingValue;
	}

	public Double getLabourValue() {
		return labourValue;
	}

	public void setLabourValue(Double labourValue) {
		this.labourValue = labourValue;
	}

	public Double getLossValue() {
		return LossValue;
	}

	public void setLossValue(Double lossValue) {
		LossValue = lossValue;
	}

	public Double getLossWeight() {
		return lossWeight;
	}

	public void setLossWeight(Double lossWeight) {
		this.lossWeight = lossWeight;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public Double getMetalValue() {
		return metalValue;
	}

	public void setMetalValue(Double metalValue) {
		this.metalValue = metalValue;
	}

	public Double getPerGramRate() {
		return perGramRate;
	}

	public void setPerGramRate(Double perGramRate) {
		this.perGramRate = perGramRate;
	}

	public Double getSetValue() {
		return setValue;
	}

	public void setSetValue(Double setValue) {
		this.setValue = setValue;
	}

	public Double getStnValue() {
		return stnValue;
	}

	public void setStnValue(Double stnValue) {
		this.stnValue = stnValue;
	}

	public Double getCompValue() {
		return compValue;
	}

	public void setCompValue(Double compValue) {
		this.compValue = compValue;
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

	public Double getAddedPerc() {
		return addedPerc;
	}

	public void setAddedPerc(Double addedPerc) {
		this.addedPerc = addedPerc;
	}

	public Double getTagPerc() {
		return tagPerc;
	}

	public void setTagPerc(Double tagPerc) {
		this.tagPerc = tagPerc;
	}

	public Double getDispPerc() {
		return dispPerc;
	}

	public void setDispPerc(Double dispPerc) {
		this.dispPerc = dispPerc;
	}

	public Double getHandlingPerc() {
		return handlingPerc;
	}

	public void setHandlingPerc(Double handlingPerc) {
		this.handlingPerc = handlingPerc;
	}
	
	

}
