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
@Table(name ="stockstndt")
public class StockStnDt {

	@Id
	@GeneratedValue
	@Column(name ="StkStnId")
	private Integer stkStnId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private StockMt stockMt;
	
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
	
	@Column(name ="Size")
	private String size;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SizeGroupId")
	private SizeGroup sizeGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SettingId")
	private Setting setting;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SetTypeId")
	private SettingType settingType;
	
	@Column(name ="Stone")
	private Integer stone;
	
	@Column(name ="Carat")
	private Double carat = 0.0;
	
	@Column(name ="Rate")
	private Double rate = 0.0;
	
	@Column(name ="DiamValue")
	private Double diamValue =0.0;
	
	@Column(name = "CenterStone")
	private Boolean centerStone = false;
	
	@Column(name = "RefStkStnId")
	private Integer refStkStnId;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive=false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name = "AvgRate")
	private Double avgRate = 0.0;
	
	@Column(name = "FactoryRate")
	private Double factoryRate = 0.0;
	
	@Column(name = "TransferRate")
	private Double transferRate = 0.0;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;
	
	@Column(name ="Sieve")
	private String sieve;
	
	
	public Integer getStkStnId() {
		return stkStnId;
	}

	public void setStkStnId(Integer stkStnId) {
		this.stkStnId = stkStnId;
	}


	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

	public Double getDiamValue() {
		return diamValue;
	}

	public void setDiamValue(Double diamValue) {
		this.diamValue = diamValue;
	}

	public Integer getRefStkStnId() {
		return refStkStnId;
	}

	public void setRefStkStnId(Integer refStkStnId) {
		this.refStkStnId = refStkStnId;
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

	public StockMt getStockMt() {
		return stockMt;
	}

	public void setStockMt(StockMt stockMt) {
		this.stockMt = stockMt;
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

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public String getSieve() {
		return sieve;
	}

	public void setSieve(String sieve) {
		this.sieve = sieve;
	}

	public Boolean getCenterStone() {
		return centerStone;
	}

	public void setCenterStone(Boolean centerStone) {
		this.centerStone = centerStone;
	}
	
	
	
}
