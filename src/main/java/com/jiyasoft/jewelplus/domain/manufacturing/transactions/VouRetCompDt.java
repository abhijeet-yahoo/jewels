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

@Entity
@Table(name ="vouretcompdt")
public class VouRetCompDt {

	@Id
	@GeneratedValue
	@Column(name ="VouRetCompId")
	private Integer id;
	
	
	@Column(name = "CompQty")
	private Double compQty = 0.0;
	
	@Column(name ="CompRate")
	private Double compRate = 0.0;
	
	@Column(name ="CompValue")
	private Double compValue = 0.0;
	
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
	@JoinColumn(name ="DtId")
	private VouRetDt vouRetDt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="MtId")
	private VouRetMt vouRetMt;
	
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



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public VouRetDt getVouRetDt() {
		return vouRetDt;
	}

	public void setVouRetDt(VouRetDt vouRetDt) {
		this.vouRetDt = vouRetDt;
	}

	public VouRetMt getVouRetMt() {
		return vouRetMt;
	}

	public void setVouRetMt(VouRetMt vouRetMt) {
		this.vouRetMt = vouRetMt;
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
	
	
	
	
	
	
	
}
