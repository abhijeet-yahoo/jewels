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
@Table(name = "stoneratemast")
public class StoneRateMast {
	
	@Id
	@GeneratedValue
	@Column(name = "StoneRateId")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ShapeId")
	private Shape shape;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StoneTypeId")
	private StoneType stoneType ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizeGroupId")
	private SizeGroup sizeGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartyId")
	private Party party;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QualityId")
	private Quality quality;
	
	@Column(name = "Size")
	private String size ;
	
	@Column(name = "StoneRate")
	private Double stoneRate = 0.0;
	
	@Column(name = "PerPcRate")
	private Double perPcRate = 0.0;
	
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
	private Boolean deactive;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name ="Sieve")
	private String sieve;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}

	public SizeGroup getSizeGroup() {
		return sizeGroup;
	}

	public void setSizeGroup(SizeGroup sizeGroup) {
		this.sizeGroup = sizeGroup;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Quality getQuality() {
		return quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	
	public Double getStoneRate() {
		return stoneRate;
	}

	public void setStoneRate(Double stoneRate) {
		this.stoneRate = stoneRate;
	}

	public Double getPerPcRate() {
		return perPcRate;
	}

	public void setPerPcRate(Double perPcRate) {
		this.perPcRate = perPcRate;
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

	public String getSieve() {
		return sieve;
	}

	public void setSieve(String sieve) {
		this.sieve = sieve;
	}
	
	
	

}
