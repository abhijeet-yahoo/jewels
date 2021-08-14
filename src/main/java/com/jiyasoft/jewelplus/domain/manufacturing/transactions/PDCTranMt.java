package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.sql.Time;
import java.text.SimpleDateFormat;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;

@Entity
@Table(name = "pdctranmt")
public class PDCTranMt {
	@Id
	@GeneratedValue
	@Column(name = "MtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StyleId")
	private Design design;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptFrom", referencedColumnName = "DeptId")
	private Department deptFrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptTo", referencedColumnName = "DeptId")
	private Department deptTo;

	@Column(name = "Pcs")
	private Double pcs = 0.0;

	@Column(name = "RecWt")
	private Double recWt = 0.0;

	@Column(name = "IssWt")
	private Double issWt = 0.0;

	@Column(name = "Loss")
	private Double loss = 0.0;

	@Column(name = "CurrStk")
	private Boolean currStk;

	@Column(name = "Time")
	private Time time;

	@Column(name = "SplitQty")
	private Double splitQty = 0.0;

	@Column(name = "SplitWt")
	private Double splitWt = 0.0;

	@Column(name = "ExtraLoss")
	private Double extraLoss = 0.0;

	@Column(name = "ExcessWt")
	private Double excessWt = 0.0;

	@Column(name = "IssueDate")
	private Date issueDate;

	@Column(name = "PurityConv")
	private Double purityConv = 0.0;

	@Column(name = "RefMtId")
	private Integer refMtId;

	@Column(name = "Remark")
	private String remark;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

	@Column(name = "Deactive")
	private Boolean deactive;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	
	public String getDateStr(){
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		return sd.format(createdDate);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDeptFrom() {
		return deptFrom;
	}

	public void setDeptFrom(Department deptFrom) {
		this.deptFrom = deptFrom;
	}

	public Department getDeptTo() {
		return deptTo;
	}

	public void setDeptTo(Department deptTo) {
		this.deptTo = deptTo;
	}

	public Double getRecWt() {
		return recWt;
	}

	public void setRecWt(Double recWt) {
		this.recWt = recWt;
	}

	public Double getIssWt() {
		return issWt;
	}

	public void setIssWt(Double issWt) {
		this.issWt = issWt;
	}

	public Double getLoss() {
		return loss;
	}

	public void setLoss(Double loss) {
		this.loss = loss;
	}

	public Boolean getCurrStk() {
		return currStk;
	}

	public void setCurrStk(Boolean currStk) {
		this.currStk = currStk;
	}


	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Double getSplitQty() {
		return splitQty;
	}

	public void setSplitQty(Double splitQty) {
		this.splitQty = splitQty;
	}

	public Double getSplitWt() {
		return splitWt;
	}

	public void setSplitWt(Double splitWt) {
		this.splitWt = splitWt;
	}

	public Double getExtraLoss() {
		return extraLoss;
	}

	public void setExtraLoss(Double extraLoss) {
		this.extraLoss = extraLoss;
	}

	public Double getExcessWt() {
		return excessWt;
	}

	public void setExcessWt(Double excessWt) {
		this.excessWt = excessWt;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
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

	public Double getPcs() {
		return pcs;
	}

	public void setPcs(Double pcs) {
		this.pcs = pcs;
	}



}
