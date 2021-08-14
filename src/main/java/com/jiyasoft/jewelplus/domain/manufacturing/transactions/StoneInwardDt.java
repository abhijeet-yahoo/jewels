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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;

@Entity
@Table(name = "stnrecdt")
public class StoneInwardDt {
	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private StoneInwardMt stoneInwardMt;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@Column(name = "Size")
	private String size;

	@Column(name = "Sieve")
	private String sieve;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizeGroupId")
	private SizeGroup sizeGroup;
	
	@Column(name = "SordDtId")
	private Integer sordDtId;

	@Column(name = "Stone")
	private Integer stone = 0;

	@Column(name = "Carat")
	private Double carat = 0.0;
	
	@Column(name = "DiffCarat")
	private Double diffCarat = 0.0;

	@Column(name = "Rate")
	private Double rate = 0.0;
	
	@Column(name = "BagQty")
	private Double bagQty = 0.0;
	
	@Column(name = "LastUpdatedRate")
	private Double lastUpdatedRate = 0.0;

	@Column(name = "PacketNo")
	private String packetNo;

	@Column(name = "BalCarat")
	private Double balCarat = 0.0;

	@Column(name = "BrkCarat")
	private Double brkCarat = 0.0;

	@Column(name = "FallCarat")
	private Double fallCarat = 0.0;

	@Column(name = "BrkStone")
	private Integer brkStone = 0;

	@Column(name = "FallStone")
	private Integer fallStone = 0;

	@Column(name = "BalStone")
	private Integer balStone = 0;

	@Column(name = "Amount")
	private Double amount = 0.0;
	
	@Column(name = "LossStone")
	private Integer lossStone = 0;

	@Column(name = "LossCarat")
	private Double lossCarat = 0.0;
	
	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	@Column(name = "Remark")
	private String remark;
	
	
	@Column(name = "OrdStnSrNo")
	private Integer ordStnSrNo;
	
	@Column(name = "TranSrNo")
	private Integer tranSrNo;
	
	
	@Column(name = "LotNo", updatable = false)
	private String lotNo;

	@Column(name = "LotSrNo", updatable = false)
	private Integer lotSrNo;
	
	@Column(name = "RefTranId")
	private Integer refTranId;
	
	@Column(name = "RefTranType")
	private String refTranType;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StoneInwardMt getStoneInwardMt() {
		return stoneInwardMt;
	}

	public void setStoneInwardMt(StoneInwardMt stoneInwardMt) {
		this.stoneInwardMt = stoneInwardMt;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLossStone() {
		return lossStone;
	}

	public void setLossStone(Integer lossStone) {
		this.lossStone = lossStone;
	}

	public Double getLossCarat() {
		return lossCarat;
	}

	public void setLossCarat(Double lossCarat) {
		this.lossCarat = lossCarat;
	}

	public Double getLastUpdatedRate() {
		return lastUpdatedRate;
	}

	public void setLastUpdatedRate(Double lastUpdatedRate) {
		this.lastUpdatedRate = lastUpdatedRate;
	}

	public Double getDiffCarat() {
		return diffCarat;
	}

	public void setDiffCarat(Double diffCarat) {
		this.diffCarat = diffCarat;
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

	public Integer getOrdStnSrNo() {
		return ordStnSrNo;
	}

	public void setOrdStnSrNo(Integer ordStnSrNo) {
		this.ordStnSrNo = ordStnSrNo;
	}

	public Integer getTranSrNo() {
		return tranSrNo;
	}

	public void setTranSrNo(Integer tranSrNo) {
		this.tranSrNo = tranSrNo;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public Integer getLotSrNo() {
		return lotSrNo;
	}

	public void setLotSrNo(Integer lotSrNo) {
		this.lotSrNo = lotSrNo;
	}

	public Integer getRefTranId() {
		return refTranId;
	}

	public void setRefTranId(Integer refTranId) {
		this.refTranId = refTranId;
	}

	public String getRefTranType() {
		return refTranType;
	}

	public void setRefTranType(String refTranType) {
		this.refTranType = refTranType;
	}


	
	
	

}
