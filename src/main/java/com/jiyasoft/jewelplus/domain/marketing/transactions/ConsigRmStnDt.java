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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;

@Entity
@Table(name="consigrmstndt")
public class ConsigRmStnDt {

	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private ConsigMt consigMt;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StoneTypeId")
	private StoneType stoneType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ShapeId")
	private Shape shape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SubShapeId")
	private SubShape subShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QualityId")
	private Quality quality;

	@Column(name = "Size")
	private String size;

	@Column(name = "Sieve")
	private String sieve;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizeGroupId")
	private SizeGroup sizeGroup;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@Column(name = "Stone")
	private Integer stone = 0;

	@Column(name = "Carat")
	private Double carat = 0.0;
	
	@Column(name = "DiffCarat")
	private Double diffCarat = 0.0;

	@Column(name = "Rate")
	private Double stnRate = 0.0;
	
	@Column(name = "Rate1")
	private Double stnRate1 = 0.0;

	@Column(name = "PacketNo")
	private String packetNo;

	@Column(name = "BalCarat")
	private Double balCarat = 0.0;

	@Column(name = "BrkCarat")
	private Double brkCarat = 0.0;

	@Column(name = "FallCarat")
	private Double fallCarat = 0.0;

	@Column(name = "BrkStone")
	private Integer brkStone;

	@Column(name = "FallStone")
	private Integer fallStone;

	@Column(name = "BalStone")
	private Integer balStone;
	
	@Column(name = "SordDtId")
	private Integer sordDtId;
	
	@Column(name = "BagQty")
	private Double bagQty = 0.0;
	
	@Column(name = "AdjWt")
	private Double adjWt = 0.0;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;


	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;

	@Column(name = "Amount")
	private Double stnAmount = 0.0;

	@Column(name = "Remark")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ConsigMt getConsigMt() {
		return consigMt;
	}

	public void setConsigMt(ConsigMt consigMt) {
		this.consigMt = consigMt;
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

	public SubShape getSubShape() {
		return subShape;
	}

	public void setSubShape(SubShape subShape) {
		this.subShape = subShape;
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

	public String getSieve() {
		return sieve;
	}

	public void setSieve(String sieve) {
		this.sieve = sieve;
	}

	public SizeGroup getSizeGroup() {
		return sizeGroup;
	}

	public void setSizeGroup(SizeGroup sizeGroup) {
		this.sizeGroup = sizeGroup;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public Double getDiffCarat() {
		return diffCarat;
	}

	public void setDiffCarat(Double diffCarat) {
		this.diffCarat = diffCarat;
	}

	public Double getStnRate() {
		return stnRate;
	}

	public void setStnRate(Double stnRate) {
		this.stnRate = stnRate;
	}

	public String getPacketNo() {
		return packetNo;
	}

	public void setPacketNo(String packetNo) {
		this.packetNo = packetNo;
	}

	public Double getBalCarat() {
		return balCarat;
	}

	public void setBalCarat(Double balCarat) {
		this.balCarat = balCarat;
	}

	public Double getBrkCarat() {
		return brkCarat;
	}

	public void setBrkCarat(Double brkCarat) {
		this.brkCarat = brkCarat;
	}

	public Double getFallCarat() {
		return fallCarat;
	}

	public void setFallCarat(Double fallCarat) {
		this.fallCarat = fallCarat;
	}

	public Integer getBrkStone() {
		return brkStone;
	}

	public void setBrkStone(Integer brkStone) {
		this.brkStone = brkStone;
	}

	public Integer getFallStone() {
		return fallStone;
	}

	public void setFallStone(Integer fallStone) {
		this.fallStone = fallStone;
	}

	public Integer getBalStone() {
		return balStone;
	}

	public void setBalStone(Integer balStone) {
		this.balStone = balStone;
	}

	public Integer getSordDtId() {
		return sordDtId;
	}

	public void setSordDtId(Integer sordDtId) {
		this.sordDtId = sordDtId;
	}

	public Double getBagQty() {
		return bagQty;
	}

	public void setBagQty(Double bagQty) {
		this.bagQty = bagQty;
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

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Double getStnAmount() {
		return stnAmount;
	}

	public void setStnAmount(Double stnAmount) {
		this.stnAmount = stnAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getAdjWt() {
		return adjWt;
	}

	public void setAdjWt(Double adjWt) {
		this.adjWt = adjWt;
	}

	public Double getStnRate1() {
		return stnRate1;
	}

	public void setStnRate1(Double stnRate1) {
		this.stnRate1 = stnRate1;
	}


	
	
}
