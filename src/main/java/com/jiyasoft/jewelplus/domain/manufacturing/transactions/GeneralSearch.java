package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "generalsearch")
public class GeneralSearch {

	@Id
	@GeneratedValue
	@Column(name = "GenSearchId")
	private int id;

	@Column(name = "PartyNm")
	private String partyNm;

	@Column(name = "OrderType")
	private String orderType;

	@Column(name = "InvNo")
	private String orderNo;

	@Column(name = "RefNo")
	private String orderRefNo;

	@Column(name = "BagNm")
	private String bagNm;

	@Column(name = "BagId")
	private Integer bagId;

	@Column(name = "StyleNo")
	private String styleNo;

	@Column(name = "purityNm")
	private String purityNm;

	@Column(name = "ColorNm")
	private String colorNm;

	@Column(name = "OrdPcs")
	private Integer ordPcs;

	@Column(name = "BagPcs")
	private Integer bagPcs;

	@Column(name = "RecWt")
	private Double grossWt;

	@Column(name = "Date")
	private Date date;

	@Column(name = "DeptNm")
	private String deptNm;

	@Column(name = "ItemNo")
	private String itemNo;
	
	@Column(name = "SizeNm")
	private String sizeNm;
	
	@Column(name = "DtItem")
	private String dtItem;
	
	@Column(name = "DtRefNo")
	private String dtRefNo;
	
	@Column(name = "DtOrdRef")
	private String dtOrdRef;
	
	@Column(name = "FinalPrice")
	private Double finalPrice = 0.0;

	@Column(name = "ExpInvNo")
	private String expInv;

	@Column(name = "ExpDate")
	private Date expDate;
	
	@Column(name = "bagCloseFlg")
	private Boolean bagCloseFlg;
	

	@Column(name = "DiaPcs")
	private Integer diaPcs = 0;
	
	@Column(name = "ColPcs")
	private Integer colPcs = 0;
	
	@Column(name = "CzPcs")
	private Integer czPcs = 0;
	
	@Column(name = "DiaWt")
	private Double diaWt = 0.0;
	
	@Column(name = "ColWt")
	private Double colWt = 0.0;
	
	@Column(name = "CzWt")
	private Double czWt = 0.0;
	
	public String getInvDateStr() {
		String vDate = "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if(expDate !=null){
			vDate = dateFormat.format(expDate);
		}
		
		return vDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartyNm() {
		return partyNm;
	}

	public void setPartyNm(String partyNm) {
		this.partyNm = partyNm;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderRefNo() {
		return orderRefNo;
	}

	public void setOrderRefNo(String orderRefNo) {
		this.orderRefNo = orderRefNo;
	}

	public String getBagNm() {
		return bagNm;
	}

	public void setBagNm(String bagNm) {
		this.bagNm = bagNm;
	}

	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
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

	public Integer getOrdPcs() {
		return ordPcs;
	}

	public void setOrdPcs(Integer ordPcs) {
		this.ordPcs = ordPcs;
	}

	public Integer getBagPcs() {
		return bagPcs;
	}

	public void setBagPcs(Integer bagPcs) {
		this.bagPcs = bagPcs;
	}

	public Double getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(Double grossWt) {
		this.grossWt = grossWt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getExpInv() {
		return expInv;
	}

	public void setExpInv(String expInv) {
		this.expInv = expInv;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	
	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getSizeNm() {
		return sizeNm;
	}

	public void setSizeNm(String sizeNm) {
		this.sizeNm = sizeNm;
	}

	public String getDtItem() {
		return dtItem;
	}

	public void setDtItem(String dtItem) {
		this.dtItem = dtItem;
	}

	public String getDtRefNo() {
		return dtRefNo;
	}

	public void setDtRefNo(String dtRefNo) {
		this.dtRefNo = dtRefNo;
	}

	public String getDtOrdRef() {
		return dtOrdRef;
	}

	public void setDtOrdRef(String dtOrdRef) {
		this.dtOrdRef = dtOrdRef;
	}

	public Boolean getBagCloseFlg() {
		return bagCloseFlg;
	}

	public void setBagCloseFlg(Boolean bagCloseFlg) {
		this.bagCloseFlg = bagCloseFlg;
	}

	public Integer getDiaPcs() {
		return diaPcs;
	}

	public void setDiaPcs(Integer diaPcs) {
		this.diaPcs = diaPcs;
	}

	public Integer getColPcs() {
		return colPcs;
	}

	public void setColPcs(Integer colPcs) {
		this.colPcs = colPcs;
	}

	public Integer getCzPcs() {
		return czPcs;
	}

	public void setCzPcs(Integer czPcs) {
		this.czPcs = czPcs;
	}

	public Double getDiaWt() {
		return diaWt;
	}

	public void setDiaWt(Double diaWt) {
		this.diaWt = diaWt;
	}

	public Double getColWt() {
		return colWt;
	}

	public void setColWt(Double colWt) {
		this.colWt = colWt;
	}

	public Double getCzWt() {
		return czWt;
	}

	public void setCzWt(Double czWt) {
		this.czWt = czWt;
	}
	

	
	
}
