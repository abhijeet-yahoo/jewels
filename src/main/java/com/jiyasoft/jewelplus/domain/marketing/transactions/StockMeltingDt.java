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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;

@Entity
@Table(name="stockmeltingdt")
public class StockMeltingDt {
	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private StockMeltingMt stockMeltingMt;
	
	@Column(name = "BagId")
	private Integer bagId;
	
	@Column(name = "Barcode")
	private String Barcode;
	
	@Column(name = "PendApprovalFlg")
	private Boolean pendApprovalFlg = false;
	
	@Column(name = "CurrStk")
	private Boolean currStk = false;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RefStockMtId")
	private StockMt stockMt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StockMeltingMt getStockMeltingMt() {
		return stockMeltingMt;
	}

	public void setStockMeltingMt(StockMeltingMt stockMeltingMt) {
		this.stockMeltingMt = stockMeltingMt;
	}

	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public String getBarcode() {
		return Barcode;
	}

	public void setBarcode(String barcode) {
		Barcode = barcode;
	}

	public Boolean getPendApprovalFlg() {
		return pendApprovalFlg;
	}

	public void setPendApprovalFlg(Boolean pendApprovalFlg) {
		this.pendApprovalFlg = pendApprovalFlg;
	}

	public Boolean getCurrStk() {
		return currStk;
	}

	public void setCurrStk(Boolean currStk) {
		this.currStk = currStk;
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

	public Date getModiDt() {
		return modiDt;
	}

	public void setModiDt(Date modiDt) {
		this.modiDt = modiDt;
	}

	public StockMt getStockMt() {
		return stockMt;
	}

	public void setStockMt(StockMt stockMt) {
		this.stockMt = stockMt;
	}

	
}
