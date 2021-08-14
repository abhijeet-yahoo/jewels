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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

@Entity
@Table(name = "metaltran")
public class MetalTran {

	@Id
	@GeneratedValue
	@Column(name = "MetalTranId")
	private Integer id;

	@Column(name = "TranDate")
	private Date tranDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationId", referencedColumnName = "DeptId")
	private Department deptLocation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "MetalWt")
	private Double metalWt = 0.0;
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "Balance")
	private Double balance = 0.0;

	/*@Column(name = "UMetalWt")
	private Double uMetalWt = 0.0;

	@Column(name = "UBalance")
	private Double uBalance = 0.0;
*/
	@Column(name = "InOutFld")
	private String inOutFld;

	@Column(name = "PurityConv")
	private Double purityConv = 0.0;

	@Column(name = "Remark")
	private String remark;

	@Column(name = "PcsWt")
	private Boolean pcsWt = false;

	@Column(name = "StubWt")
	private Boolean stubWt = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;

	@Column(name = "TranType")
	private String tranType;

	@Column(name = "RefTranId")
	private Integer refTranId;
	
	@Column(name = "TranMtId")
	private Integer tranMtId;

	@Column(name = "PDStyleId")
	private Integer styleId;

	@Column(name = "SrNo")
	private Integer srNo;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDeptLocation() {
		return deptLocation;
	}

	public void setDeptLocation(Department deptLocation) {
		this.deptLocation = deptLocation;
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

	public Double getMetalWt() {
		return metalWt;
	}

	public void setMetalWt(Double metalWt) {
		this.metalWt = metalWt;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/*public Double getuMetalWt() {
		return uMetalWt;
	}

	public void setuMetalWt(Double uMetalWt) {
		this.uMetalWt = uMetalWt;
	}

	public Double getuBalance() {
		return uBalance;
	}

	public void setuBalance(Double uBalance) {
		this.uBalance = uBalance;
	}
*/
	public String getInOutFld() {
		return inOutFld;
	}

	public void setInOutFld(String inOutFld) {
		this.inOutFld = inOutFld;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getPcsWt() {
		return pcsWt;
	}

	public void setPcsWt(Boolean pcsWt) {
		this.pcsWt = pcsWt;
	}

	public Boolean getStubWt() {
		return stubWt;
	}

	public void setStubWt(Boolean stubWt) {
		this.stubWt = stubWt;
	}

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
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

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
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

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public Integer getTranMtId() {
		return tranMtId;
	}

	public void setTranMtId(Integer tranMtId) {
		this.tranMtId = tranMtId;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}
	
	
	
	

}
