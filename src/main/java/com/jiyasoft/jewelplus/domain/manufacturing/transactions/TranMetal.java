package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.sql.Time;
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
@Table(name = "tranmetal")

public class TranMetal {
	
	@Id
	@GeneratedValue
	@Column(name = "TranMetalId")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;

	@Column(name = "CurrStk")
	private Boolean currStk;
	
	@Column(name ="MainMetal")
	private Boolean mainMetal=false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private TranMt tranMt;
	
	@Column(name = "TranDate")
	private Date tranDate;

	@Column(name = "Time")
	private Time time;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptFrom", referencedColumnName = "DeptId")
	private Department deptFrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptTo", referencedColumnName = "DeptId")
	private Department deptTo;

	@Column(name = "IssDate")
	private Date issDate;

	@Column(name = "RefTranMetalId")
	private Integer refTranMetalId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	
	@Column(name = "PurityConv")
	private Double purityConv = 0.0;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "MetalWeight")
	private Double metalWeight = 0.0;
	
	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "MetalPcs")
	private Integer metalPcs = 0;
	
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public TranMt getTranMt() {
		return tranMt;
	}

	public void setTranMt(TranMt tranMt) {
		this.tranMt = tranMt;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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

	public Date getIssDate() {
		return issDate;
	}

	public void setIssDate(Date issDate) {
		this.issDate = issDate;
	}

	public Integer getRefTranMetalId() {
		return refTranMetalId;
	}

	public void setRefTranMetalId(Integer refTranMetalId) {
		this.refTranMetalId = refTranMetalId;
	}

	public Purity getPurity() {
		return purity;
	}

	public void setPurity(Purity purity) {
		this.purity = purity;
	}

	public LookUpMast getPartNm() {
		return partNm;
	}

	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Double getMetalWeight() {
		return metalWeight;
	}

	public void setMetalWeight(Double metalWeight) {
		this.metalWeight = metalWeight;
	}

	public Integer getMetalPcs() {
		return metalPcs;
	}

	public void setMetalPcs(Integer metalPcs) {
		this.metalPcs = metalPcs;
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



	public Boolean getCurrStk() {
		return currStk;
	}

	public void setCurrStk(Boolean currStk) {
		this.currStk = currStk;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public Boolean getMainMetal() {
		return mainMetal;
	}

	public void setMainMetal(Boolean mainMetal) {
		this.mainMetal = mainMetal;
	}

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	
	
	
	
	
	

}
