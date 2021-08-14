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
@Table(name = "jobrecmetaldt")
public class JobRecMetalDt {

	@Id
	@GeneratedValue
	@Column(name = "JobRecMetalId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DtId")
	private JobRecDt jobRecDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private JobRecMt jobRecMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PurityId")
	private Purity purity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColorId")
	private Color color;

	@Column(name = "MetalWeight")
	private Double metalWeight = 0.0;

	@Column(name = "MetalPcs")
	private Integer metalPcs = 0;

	@Column(name = "MetalRate")
	private Double metalRate = 0.0;

	@Column(name = "PerGramRate")
	private Double perGramRate = 0.0;

	@Column(name = "LossPerc")
	private Double lossPerc = 0.0;

	@Column(name = "MetalValue")
	private Double metalValue = 0.0;

	@Column(name = "MainMetal")
	private Boolean mainMetal = false;

	@Column(name = "Rlock")
	private Boolean rLock = false;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name = "PerPcRate")
	private Boolean perPcRate = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JobRecDt getJobRecDt() {
		return jobRecDt;
	}

	public void setJobRecDt(JobRecDt jobRecDt) {
		this.jobRecDt = jobRecDt;
	}

	public JobRecMt getJobRecMt() {
		return jobRecMt;
	}

	public void setJobRecMt(JobRecMt jobRecMt) {
		this.jobRecMt = jobRecMt;
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

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public Double getMetalRate() {
		return metalRate;
	}

	public void setMetalRate(Double metalRate) {
		this.metalRate = metalRate;
	}

	public Double getPerGramRate() {
		return perGramRate;
	}

	public void setPerGramRate(Double perGramRate) {
		this.perGramRate = perGramRate;
	}

	public Double getLossPerc() {
		return lossPerc;
	}

	public void setLossPerc(Double lossPerc) {
		this.lossPerc = lossPerc;
	}

	public Double getMetalValue() {
		return metalValue;
	}

	public void setMetalValue(Double metalValue) {
		this.metalValue = metalValue;
	}

	public Boolean getMainMetal() {
		return mainMetal;
	}

	public void setMainMetal(Boolean mainMetal) {
		this.mainMetal = mainMetal;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Boolean getPerPcRate() {
		return perPcRate;
	}

	public void setPerPcRate(Boolean perPcRate) {
		this.perPcRate = perPcRate;
	}
	
	
}
