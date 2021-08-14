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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;

@Entity
@Table(name = "jobrecstndt")
public class JobRecStnDt {
	
	@Id
	@GeneratedValue
	@Column(name = "StnDtId ")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DtId")
	private JobRecDt jobRecDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private JobRecMt jobRecMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ShapeId")
	private Shape shape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QualityId")
	private Quality quality;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizeGroupId")
	private SizeGroup sizeGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SettingId")
	private Setting setting;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SetTypeId")
	private SettingType settingType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SordDtId")
	private OrderDt orderDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StnRecDtId")
	private StoneInwardDt stoneInwardDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StoneTypeId")
	private StoneType stoneType;

	@Column(name = "SeqNo")
	private Integer seqNo;

	@Column(name = "BagSrNo",updatable = false)
	private Integer bagSrNo;

	@Column(name = "Size")
	private String size;

	@Column(name = "Sieve")
	private String sieve;

	@Column(name = "Stone")
	private Integer stone = 0;

	@Column(name = "Carat")
	private Double carat = 0.0;

	@Column(name = "TagWt")
	private Double tagWt = 0.0;

	@Column(name = "StnRate")
	private Double stnRate = 0.0;

	@Column(name = "StoneValue")
	private Double stoneValue = 0.0;

	@Column(name = "SetRate")
	private Double setRate = 0.0;

	@Column(name = "SetValue")
	private Double setValue = 0.0;

	@Column(name = "HandlingRate")
	private Double handlingRate = 0.0;

	@Column(name = "HandlingValue")
	private Double handlingValue = 0.0;

	@Column(name = "ManualCaratRate")
	private Double manualCaratRate = 0.0;

	@Column(name = "ManualSetRate")
	private Double manualSetRate = 0.0;

	@Column(name = "PerStoneWt ")
	private Double perStoneWt = 0.0;

	@Column(name = "CaratRate ")
	private Double caratRate = 0.0;

	@Column(name = "CenterStone")
	private Boolean centerStone = false;

	@Column(name = "Rlock")
	private Boolean rLock = false;

	@Column(name = "ItemNo")
	private String itemNo;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;
	
	@Column(name = "PerPcRate")
	private Boolean perPcRate = false;
	
	@Column(name = "PerGramRate")
	private Boolean perGramRate = false;
	
	@Column(name = "HdlgPerCarat")
	private Boolean hdlgPerCarat = false;

	@Column(name = "HdlgPercentWise")
	private Boolean hdlgPercentWise = false;

	@Column(name = "PerPcsRateFlg")
	private Boolean perPcsRateFlg = false;
	
	@Column(name = "ManualFlg")
	private Boolean manualFlg = false;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JobRecDt getJobRecDt() {
		return jobRecDt;
	}

	public void setJobRecDt(JobRecDt jobRecDt) {
		this.jobRecDt = jobRecDt;
	}

	public JobRecMt getJobRecMt() {
		return jobRecMt;
	}

	public void setJobRecMt(JobRecMt jobRecMt) {
		this.jobRecMt = jobRecMt;
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

	public OrderDt getOrderDt() {
		return orderDt;
	}

	public void setOrderDt(OrderDt orderDt) {
		this.orderDt = orderDt;
	}

	public StoneInwardDt getStoneInwardDt() {
		return stoneInwardDt;
	}

	public void setStoneInwardDt(StoneInwardDt stoneInwardDt) {
		this.stoneInwardDt = stoneInwardDt;
	}

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public Integer getBagSrNo() {
		return bagSrNo;
	}

	public void setBagSrNo(Integer bagSrNo) {
		this.bagSrNo = bagSrNo;
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

	public Double getTagWt() {
		return tagWt;
	}

	public void setTagWt(Double tagWt) {
		this.tagWt = tagWt;
	}

	public Double getStnRate() {
		return stnRate;
	}

	public void setStnRate(Double stnRate) {
		this.stnRate = stnRate;
	}

	public Double getStoneValue() {
		return stoneValue;
	}

	public void setStoneValue(Double stoneValue) {
		this.stoneValue = stoneValue;
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

	public Double getManualCaratRate() {
		return manualCaratRate;
	}

	public void setManualCaratRate(Double manualCaratRate) {
		this.manualCaratRate = manualCaratRate;
	}

	public Double getManualSetRate() {
		return manualSetRate;
	}

	public void setManualSetRate(Double manualSetRate) {
		this.manualSetRate = manualSetRate;
	}

	public Double getPerStoneWt() {
		return perStoneWt;
	}

	public void setPerStoneWt(Double perStoneWt) {
		this.perStoneWt = perStoneWt;
	}

	public Double getCaratRate() {
		return caratRate;
	}

	public void setCaratRate(Double caratRate) {
		this.caratRate = caratRate;
	}

	public Boolean getCenterStone() {
		return centerStone;
	}

	public void setCenterStone(Boolean centerStone) {
		this.centerStone = centerStone;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
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

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public Boolean getPerPcRate() {
		return perPcRate;
	}

	public void setPerPcRate(Boolean perPcRate) {
		this.perPcRate = perPcRate;
	}

	public Boolean getPerGramRate() {
		return perGramRate;
	}

	public void setPerGramRate(Boolean perGramRate) {
		this.perGramRate = perGramRate;
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

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public Boolean getManualFlg() {
		return manualFlg;
	}

	public void setManualFlg(Boolean manualFlg) {
		this.manualFlg = manualFlg;
	}
	
	

}
