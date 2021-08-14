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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

@Entity
@Table(name = "stockmt")
public class StockMt {

	@Id
	@GeneratedValue
	@Column(name = "MtId")
	private Integer mtId;
	
	@Column(name ="Barcode")
	private String barcode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationId")
	private Department location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MemoPartyId")
	private Party memoParty;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategId")
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="FromLocationId", referencedColumnName ="DeptId")
	private Department fromLocation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ToLocationId", referencedColumnName = "DeptId")
	private Department toLocation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StyleId")
	private Design design;
	
	@Column(name = "Qty")
	private Double qty = 0.0;
	
	@Column(name="BagId")
	private Integer bagId;
	
	@Column(name = "GrossWt")
	private Double grossWt = 0.0;
	
	@Column(name ="NetWt")
	private Double netWt = 0.0;
	
	@Column(name ="OtherWt")
	private Double otherWt = 0.0;
	
	@Column(name ="OtherValue")
	private Double otherVlaue = 0.0;
	
	@Column(name ="MetalValue")
	private Double metalValue = 0.0;
	
	@Column(name = "StoneValue")
	private Double stoneValue = 0.0;
	
	@Column(name ="LabourValue")
	private Double labourValue = 0.0;
	
	@Column(name ="FactoryCost")
	private Double factoryCost = 0.0;
	
	@Column(name = "MRP")
	private Double mrp = 0.0;
	
	@Column(name ="TranType")
	private String tranType;
	
	@Column(name ="RefTranId")
	private Integer refTranId;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive =false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name = "CurrStk")
	private Boolean currStk;
	

	
	@Column(name = "IssueDate")
	private Date issueDate;
	
	@Column(name ="TranDate")
	private Date tranDate;
	
	@Column(name ="RefMtId")
	private Integer refMtId;
	
	@Column(name = "Remark")
	private String remark;
	
	@Column(name ="HallMarking")
	private Double hallMarking = 0.0;
	
	@Column(name ="LazerMarking")
	private Double lazerMarking = 0.0;
	
	@Column(name ="Grading")
	private Double grading = 0.0;
	
	@Column(name ="Engraving")
	private Double engraving = 0.0;

	@Column(name ="HallMarkId")
	private String hallMarkId;
	
	public Integer getMtId() {
		return mtId;
	}

	public void setMtId(Integer mtId) {
		this.mtId = mtId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Department getLocation() {
		return location;
	}

	public void setLocation(Department location) {
		this.location = location;
	}

	public Party getMemoParty() {
		return memoParty;
	}

	public void setMemoParty(Party memoParty) {
		this.memoParty = memoParty;
	}

	public Department getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(Department fromLocation) {
		this.fromLocation = fromLocation;
	}

	public Department getToLocation() {
		return toLocation;
	}

	public void setToLocation(Department toLocation) {
		this.toLocation = toLocation;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
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

	public Double getLabourValue() {
		return labourValue;
	}

	public void setLabourValue(Double labourValue) {
		this.labourValue = labourValue;
	}

	public Double getFactoryCost() {
		return factoryCost;
	}

	public void setFactoryCost(Double factoryCost) {
		this.factoryCost = factoryCost;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public Integer getRefTranId() {
		return refTranId;
	}

	public void setRefTranId(Integer refTranId) {
		this.refTranId = refTranId;
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

	public Boolean getCurrStk() {
		return currStk;
	}

	public void setCurrStk(Boolean currStk) {
		this.currStk = currStk;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public Integer getRefMtId() {
		return refMtId;
	}

	public void setRefMtId(Integer refMtId) {
		this.refMtId = refMtId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getStoneValue() {
		return stoneValue;
	}

	public void setStoneValue(Double stoneValue) {
		this.stoneValue = stoneValue;
	}

	public Double getHallMarking() {
		return hallMarking;
	}

	public void setHallMarking(Double hallMarking) {
		this.hallMarking = hallMarking;
	}

	public Double getLazerMarking() {
		return lazerMarking;
	}

	public void setLazerMarking(Double lazerMarking) {
		this.lazerMarking = lazerMarking;
	}

	public Double getGrading() {
		return grading;
	}

	public void setGrading(Double grading) {
		this.grading = grading;
	}

	public Double getEngraving() {
		return engraving;
	}

	public void setEngraving(Double engraving) {
		this.engraving = engraving;
	}

	public Double getOtherWt() {
		return otherWt;
	}

	public void setOtherWt(Double otherWt) {
		this.otherWt = otherWt;
	}

	public Double getOtherVlaue() {
		return otherVlaue;
	}

	public void setOtherVlaue(Double otherVlaue) {
		this.otherVlaue = otherVlaue;
	}

	public String getHallMarkId() {
		return hallMarkId;
	}

	public void setHallMarkId(String hallMarkId) {
		this.hallMarkId = hallMarkId;
	}

	
	
	
}
