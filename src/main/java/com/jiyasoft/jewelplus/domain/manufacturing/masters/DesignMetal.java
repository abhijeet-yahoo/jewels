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

@Entity
@Table(name = "designmetal")
public class DesignMetal {

	@Id
	@GeneratedValue
	@Column(name = "DesignMetalId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StyleId")
	private Design design;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "MetalWeight")
	private Double metalWeight = 0.0;

	@Column(name = "PerPcMetalWeight")
	private Double perPcMetalWeight = 0.0;

	
	@Column(name = "MetalPcs")
	private Integer metalPcs = 0;

	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "PerGramRate")
	private Double perGramRate = 0.0;

	@Column(name = "LossPerc")
	private Double lossPerc = 0.0;

	@Column(name = "MetalValue")
	private Double metalValue = 0.0;

	@Column(name = "WaxWt")
	private Double waxWt = 0.0;
	
	@Column(name = "PerPcWaxWt")
	private Double perPcWaxWt = 0.0;

	@Column(name = "MainMetal")
	private Boolean mainMetal = false;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

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
	
	@Column(name="RptWt")
	private Double rptWt = 0.0;
	
	
	@Column(name="SilverWt")
	private Double silverWt = 0.0;
	
	@Column(name="PerPcSilverWt")
	private Double perPcSilverWt = 0.0;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}

	public Double getMetalWeight() {
		return metalWeight;
	}

	public void setMetalWeight(Double metalWeight) {
		this.metalWeight = metalWeight;
	}

	public Integer getMetalPcs() {
		return metalPcs;
	}

	public void setMetalPcs(Integer metalPcs) {
		this.metalPcs = metalPcs;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public Double getPerGramRate() {
		return perGramRate;
	}

	public void setPerGramRate(Double perGramRate) {
		this.perGramRate = perGramRate;
	}

	public Double getLossPerc() {
		return lossPerc;
	}

	public void setLossPerc(Double lossPerc) {
		this.lossPerc = lossPerc;
	}

	public Double getMetalValue() {
		return metalValue;
	}

	public void setMetalValue(Double metalValue) {
		this.metalValue = metalValue;
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

	public Boolean getMainMetal() {
		return mainMetal;
	}

	public void setMainMetal(Boolean mainMetal) {
		this.mainMetal = mainMetal;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public Double getWaxWt() {
		return waxWt;
	}

	public void setWaxWt(Double waxWt) {
		this.waxWt = waxWt;
	}

	public Double getRptWt() {
		return rptWt;
	}

	public void setRptWt(Double rptWt) {
		this.rptWt = rptWt;
	}

	public Double getSilverWt() {
		return silverWt;
	}

	public void setSilverWt(Double silverWt) {
		this.silverWt = silverWt;
	}

	public Double getPerPcMetalWeight() {
		return perPcMetalWeight;
	}

	public void setPerPcMetalWeight(Double perPcMetalWeight) {
		this.perPcMetalWeight = perPcMetalWeight;
	}

	public Double getPerPcWaxWt() {
		return perPcWaxWt;
	}

	public void setPerPcWaxWt(Double perPcWaxWt) {
		this.perPcWaxWt = perPcWaxWt;
	}

	public Double getPerPcSilverWt() {
		return perPcSilverWt;
	}

	public void setPerPcSilverWt(Double perPcSilverWt) {
		this.perPcSilverWt = perPcSilverWt;
	}
	
	

}
