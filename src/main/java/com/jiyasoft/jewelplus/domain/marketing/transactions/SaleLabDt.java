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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;

@Entity
@Table(name ="salelabdt")
public class SaleLabDt {
	
	@Id
	@GeneratedValue
	@Column(name ="SaleLabId")
	private Integer id;
	
	@Column(name ="GramWise")
	private Boolean gramWise = false;
	
	@Column(name ="LabourRate")
	private Double labourRate = 0.0;

	@Column(name = "LabourValue")
	private Double labourValue = 0.0;
	
	@Column(name ="PcsWise")
	private Boolean pcsWise = false;
	
	@Column(name ="PerCaratRate")
	private Boolean perCaratRate = false;
	
	@Column(name ="PercentWise")
	private Boolean percentWise = false;
	
	@Column(name ="RLock")
	private Boolean rLock = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="LabTypeId")
	private LabourType labourType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="MetalId")
	private Metal metal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="Dtid")
	private SaleDt saleDt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="Mtid")
	private SaleMt saleMt;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;	

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;


	@Column(name = "PerGramRate")
	private Boolean perGramRate = false;
	
	@Column(name = "Percentage")
	private Boolean percentage = false;
	
	@Column(name = "PerPcRate")
	private Boolean perPcRate = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getGramWise() {
		return gramWise;
	}

	public void setGramWise(Boolean gramWise) {
		this.gramWise = gramWise;
	}

	public Double getLabourRate() {
		return labourRate;
	}

	public void setLabourRate(Double labourRate) {
		this.labourRate = labourRate;
	}

	public Double getLabourValue() {
		return labourValue;
	}

	public void setLabourValue(Double labourValue) {
		this.labourValue = labourValue;
	}

	public Boolean getPcsWise() {
		return pcsWise;
	}

	public void setPcsWise(Boolean pcsWise) {
		this.pcsWise = pcsWise;
	}

	public Boolean getPerCaratRate() {
		return perCaratRate;
	}

	public void setPerCaratRate(Boolean perCaratRate) {
		this.perCaratRate = perCaratRate;
	}

	public Boolean getPercentWise() {
		return percentWise;
	}

	public void setPercentWise(Boolean percentWise) {
		this.percentWise = percentWise;
	}

	public Boolean getrLock() {
		return rLock;
	}

	public void setrLock(Boolean rLock) {
		this.rLock = rLock;
	}

	public LabourType getLabourType() {
		return labourType;
	}

	public void setLabourType(LabourType labourType) {
		this.labourType = labourType;
	}

	public Metal getMetal() {
		return metal;
	}

	public void setMetal(Metal metal) {
		this.metal = metal;
	}

	public SaleDt getSaleDt() {
		return saleDt;
	}

	public void setSaleDt(SaleDt saleDt) {
		this.saleDt = saleDt;
	}

	public SaleMt getSaleMt() {
		return saleMt;
	}

	public void setSaleMt(SaleMt saleMt) {
		this.saleMt = saleMt;
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


	public Boolean getPerGramRate() {
		return perGramRate;
	}

	public void setPerGramRate(Boolean perGramRate) {
		this.perGramRate = perGramRate;
	}

	public Boolean getPercentage() {
		return percentage;
	}

	public void setPercentage(Boolean percentage) {
		this.percentage = percentage;
	}

	public Boolean getPerPcRate() {
		return perPcRate;
	}

	public void setPerPcRate(Boolean perPcRate) {
		this.perPcRate = perPcRate;
	}
	
	

	
}
