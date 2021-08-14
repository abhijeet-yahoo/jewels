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
@Table(name = "stockcompdt")
public class StockCompDt {

	@Id
	@GeneratedValue
	@Column(name ="StkCompId")
	private Integer stkCompId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private StockMt stockMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="PurityId")
	private Purity purity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ColorId")
	private Color color;
	
	@Column(name ="PurityConv")
	private Double purityConv = 0.0;
	
	@Column(name ="CompWt")
	private Double compWt = 0.0;
	
	@Column(name = "CompQty")
	private Double compQty = 0.0;
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;
	
	@Column(name = "MetalValue")
	private Double metalValue = 0.0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ComponentId")
	private Component component;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

	@Column(name = "Deactive")
	private boolean deactive =false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name ="RefStkCompId")
	private Integer refStkCompId;

	public Integer getStkCompId() {
		return stkCompId;
	}

	public void setStkCompId(Integer stkCompId) {
		this.stkCompId = stkCompId;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
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

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
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

	public Integer getRefStkCompId() {
		return refStkCompId;
	}

	public void setRefStkCompId(Integer refStkCompId) {
		this.refStkCompId = refStkCompId;
	}

	public StockMt getStockMt() {
		return stockMt;
	}

	public void setStockMt(StockMt stockMt) {
		this.stockMt = stockMt;
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
	
	
	
	
}
