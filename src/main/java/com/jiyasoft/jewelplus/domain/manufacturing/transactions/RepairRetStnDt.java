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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;

@Entity
@Table(name ="repairretstndt")
public class RepairRetStnDt {

	@Id
	@GeneratedValue
	@Column(name ="StnId")
	private Integer id;
	
	
	@Column(name ="Carat")
	private Double carat = 0.0;
	
	@Column(name="HandlingRate")
	private Double handlingRate =0.0;
	
	@Column(name ="HandlingValue")
	private Double handlingValue =0.0;
	
	@Column(name ="HdlgPerCarat")
	private Boolean hdlgPerCarat = false;

	@Column(name ="HdlgPercentWise")
	private Boolean hdlgPercentWise = false;
	
	@Column(name ="PerPcsRateFlg")
	private Boolean perPcsRateFlg = false;
	
	@Column(name ="Rlock")
	private Boolean rLock = false;
	
	@Column(name = "SeqNo")
	private Integer seqNo;
	
	@Column(name = "SetRate")
	private Double setRate = 0.0;
	
	@Column(name ="SetValue")
	private Double setValue = 0.0;
	
	@Column(name ="Sieve")
	private String sieve;
	
	@Column(name = "Size")
	private String size;
	
	@Column(name ="SrNo")
	private Integer srNo;
	
	@Column(name ="Stone")
	private Integer stone;
	
	@Column(name = "StoneRate")
	private Double stoneRate = 0.0;
	
	@Column(name ="StoneValue")
	private Double stoneValue = 0.0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DtId")
	private RepairRetDt repairRetDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private RepairRetMt repairRetMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="PartNm")
	private LookUpMast partNm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="QualityId")
	private Quality quality;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SettingId")
	private Setting setting;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SetTypeId")
	private SettingType settingType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ShapeId")
	private Shape shape;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SizeGroupId")
	private SizeGroup sizeGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="StoneTypeId")
	private StoneType stoneType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SubShapeId")
	private SubShape subShape;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;	

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

	@Column(name = "CenterStone")
	private Boolean centerStone = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCarat() {
		return carat;
	}

	public void setCarat(Double carat) {
		this.carat = carat;
	}

	public Double getHandlingRate() {
		return handlingRate;
	}

	public void setHandlingRate(Double handlingRate) {
		this.handlingRate = handlingRate;
	}

	public Double getHandlingValue() {
		return handlingValue;
	}

	public void setHandlingValue(Double handlingValue) {
		this.handlingValue = handlingValue;
	}

	public Boolean getHdlgPerCarat() {
		return hdlgPerCarat;
	}

	public void setHdlgPerCarat(Boolean hdlgPerCarat) {
		this.hdlgPerCarat = hdlgPerCarat;
	}

	public Boolean getHdlgPercentWise() {
		return hdlgPercentWise;
	}

	public void setHdlgPercentWise(Boolean hdlgPercentWise) {
		this.hdlgPercentWise = hdlgPercentWise;
	}

	public Boolean getPerPcsRateFlg() {
		return perPcsRateFlg;
	}

	public void setPerPcsRateFlg(Boolean perPcsRateFlg) {
		this.perPcsRateFlg = perPcsRateFlg;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public Double getSetRate() {
		return setRate;
	}

	public void setSetRate(Double setRate) {
		this.setRate = setRate;
	}

	public Double getSetValue() {
		return setValue;
	}

	public void setSetValue(Double setValue) {
		this.setValue = setValue;
	}

	public String getSieve() {
		return sieve;
	}

	public void setSieve(String sieve) {
		this.sieve = sieve;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public Integer getStone() {
		return stone;
	}

	public void setStone(Integer stone) {
		this.stone = stone;
	}

	public Double getStoneRate() {
		return stoneRate;
	}

	public void setStoneRate(Double stoneRate) {
		this.stoneRate = stoneRate;
	}

	public Double getStoneValue() {
		return stoneValue;
	}

	public void setStoneValue(Double stoneValue) {
		this.stoneValue = stoneValue;
	}

	public RepairRetDt getRepairRetDt() {
		return repairRetDt;
	}

	public void setRepairRetDt(RepairRetDt repairRetDt) {
		this.repairRetDt = repairRetDt;
	}

	public RepairRetMt getRepairRetMt() {
		return repairRetMt;
	}

	public void setRepairRetMt(RepairRetMt repairRetMt) {
		this.repairRetMt = repairRetMt;
	}

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public Quality getQuality() {
		return quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
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

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public SizeGroup getSizeGroup() {
		return sizeGroup;
	}

	public void setSizeGroup(SizeGroup sizeGroup) {
		this.sizeGroup = sizeGroup;
	}

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}

	public SubShape getSubShape() {
		return subShape;
	}

	public void setSubShape(SubShape subShape) {
		this.subShape = subShape;
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
	
	
	
	
}
