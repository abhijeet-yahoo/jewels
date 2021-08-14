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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;

@Entity
@Table(name = "castdt")
public class CastingDt {

	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@Column(name = "TransferDate")
	private Date transferDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeId", referencedColumnName = "MtId")
	private CastingMt castingMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;

	@Column(name = "MetalWt")
	private Double metalWt = 0.0;

	@Column(name = "Qty")
	private Double qty = 0.0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId", referencedColumnName = "DeptId")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptTo", referencedColumnName = "DeptId")
	private Department deptTo;

	@Column(name = "Transfer")
	private Boolean transfer =false;
	
	@Column(name = "TransferTranMtId")
	private Integer transferTranMtId;

	@Column(name = "RefMtId")
	private Integer refMtId;
	
	@Column(name = "RefTranMetalId")
	private Integer refTranMetalId;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SordMtId", referencedColumnName = "MtId")
	private OrderMt orderMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SordDtId", referencedColumnName = "DtId")
	private OrderDt orderDt;
	
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
	
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public CastingMt getCastingMt() {
		return castingMt;
	}

	public void setCastingMt(CastingMt castingMt) {
		this.castingMt = castingMt;
	}

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public Double getMetalWt() {
		return metalWt;
	}

	public void setMetalWt(Double metalWt) {
		this.metalWt = metalWt;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDeptTo() {
		return deptTo;
	}

	public void setDeptTo(Department deptTo) {
		this.deptTo = deptTo;
	}

	public Boolean getTransfer() {
		return transfer;
	}

	public void setTransfer(Boolean transfer) {
		this.transfer = transfer;
	}

	public Integer getRefMtId() {
		return refMtId;
	}

	public void setRefMtId(Integer refMtId) {
		this.refMtId = refMtId;
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

	public Integer getRefTranMetalId() {
		return refTranMetalId;
	}

	public void setRefTranMetalId(Integer refTranMetalId) {
		this.refTranMetalId = refTranMetalId;
	}

	public Integer getTransferTranMtId() {
		return transferTranMtId;
	}

	public void setTransferTranMtId(Integer transferTranMtId) {
		this.transferTranMtId = transferTranMtId;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}


	
	

}