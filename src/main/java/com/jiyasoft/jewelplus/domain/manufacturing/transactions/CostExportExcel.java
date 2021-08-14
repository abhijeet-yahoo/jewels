package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "costexportexcel")
public class CostExportExcel {
	
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
	
	
	
	@Column(name = "Barcode")
	private String barcode;
	
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

	@Column(name = "StoneValue")
	private Double stoneValue = 0.0;

	@Column(name = "CompValue")
	private Double compValue = 0.0;

	@Column(name = "LabValue")
	private Double labValue = 0.0;

	@Column(name = "HdlgValue")
	private Double hdlgValue = 0.0;

	@Column(name = "LossValue")
	private Double lossValue = 0.0;

	@Column(name = "SetValue")
	private Double setValue = 0.0;

	@Column(name = "Fob")
	private Double fob = 0.0;
	
	@Column(name = "PerPcFinalPrice")
	private Double perPcFinalPrice = 0.0;

	@Column(name = "FinalPrice")
	private Double finalPrice = 0.0;

	@Column(name = "PerGramRate")
	private Double perGramRate = 0.0;
	
	@Column(name = "DiscAmount")
	private Double discAmount = 0.0;
	
	@Column(name = "Carat")
	private Double carat = 0.0;
	
	@Column(name = "HandlingRate")
	private Double handlingRate = 0.0;

	@Column(name = "HandlingValue")
	private Double handlingValue = 0.0;
	
	@Column(name = "SetVal")
	private Double setVal = 0.0;
	
	@Column(name = "StnRate")
	private Double stnRate = 0.0;
	
	@Column(name = "Sieve")
	private String sieve;
	
	
	@Column(name = "Size")
	private String size;
	
	@Column(name = "Stone")
	private Integer stone = 0;
	
	
	@Column(name = "StoneVal")
	private Double stoneVal = 0.0;
	 
	@Column(name = "QualityNm")
	private String qualityNm;
	
	
	@Column(name = "Setnm")
	private String setNm;
	
	
	@Column(name = "SettypeNm")
	private String settypeNm;
	
	@Column(name = "ShapeNm")
	private String shapeNm;
	
	@Column(name = "SizegroupNm")
	private String sizegroupNm;
	
	@Column(name = "StonetypeNm")
	private String stonetypeNm;
	

	
	@Column(name = "Cfpl")
	private Double cfpl = 0.0;
	
	@Column(name = "Rhd")
	private Double rhd = 0.0;
	
	@Column(name = "Mil")
	private Double mil = 0.0;
	
	@Column(name = "Certify")
	private Double certify = 0.0;
	
	@Column(name = "Solder")
	private Double solder = 0.0;
	
	@Column(name = "Serial")
	private Integer serial = 0;
	
	@Column(name = "SetRate")
	private Double setRate = 0.0;
	
	@Column(name = "RefNo")
	private String refNo;
	
	@Column(name = "OrderRate")
	private Double orderRate = 0.0;

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



	public String getBarcode() {
		return barcode;
	}



	public void setBarcode(String barcode) {
		this.barcode = barcode;
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



	public Double getCompValue() {
		return compValue;
	}



	public void setCompValue(Double compValue) {
		this.compValue = compValue;
	}



	public Double getLabValue() {
		return labValue;
	}



	public void setLabValue(Double labValue) {
		this.labValue = labValue;
	}



	public Double getHdlgValue() {
		return hdlgValue;
	}



	public void setHdlgValue(Double hdlgValue) {
		this.hdlgValue = hdlgValue;
	}



	public Double getLossValue() {
		return lossValue;
	}



	public void setLossValue(Double lossValue) {
		this.lossValue = lossValue;
	}



	public Double getSetValue() {
		return setValue;
	}



	public void setSetValue(Double setValue) {
		this.setValue = setValue;
	}



	public Double getFob() {
		return fob;
	}



	public void setFob(Double fob) {
		this.fob = fob;
	}



	public Double getPerPcFinalPrice() {
		return perPcFinalPrice;
	}



	public void setPerPcFinalPrice(Double perPcFinalPrice) {
		this.perPcFinalPrice = perPcFinalPrice;
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



	public Double getDiscAmount() {
		return discAmount;
	}



	public void setDiscAmount(Double discAmount) {
		this.discAmount = discAmount;
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



	public Double getSetVal() {
		return setVal;
	}



	public void setSetVal(Double setVal) {
		this.setVal = setVal;
	}



	public Double getStnRate() {
		return stnRate;
	}



	public void setStnRate(Double stnRate) {
		this.stnRate = stnRate;
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



	public Integer getStone() {
		return stone;
	}



	public void setStone(Integer stone) {
		this.stone = stone;
	}



	public Double getStoneVal() {
		return stoneVal;
	}



	public void setStoneVal(Double stoneVal) {
		this.stoneVal = stoneVal;
	}



	public String getQualityNm() {
		return qualityNm;
	}



	public void setQualityNm(String qualityNm) {
		this.qualityNm = qualityNm;
	}



	public String getSetNm() {
		return setNm;
	}



	public void setSetNm(String setNm) {
		this.setNm = setNm;
	}



	public String getSettypeNm() {
		return settypeNm;
	}



	public void setSettypeNm(String settypeNm) {
		this.settypeNm = settypeNm;
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



	public String getStonetypeNm() {
		return stonetypeNm;
	}



	public void setStonetypeNm(String stonetypeNm) {
		this.stonetypeNm = stonetypeNm;
	}






	public Double getCfpl() {
		return cfpl;
	}



	public void setCfpl(Double cfpl) {
		this.cfpl = cfpl;
	}



	public Double getRhd() {
		return rhd;
	}



	public void setRhd(Double rhd) {
		this.rhd = rhd;
	}



	public Double getMil() {
		return mil;
	}



	public void setMil(Double mil) {
		this.mil = mil;
	}



	public Double getCertify() {
		return certify;
	}



	public void setCertify(Double certify) {
		this.certify = certify;
	}



	public Double getSolder() {
		return solder;
	}



	public void setSolder(Double solder) {
		this.solder = solder;
	}



	public Integer getSerial() {
		return serial;
	}



	public void setSerial(Integer serial) {
		this.serial = serial;
	}



	public Double getSetRate() {
		return setRate;
	}



	public void setSetRate(Double setRate) {
		this.setRate = setRate;
	}



	public String getRefNo() {
		return refNo;
	}



	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}



	public Double getOrderRate() {
		return orderRate;
	}



	public void setOrderRate(Double orderRate) {
		this.orderRate = orderRate;
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
	
	
	
	
	
	
	
	

	
	

}
