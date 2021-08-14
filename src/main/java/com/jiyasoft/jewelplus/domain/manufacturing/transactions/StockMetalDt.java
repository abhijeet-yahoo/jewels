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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name ="stockmetaldt")
public class StockMetalDt {
	
	@Id
	@GeneratedValue
	@Column(name = "StkMetalId")
	private Integer stkMetalId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Mtid")
	private StockMt stockMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="PurityId")
	private Purity purity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color; 
	
	@Column(name ="PurityConv")
	private Double purityConv = 0.0;

	@Column(name = "MetalWt")
	private Double metalWt = 0.0;
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;
	
	@Column(name = "MetalValue")
	private Double metalValue = 0.0;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive=false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name = "RefStkMetalId")
	private Integer refStkMetalId;
	
	@Column(name = "MainMetal")
	private Boolean mainMetal = false;
	
	@Column(name = "MetalPcs")
	private Integer metalPcs = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;
	

	public Integer getStkMetalId() {
		return stkMetalId;
	}

	public void setStkMetalId(Integer stkMetalId) {
		this.stkMetalId = stkMetalId;
	}



	public StockMt getStockMt() {
		return stockMt;
	}

	public void setStockMt(StockMt stockMt) {
		this.stockMt = stockMt;
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

	public Double getMetalWt() {
		return metalWt;
	}

	public void setMetalWt(Double metalWt) {
		this.metalWt = metalWt;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
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

	public Integer getRefStkMetalId() {
		return refStkMetalId;
	}

	public void setRefStkMetalId(Integer refStkMetalId) {
		this.refStkMetalId = refStkMetalId;
	}

	public Boolean getMainMetal() {
		return mainMetal;
	}

	public void setMainMetal(Boolean mainMetal) {
		this.mainMetal = mainMetal;
	}

	public Integer getMetalPcs() {
		return metalPcs;
	}

	public void setMetalPcs(Integer metalPcs) {
		this.metalPcs = metalPcs;
	}

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public Double getMetalValue() {
		return metalValue;
	}

	public void setMetalValue(Double metalValue) {
		this.metalValue = metalValue;
	}

	

}
