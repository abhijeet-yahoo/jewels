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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name ="repairretcompdt")
public class RepairRetCompDt {
	
	@Id
	@GeneratedValue
	@Column(name ="CompId")
	private Integer id;
	
	@Column(name ="CompWt")
	private Double compWt = 0.0;

	@Column(name = "CompQty")
	private Double compQty = 0.0;
	
	@Column(name ="CompRate")
	private Double compRate = 0.0;
	
	@Column(name ="CompValue")
	private Double compValue = 0.0;
	
	@Column(name = "LossPerc")
	private Double lossPerc = 0.0;
	
	@Column(name ="MetalRate")
	private Double metalRate = 0.0;
	
	@Column(name ="MetalValue")
	private Double metalValue = 0.0;
	
	@Column(name ="PerGramRate")
	private Boolean perGramRate = false;

	@Column(name ="PerPcRate")
	private Boolean perPcRate = false;
	
	@Column(name ="PurityConv")
	private Double purityConv = 0.0;
	
	@Column(name ="RLock")
	private Boolean rLock = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ColorId")
	private Color color;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ComponentId")
	private Component component;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DtId")
	private RepairRetDt repairRetDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private RepairRetMt repairRetMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="PurityId")
	private Purity purity;
	

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;	

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

	@Column(name = "PerGramMetalRate")
	private Double perGramMetalRate = 0.0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCompWt() {
		return compWt;
	}

	public void setCompWt(Double compWt) {
		this.compWt = compWt;
	}

	public Double getCompQty() {
		return compQty;
	}

	public void setCompQty(Double compQty) {
		this.compQty = compQty;
	}

	public Double getCompRate() {
		return compRate;
	}

	public void setCompRate(Double compRate) {
		this.compRate = compRate;
	}

	public Double getCompValue() {
		return compValue;
	}

	public void setCompValue(Double compValue) {
		this.compValue = compValue;
	}

	public Double getLossPerc() {
		return lossPerc;
	}

	public void setLossPerc(Double lossPerc) {
		this.lossPerc = lossPerc;
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

	public Boolean getPerGramRate() {
		return perGramRate;
	}

	public void setPerGramRate(Boolean perGramRate) {
		this.perGramRate = perGramRate;
	}

	public Boolean getPerPcRate() {
		return perPcRate;
	}

	public void setPerPcRate(Boolean perPcRate) {
		this.perPcRate = perPcRate;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public RepairRetDt getRepairRetDt() {
		return repairRetDt;
	}

	public void setRepairRetDt(RepairRetDt repairRetDt) {
		this.repairRetDt = repairRetDt;
	}

	public RepairRetMt getRepairRetMt() {
		return repairRetMt;
	}

	public void setRepairRetMt(RepairRetMt repairRetMt) {
		this.repairRetMt = repairRetMt;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
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

	public Double getPerGramMetalRate() {
		return perGramMetalRate;
	}

	public void setPerGramMetalRate(Double perGramMetalRate) {
		this.perGramMetalRate = perGramMetalRate;
	}
	
	
	
	
	
	

}
