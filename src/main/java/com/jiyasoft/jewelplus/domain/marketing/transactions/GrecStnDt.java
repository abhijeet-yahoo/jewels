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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;

@Entity
@Table(name = "grecstndt")
public class GrecStnDt {

	@Id
	@GeneratedValue
	@Column(name = "GrecStnId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private GrecMt grecMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DtId")
	private GrecDt grecDt;

	@Column(name = "SrNo", updatable = false)
	private Integer srNo;

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
	
	@Column(name = "DiaCateg")
	private String diaCateg;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizeGroupId")
	private SizeGroup sizeGroup;

	@Column(name = "Stone")
	private Integer stone =0;

	@Column(name = "Carat")
	private Double carat =0.0;

	
	@Column(name = "StoneValue")
	private Double stoneValue = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SettingId")
	private Setting setting;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StoneTypeId")
	private StoneType stoneType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SettypeId")
	private SettingType settingType;

	@Column(name = "SetRate")
	private Double setRate =0.0;

	@Column(name = "SetValue")
	private Double setValue =0.0;

	@Column(name = "HandlingRate")
	private Double handlingRate =0.0;

	@Column(name = "HandlingValue")
	private Double handlingValue =0.0;

	@Column(name = "SeqNo")
	private Integer seqNo;

	@Column(name = "Breadth")
	private String breadth;

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
	
	@Column(name = "CenterStone")
	private Boolean centerStone = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name = "Rlock")
	private Boolean rLock = false;
	
	@Column(name = "HdlgPerCarat")
	private Boolean hdlgPerCarat = false;
	
	@Column(name = "HdlgPercentWise")
	private Boolean hdlgPercentWise = false;
	
	@Column(name = "StnRate")
	private Double stnRate = 0.0;
	
	@Column(name = "PerPcsRateFlg")
	private Boolean perPcsRateFlg = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GrecMt getGrecMt() {
		return grecMt;
	}

	public void setGrecMt(GrecMt grecMt) {
		this.grecMt = grecMt;
	}

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public GrecDt getGrecDt() {
		return grecDt;
	}

	public void setGrecDt(GrecDt grecDt) {
		this.grecDt = grecDt;
	}

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
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

	public String getDiaCateg() {
		return diaCateg;
	}

	public void setDiaCateg(String diaCateg) {
		this.diaCateg = diaCateg;
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

	public Double getStoneValue() {
		return stoneValue;
	}

	public void setStoneValue(Double stoneValue) {
		this.stoneValue = stoneValue;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}

	public SettingType getSettingType() {
		return settingType;
	}

	public void setSettingType(SettingType settingType) {
		this.settingType = settingType;
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

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getBreadth() {
		return breadth;
	}

	public void setBreadth(String breadth) {
		this.breadth = breadth;
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

	public Boolean getCenterStone() {
		return centerStone;
	}

	public void setCenterStone(Boolean centerStone) {
		this.centerStone = centerStone;
	}

	public Date getDeactiveDt() {
		return deactiveDt;
	}

	public void setDeactiveDt(Date deactiveDt) {
		this.deactiveDt = deactiveDt;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
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

	public Double getStnRate() {
		return stnRate;
	}

	public void setStnRate(Double stnRate) {
		this.stnRate = stnRate;
	}

	public Boolean getPerPcsRateFlg() {
		return perPcsRateFlg;
	}

	public void setPerPcsRateFlg(Boolean perPcsRateFlg) {
		this.perPcsRateFlg = perPcsRateFlg;
	}
	
	
}
