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
@Table(name = "labourcharge")
public class LabourCharge {

	@Id
	@GeneratedValue
	@Column(name = "LabChargeId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategId")
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartyId")
	private Party party;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LabTypeId")
	private LabourType labourType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MetalId")
	private Metal metal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@Column(name = "Rate")
	private Double rate = 0.0;

	@Column(name = "PerPcsRate")
	private Boolean perPcsRate = false;
	
	@Column(name = "PerCaratRate")
	private Boolean perCaratRate = false;

	@Column(name = "PerGramRate")
	private Boolean perGramRate = false;

	@Column(name = "Percentage")
	private Boolean percentage = false;

	@Column(name = "DefLabour")
	private Boolean defLabour = false;

	@Column(name = "FromWeight")
	private Double fromWeight = 0.0;

	@Column(name = "ToWeight")
	private Double toWeight = 0.0;

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

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LabourType getLabourType() {
		return labourType;
	}

	public void setLabourType(LabourType labourType) {
		this.labourType = labourType;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Boolean getPerPcsRate() {
		return perPcsRate;
	}

	public void setPerPcsRate(Boolean perPcsRate) {
		this.perPcsRate = perPcsRate;
	}

	public Boolean getPerGramRate() {
		return perGramRate;
	}

	public void setPerGramRate(Boolean perGramRate) {
		this.perGramRate = perGramRate;
	}

	public Boolean getPercentage() {
		return percentage;
	}

	public void setPercentage(Boolean percentage) {
		this.percentage = percentage;
	}

	public Double getFromWeight() {
		return fromWeight;
	}

	public void setFromWeight(Double fromWeight) {
		this.fromWeight = fromWeight;
	}

	public Double getToWeight() {
		return toWeight;
	}

	public void setToWeight(Double toWeight) {
		this.toWeight = toWeight;
	}

	public Boolean getDefLabour() {
		return defLabour;
	}

	public void setDefLabour(Boolean defLabour) {
		this.defLabour = defLabour;
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

	public Metal getMetal() {
		return metal;
	}

	public void setMetal(Metal metal) {
		this.metal = metal;
	}

	public Boolean getPerCaratRate() {
		return perCaratRate;
	}

	public void setPerCaratRate(Boolean perCaratRate) {
		this.perCaratRate = perCaratRate;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}
	
	

}
