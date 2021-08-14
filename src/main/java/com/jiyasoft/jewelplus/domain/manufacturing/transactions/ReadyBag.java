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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;

@Entity
@Table(name = "readybag")
public class ReadyBag {
	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;

	@Column(name = "BagPcs")
	private Double bagPcs;

	@Column(name = "BagSrNo")
	private Integer bagSrNo = 0;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationId")
	private Department location;

	@Column(name = "Size")
	private String size;

	@Column(name = "Sieve")
	private String sieve;

	@Column(name = "Stone")
	private Integer stone = 0;

	@Column(name = "Carat")
	private Double carat = 0.0;

	@Column(name = "RetStone")
	private Integer retStone = 0;

	@Column(name = "RetCarat")
	private Double retCarat = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizeGroupId")
	private SizeGroup sizeGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SettingId")
	private Setting setting;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SettypeId")
	private SettingType settingType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;
	
	@Column(name = "PendApprovalFlg")
	private Boolean pendApprovalFlg = false;

	@Column(name = "CurrStk")
	private Boolean currentStock;

	@Column(name = "IssDt")
	private Date issDt;

	@Column(name = "CenterStone")
	private Boolean centerStone = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private TranMt tranMt;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name = "TranDate")
	private Date tranDate;
	
	@Column(name = "DiaCateg")
	private String diaCateg;
	
	@Column(name = "Rate")
	private Double rate = 0.0;

	
	@Column(name = "AvgRate")
	private Double avgRate = 0.0;
	
	@Column(name = "FactoryRate")
	private Double factoryRate = 0.0;
	
	@Column(name = "TransferRate")
	private Double transferRate = 0.0;
	
	
	
	
	
	
	


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StoneTypeId")
	private StoneType stoneType;

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

	public Double getBagPcs() {
		return bagPcs;
	}

	public void setBagPcs(Double bagPcs) {
		this.bagPcs = bagPcs;
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

	public Integer getRetStone() {
		return retStone;
	}

	public void setRetStone(Integer retStone) {
		this.retStone = retStone;
	}

	public Double getRetCarat() {
		return retCarat;
	}

	public void setRetCarat(Double retCarat) {
		this.retCarat = retCarat;
	}

	public SizeGroup getSizeGroup() {
		return sizeGroup;
	}

	public void setSizeGroup(SizeGroup sizeGroup) {
		this.sizeGroup = sizeGroup;
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

	public Boolean getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(Boolean currentStock) {
		this.currentStock = currentStock;
	}

	public TranMt getTranMt() {
		return tranMt;
	}

	public void setTranMt(TranMt tranMt) {
		this.tranMt = tranMt;
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

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}

	public Date getIssDt() {
		return issDt;
	}

	public void setIssDt(Date issDt) {
		this.issDt = issDt;
	}

	public Boolean getCenterStone() {
		return centerStone;
	}

	public void setCenterStone(Boolean centerStone) {
		this.centerStone = centerStone;
	}

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getDiaCateg() {
		return diaCateg;
	}

	public void setDiaCateg(String diaCateg) {
		this.diaCateg = diaCateg;
	}

	public Department getLocation() {
		return location;
	}

	public void setLocation(Department location) {
		this.location = location;
	}

	public Boolean getPendApprovalFlg() {
		return pendApprovalFlg;
	}

	public void setPendApprovalFlg(Boolean pendApprovalFlg) {
		this.pendApprovalFlg = pendApprovalFlg;
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
	
	
	

}
