package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vaddexportexcel")
public class VaddExportExcel {
	
	@Id
	@GeneratedValue
	@Column(name = "ExcelId")
	private Integer id;
	


	@Column(name = "InvNo")
	private String invNo;

	@Column(name = "InvDate")
	private Date invDate;
	
	@Column(name = "TotStone")
	private Integer totStone=0;
	
	
	@Column(name = "TotCarat")
	private Double totCarat = 0.0;
	
	@Column(name = "StyleNo")
	private String styleNo;

	
	@Column(name = "PurityNm")
	private String purityNm;
	
	@Column(name = "ColorNm")
	private String colorNm;
	
	@Column(name = "Pcs")
	private Double pcs = 0.0;
	
	@Column(name = "GrossWt")
	private Double grossWt = 0.0;
	
	@Column(name = "NetWt")
	private Double netWt = 0.0;
	
	@Column(name = "MetalValue")
	private Double metalValue = 0.0;
	
	@Column(name = "TotMetalValue")
	private Double totMetalValue = 0.0;

	@Column(name = "TotStoneValue")
	private Double totStoneValue = 0.0;
	
	@Column(name = "LossWt")
	private Double lossWt = 0.0;
	
	@Column(name = "LossValue")
	private Double lossValue = 0.0;
	


	@Column(name = "Fob")
	private Double fob = 0.0;
	
	@Column(name = "FinalPrcPerPcs")
	private Double finalPrcPerPcs = 0.0;

	@Column(name = "FinalPrice")
	private Double finalPrice = 0.0;

	@Column(name = "PerGramRate")
	private Double perGramRate = 0.0;
	
	
	
	@Column(name = "Carat")
	private Double carat = 0.0;
	

	

	
	@Column(name = "StnRate")
	private Double stnRate = 0.0;
	
	
	@Column(name = "Size")
	private String size;
	
	@Column(name = "Stone")
	private Integer stone = 0;
	
	
	@Column(name = "StoneValue")
	private Double stoneValue = 0.0;
	 
	@Column(name = "QualityNm")
	private String qualityNm;
	
	@Column(name = "IntQuality")
	private String intQuality;
	
	@Column(name = "DiaCut")
	private String diaCut;
	

	
	@Column(name = "ShapeNm")
	private String shapeNm;
	
	@Column(name = "SizegroupNm")
	private String sizegroupNm;
	
	
	
	@Column(name = "Serial")
	private Integer serial = 0;
	

	
	@Column(name = "RefNo")
	private String refNo;
	
	

	@Column(name = "ItemSr")
	private Integer itemSr=0;
	
	@Column(name = "CategNm")
	private String categNm;

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

	public Integer getTotStone() {
		return totStone;
	}

	public void setTotStone(Integer totStone) {
		this.totStone = totStone;
	}

	public Double getTotCarat() {
		return totCarat;
	}

	public void setTotCarat(Double totCarat) {
		this.totCarat = totCarat;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public String getPurityNm() {
		return purityNm;
	}

	public void setPurityNm(String purityNm) {
		this.purityNm = purityNm;
	}

	public String getColorNm() {
		return colorNm;
	}

	public void setColorNm(String colorNm) {
		this.colorNm = colorNm;
	}

	public Double getPcs() {
		return pcs;
	}

	public void setPcs(Double pcs) {
		this.pcs = pcs;
	}

	public Double getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(Double grossWt) {
		this.grossWt = grossWt;
	}

	public Double getNetWt() {
		return netWt;
	}

	public void setNetWt(Double netWt) {
		this.netWt = netWt;
	}

	public Double getMetalValue() {
		return metalValue;
	}

	public void setMetalValue(Double metalValue) {
		this.metalValue = metalValue;
	}

	public Double getStoneValue() {
		return stoneValue;
	}

	public void setStoneValue(Double stoneValue) {
		this.stoneValue = stoneValue;
	}

	public Double getLossWt() {
		return lossWt;
	}

	public void setLossWt(Double lossWt) {
		this.lossWt = lossWt;
	}

	public Double getLossValue() {
		return lossValue;
	}

	public void setLossValue(Double lossValue) {
		this.lossValue = lossValue;
	}

	public Double getFob() {
		return fob;
	}

	public void setFob(Double fob) {
		this.fob = fob;
	}

	public Double getFinalPrcPerPcs() {
		return finalPrcPerPcs;
	}

	public void setFinalPrcPerPcs(Double finalPrcPerPcs) {
		this.finalPrcPerPcs = finalPrcPerPcs;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Double getPerGramRate() {
		return perGramRate;
	}

	public void setPerGramRate(Double perGramRate) {
		this.perGramRate = perGramRate;
	}

	public Double getCarat() {
		return carat;
	}

	public void setCarat(Double carat) {
		this.carat = carat;
	}

	public Double getStnRate() {
		return stnRate;
	}

	public void setStnRate(Double stnRate) {
		this.stnRate = stnRate;
	}


	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getStone() {
		return stone;
	}

	public void setStone(Integer stone) {
		this.stone = stone;
	}

	

	public String getQualityNm() {
		return qualityNm;
	}

	public void setQualityNm(String qualityNm) {
		this.qualityNm = qualityNm;
	}

	public String getIntQuality() {
		return intQuality;
	}

	public void setIntQuality(String intQuality) {
		this.intQuality = intQuality;
	}

	public String getDiaCut() {
		return diaCut;
	}

	public void setDiaCut(String diaCut) {
		this.diaCut = diaCut;
	}

	public String getShapeNm() {
		return shapeNm;
	}

	public void setShapeNm(String shapeNm) {
		this.shapeNm = shapeNm;
	}

	public String getSizegroupNm() {
		return sizegroupNm;
	}

	public void setSizegroupNm(String sizegroupNm) {
		this.sizegroupNm = sizegroupNm;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}



	public Integer getItemSr() {
		return itemSr;
	}

	public void setItemSr(Integer itemSr) {
		this.itemSr = itemSr;
	}

	public String getCategNm() {
		return categNm;
	}

	public void setCategNm(String categNm) {
		this.categNm = categNm;
	}

	public Double getTotStoneValue() {
		return totStoneValue;
	}

	public void setTotStoneValue(Double totStoneValue) {
		this.totStoneValue = totStoneValue;
	}

	public Double getTotMetalValue() {
		return totMetalValue;
	}

	public void setTotMetalValue(Double totMetalValue) {
		this.totMetalValue = totMetalValue;
	}
	


	

	
	

}
