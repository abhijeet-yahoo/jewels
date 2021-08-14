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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "costjobdt")
public class CostingJobDt {

	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private CostingJobMt costingJobMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StyleId ")
	private Design design;

	@Column(name = "StyleNo")
	private String styleNo;

	@Column(name = "Version")
	private String version;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;

	@Column(name = "BagNm")
	private String bagNm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@Column(name = "PurityNm")
	private String purityNm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "ColorNm")
	private String colorNm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SordDtId")
	private OrderDt orderDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@Column(name = "GrossWt")
	private Double grossWt = 0.0;

	@Column(name = "NetWt")
	private Double netWt = 0.0;

	@Column(name = "perGmWt")
	private Double perGmWt = 0.0;

	@Column(name = "Pcs")
	private Double pcs = 0.0;

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

	@Column(name = "FinalPrice")
	private Double finalPrice = 0.0;

	@Column(name = "Rlock")
	private Boolean rLock = false;

	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "PurityConv")
	private Double purityConv = 0.0;

	@Column(name = "LossWt")
	private Double lossWt = 0.0;

	@Column(name = "Other")
	private Double other = 0.0;

	@Column(name = "ClientPurityConv")
	private Double clientPurityConv = 0.0;

	@Column(name = "BlackRhod")
	private Boolean blackRhod = false;

	@Column(name = "ColorRhod ")
	private Boolean colorRhod = false;

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

	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CostingJobMt getCostingJobMt() {
		return costingJobMt;
	}

	public void setCostingJobMt(CostingJobMt costingJobMt) {
		this.costingJobMt = costingJobMt;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public String getBagNm() {
		return bagNm;
	}

	public void setBagNm(String bagNm) {
		this.bagNm = bagNm;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}

	public String getPurityNm() {
		return purityNm;
	}

	public void setPurityNm(String purityNm) {
		this.purityNm = purityNm;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getColorNm() {
		return colorNm;
	}

	public void setColorNm(String colorNm) {
		this.colorNm = colorNm;
	}

	public OrderDt getOrderDt() {
		return orderDt;
	}

	public void setOrderDt(OrderDt orderDt) {
		this.orderDt = orderDt;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public Double getPerGmWt() {
		return perGmWt;
	}

	public void setPerGmWt(Double perGmWt) {
		this.perGmWt = perGmWt;
	}

	public Double getPcs() {
		return pcs;
	}

	public void setPcs(Double pcs) {
		this.pcs = pcs;
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

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public Double getLossWt() {
		return lossWt;
	}

	public void setLossWt(Double lossWt) {
		this.lossWt = lossWt;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public Double getClientPurityConv() {
		return clientPurityConv;
	}

	public void setClientPurityConv(Double clientPurityConv) {
		this.clientPurityConv = clientPurityConv;
	}

	public Boolean getBlackRhod() {
		return blackRhod;
	}

	public void setBlackRhod(Boolean blackRhod) {
		this.blackRhod = blackRhod;
	}

	public Boolean getColorRhod() {
		return colorRhod;
	}

	public void setColorRhod(Boolean colorRhod) {
		this.colorRhod = colorRhod;
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

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}

}