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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;

@Entity
@Table(name = "bagmt")
public class BagMt {
	@Id
	@GeneratedValue
	@Column(name = "BagId")
	private Integer id;

	// OrderTypeCode + max(of new field) + year(15)
	@Column(name = "BagNm")
	private String name;

	@Column(name = "SrNo")
	private Integer srNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SordMtId")
	private OrderMt orderMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SordDtId")
	private OrderDt orderDt;

	// Pcs
	@Column(name = "Qty")
	private Double qty;

	@Column(name = "MainParentBagNm")
	private String mainParentBagNm;

	@Column(name = "ParentBagNm")
	private String parentBagNm;

	@Column(name = "ItemNo")
	private String itemNo;

	@Column(name = "Recast")
	private Boolean recast = false;
	
	@Column(name = "BagCloseFlg")
	private Boolean bagCloseFlg = false;

	@Column(name = "BarcodeSrNo")
	private Integer barcodeSrNo;
	
	@Column(name = "Barcode")
	private String barcode;
	
	@Column(name = "BarcodeType")
	private String barcodeType;
	
	


	@Column(name = "ItemNoDate")
	private Date itemNoDate;

	@Column(name = "ItemNoDeptId")
	private Integer itemNoDeptId;

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

	@Column(name = "SeqNo")
	private Integer seqNo = 0;
	
	@Column(name = "Split")
	private Boolean split = false;
	
	@Column(name = "CostingFlg")
	private Boolean costingFlg = false;
	
	@Column(name = "JobWorkFlg")
	private Boolean jobWorkFlg = false;

	@Column(name = "CostingMtId")
	private Integer costingMtId;
	
	@Column(name ="HuId")
	private String huId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public OrderMt getOrderMt() {
		return orderMt;
	}

	public void setOrderMt(OrderMt orderMt) {
		this.orderMt = orderMt;
	}

	public OrderDt getOrderDt() {
		return orderDt;
	}

	public void setOrderDt(OrderDt orderDt) {
		this.orderDt = orderDt;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public String getMainParentBagNm() {
		return mainParentBagNm;
	}

	public void setMainParentBagNm(String mainParentBagNm) {
		this.mainParentBagNm = mainParentBagNm;
	}

	public String getParentBagNm() {
		return parentBagNm;
	}

	public void setParentBagNm(String parentBagNm) {
		this.parentBagNm = parentBagNm;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Date getItemNoDate() {
		return itemNoDate;
	}

	public void setItemNoDate(Date itemNoDate) {
		this.itemNoDate = itemNoDate;
	}

	public Integer getItemNoDeptId() {
		return itemNoDeptId;
	}

	public void setItemNoDeptId(Integer itemNoDeptId) {
		this.itemNoDeptId = itemNoDeptId;
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

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public Boolean getRecast() {
		return recast;
	}

	public void setRecast(Boolean recast) {
		this.recast = recast;
	}

	public Boolean getSplit() {
		return split;
	}

	public void setSplit(Boolean split) {
		this.split = split;
	}

	public Boolean getCostingFlg() {
		return costingFlg;
	}

	public void setCostingFlg(Boolean costingFlg) {
		this.costingFlg = costingFlg;
	}

	public Integer getCostingMtId() {
		return costingMtId;
	}

	public void setCostingMtId(Integer costingMtId) {
		this.costingMtId = costingMtId;
	}

	public Boolean getJobWorkFlg() {
		return jobWorkFlg;
	}

	public void setJobWorkFlg(Boolean jobWorkFlg) {
		this.jobWorkFlg = jobWorkFlg;
	}

	public Boolean getBagCloseFlg() {
		return bagCloseFlg;
	}

	public void setBagCloseFlg(Boolean bagCloseFlg) {
		this.bagCloseFlg = bagCloseFlg;
	}

	public Integer getBarcodeSrNo() {
		return barcodeSrNo;
	}

	public void setBarcodeSrNo(Integer barcodeSrNo) {
		this.barcodeSrNo = barcodeSrNo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcodeType() {
		return barcodeType;
	}

	public void setBarcodeType(String barcodeType) {
		this.barcodeType = barcodeType;
	}

	public String getHuId() {
		return huId;
	}

	public void setHuId(String huId) {
		this.huId = huId;
	}
	
	
	
	

}
