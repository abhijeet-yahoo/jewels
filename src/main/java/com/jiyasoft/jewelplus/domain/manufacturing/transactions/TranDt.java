package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.sql.Time;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;

@Entity
@Table(name = "trandt")
public class TranDt {

	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;

	@Column(name = "BagSrNo")
	private Integer bagSrNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;


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

	@Column(name = "Carat")
	private Double carat = 0.0;

	@Column(name = "Stone")
	private Integer stone = 0;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StoneTypeId")
	private StoneType stoneType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SetTypeId")
	private SettingType settingType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SettingId")
	private Setting setting;

	@Column(name = "Pcs")
	private Double pcs;

	@Column(name = "CurrStk")
	private Boolean currStk;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private TranMt tranMt;

	@Column(name = "TranDate")
	private Date tranDate;

	@Column(name = "Time")
	private Time time;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptFrom", referencedColumnName = "DeptId")
	private Department deptFrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptTo", referencedColumnName = "DeptId")
	private Department deptTo;

	@Column(name = "IssDate")
	private Date issDate;

	@Column(name = "RefDtId")
	private Integer refDtId;
	
	@Column(name = "RefTranDtId")
	private Integer refTranDtId;
	
	@Column(name = "RefTranType")
	private String refTranType;

	@Column(name = "SplitQty")
	private Double splitQty;

	@Column(name = "SplitCarat")
	private Double splitCarat;

	@Column(name = "SplitStone")
	private Integer splitStone;

	@Column(name = "CenterStone")
	private Boolean centerStone = false;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;
	
	
	@Column(name = "Rate")
	private Double rate = 0.0;

	
	@Column(name = "AvgRate")
	private Double avgRate = 0.0;
	
	@Column(name = "FactoryRate")
	private Double factoryRate = 0.0;
	
	@Column(name = "TransferRate")
	private Double transferRate = 0.0;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public Integer getBagSrNo() {
		return bagSrNo;
	}

	public void setBagSrNo(Integer bagSrNo) {
		this.bagSrNo = bagSrNo;
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

	public Double getCarat() {
		return carat;
	}

	public void setCarat(Double carat) {
		this.carat = carat;
	}

	public Integer getStone() {
		return stone;
	}

	public void setStone(Integer stone) {
		this.stone = stone;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public SettingType getSettingType() {
		return settingType;
	}

	public void setSettingType(SettingType settingType) {
		this.settingType = settingType;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Double getPcs() {
		return pcs;
	}

	public void setPcs(Double pcs) {
		this.pcs = pcs;
	}

	public Boolean getCurrStk() {
		return currStk;
	}

	public void setCurrStk(Boolean currStk) {
		this.currStk = currStk;
	}

	public TranMt getTranMt() {
		return tranMt;
	}

	public void setTranMt(TranMt tranMt) {
		this.tranMt = tranMt;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Department getDeptFrom() {
		return deptFrom;
	}

	public void setDeptFrom(Department deptFrom) {
		this.deptFrom = deptFrom;
	}

	public Department getDeptTo() {
		return deptTo;
	}

	public void setDeptTo(Department deptTo) {
		this.deptTo = deptTo;
	}

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}

	public Date getIssDate() {
		return issDate;
	}

	public void setIssDate(Date issDate) {
		this.issDate = issDate;
	}

	public Integer getRefDtId() {
		return refDtId;
	}

	public void setRefDtId(Integer refDtId) {
		this.refDtId = refDtId;
	}

	public Double getSplitQty() {
		return splitQty;
	}

	public void setSplitQty(Double splitQty) {
		this.splitQty = splitQty;
	}

	public Double getSplitCarat() {
		return splitCarat;
	}

	public void setSplitCarat(Double splitCarat) {
		this.splitCarat = splitCarat;
	}

	public Integer getSplitStone() {
		return splitStone;
	}

	public void setSplitStone(Integer splitStone) {
		this.splitStone = splitStone;
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



	public Boolean getCenterStone() {
		return centerStone;
	}

	public void setCenterStone(Boolean centerStone) {
		this.centerStone = centerStone;
	}

	public SubShape getSubShape() {
		return subShape;
	}

	public void setSubShape(SubShape subShape) {
		this.subShape = subShape;
	}

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(Double avgRate) {
		this.avgRate = avgRate;
	}

	public Double getFactoryRate() {
		return factoryRate;
	}

	public void setFactoryRate(Double factoryRate) {
		this.factoryRate = factoryRate;
	}

	public Double getTransferRate() {
		return transferRate;
	}

	public void setTransferRate(Double transferRate) {
		this.transferRate = transferRate;
	}

	public Integer getRefTranDtId() {
		return refTranDtId;
	}

	public void setRefTranDtId(Integer refTranDtId) {
		this.refTranDtId = refTranDtId;
	}

	public String getRefTranType() {
		return refTranType;
	}

	public void setRefTranType(String refTranType) {
		this.refTranType = refTranType;
	}

	
	
	

}
