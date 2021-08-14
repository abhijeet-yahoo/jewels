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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "jobrecdt")
public class JobRecDt {

	@Id
	@GeneratedValue
	@Column(name = "DtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private JobRecMt jobRecMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StyleId ")
	private Design design;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SordDtId")
	private OrderDt orderDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrdPartyId")
	private Party party;

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
	
	@Column(name = "DiscAmount")
	private Double discAmount = 0.0;

	@Column(name = "DispPercDt")
	private Double dispPercentDt = 0.0;

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
	
	@Column(name = "CostTransfer")
	private Boolean costTransfer = false;
	
	@Column(name = "TranMtId")
	private Integer tranMtId ;
	
	@Column(name = "RefJobIssMtId")
	private Integer refJobIssMtId;
	
	@Column(name = "RefJobIssDtId")
	private Integer refJobIssDtId;
	
	
	@Column(name="AdjustedQty")
	private Double adjustedQty = 0.0;
	
	@Column(name = "LossPercDt")
	private Double lossPercDt = 0.0;
	
	@Column(name = "SetNo")
	private Integer setNo;
	
	@Column(name = "Process")
	private String process;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JobRecMt getJobRecMt() {
		return jobRecMt;
	}

	public void setJobRecMt(JobRecMt jobRecMt) {
		this.jobRecMt = jobRecMt;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
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

	public Double getDiscAmount() {
		return discAmount;
	}

	public void setDiscAmount(Double discAmount) {
		this.discAmount = discAmount;
	}

	public Double getDispPercentDt() {
		return dispPercentDt;
	}

	public void setDispPercentDt(Double dispPercentDt) {
		this.dispPercentDt = dispPercentDt;
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

	public Boolean getCostTransfer() {
		return costTransfer;
	}

	public void setCostTransfer(Boolean costTransfer) {
		this.costTransfer = costTransfer;
	}



	public Integer getTranMtId() {
		return tranMtId;
	}

	public void setTranMtId(Integer tranMtId) {
		this.tranMtId = tranMtId;
	}

	public Double getAdjustedQty() {
		return adjustedQty;
	}

	public void setAdjustedQty(Double adjustedQty) {
		this.adjustedQty = adjustedQty;
	}

	public Double getLossPercDt() {
		return lossPercDt;
	}

	public void setLossPercDt(Double lossPercDt) {
		this.lossPercDt = lossPercDt;
	}

	public Integer getSetNo() {
		return setNo;
	}

	public void setSetNo(Integer setNo) {
		this.setNo = setNo;
	}

	public Integer getRefJobIssMtId() {
		return refJobIssMtId;
	}

	public void setRefJobIssMtId(Integer refJobIssMtId) {
		this.refJobIssMtId = refJobIssMtId;
	}

	public Integer getRefJobIssDtId() {
		return refJobIssDtId;
	}

	public void setRefJobIssDtId(Integer refJobIssDtId) {
		this.refJobIssDtId = refJobIssDtId;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	
	
	
}
