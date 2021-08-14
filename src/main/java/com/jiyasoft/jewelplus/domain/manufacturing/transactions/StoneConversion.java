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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;

@Entity
@Table(name = "stoneconv")
public class StoneConversion {
	@Id
	@GeneratedValue
	@Column(name = "MtId")
	private Integer id;
	
	@Column(name = "InvNo")
	private String invNo;
	
	@Column(name = "InvDate")
	private Date invDate;
	
	@Column(name ="SrNo")
	private Integer srNo;
	
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StoneTypeId")
	private StoneType stoneType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ShapeId")
	private Shape shape;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QualityId")
	private Quality quality;
	
	@Column(name = "Size")
	private String size;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IssShapeId")
	private Shape issShape;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IssQualityId")
	private Quality issQuality;
	
	@Column(name = "IssSize")
	private String issSize;
	
	
	@Column(name = "Stone")
	private Integer stone = 0;

	@Column(name = "Carat")
	private Double carat = 0.0;
	
	@Column(name = "StoneRate")
	private Double stoneRate = 0.0;
	
	@Column(name = "RefTranId")
	private Integer refTranId = 0;
	
	@Column(name = "Sieve")
	private String sieve;
	
	@Column(name = "RefTranType")
	private String refTranType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
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

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
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

	public Shape getIssShape() {
		return issShape;
	}

	public void setIssShape(Shape issShape) {
		this.issShape = issShape;
	}

	public Quality getIssQuality() {
		return issQuality;
	}

	public void setIssQuality(Quality issQuality) {
		this.issQuality = issQuality;
	}

	public String getIssSize() {
		return issSize;
	}

	public void setIssSize(String issSize) {
		this.issSize = issSize;
	}

	public Integer getStone() {
		return stone;
	}

	public void setStone(Integer stone) {
		this.stone = stone;
	}

	public Double getCarat() {
		return carat;
	}

	public void setCarat(Double carat) {
		this.carat = carat;
	}

	public Double getStoneRate() {
		return stoneRate;
	}

	public void setStoneRate(Double stoneRate) {
		this.stoneRate = stoneRate;
	}

	public Integer getRefTranId() {
		return refTranId;
	}

	public void setRefTranId(Integer refTranId) {
		this.refTranId = refTranId;
	}

	public String getSieve() {
		return sieve;
	}

	public void setSieve(String sieve) {
		this.sieve = sieve;
	}

	public String getRefTranType() {
		return refTranType;
	}

	public void setRefTranType(String refTranType) {
		this.refTranType = refTranType;
	}

	
	
	
}
