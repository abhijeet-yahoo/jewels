package com.jiyasoft.jewelplus.domain.marketing.transactions;

public class BarcodeExcel {

	private String barcode;
	private Integer bagId;
	
	private String statusRemark;
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	  
	
	public Integer getBagId() {
		return bagId;
	}
	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}
	public String getStatusRemark() {
		return statusRemark;
	}
	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}

	
	
}
