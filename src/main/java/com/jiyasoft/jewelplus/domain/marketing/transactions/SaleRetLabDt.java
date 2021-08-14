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
@Table(name ="saleretlabdt")
public class SaleRetLabDt {


	@Id
	@GeneratedValue
	@Column(name ="SaleRetLabId")
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
	private SaleRetDt saleRetDt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="Mtid")
	private SaleRetMt saleRetMt;
	
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
	
	@Column(name ="RefTranType")
	private String refTranType;
	
	@Column(name ="RefSaleDtId")
	private Integer refSaleDtId;

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

	public SaleRetDt getSaleRetDt() {
		return saleRetDt;
	}

	public void setSaleRetDt(SaleRetDt saleRetDt) {
		this.saleRetDt = saleRetDt;
	}

	public SaleRetMt getSaleRetMt() {
		return saleRetMt;
	}

	public void setSaleRetMt(SaleRetMt saleRetMt) {
		this.saleRetMt = saleRetMt;
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

	public String getRefTranType() {
		return refTranType;
	}

	public void setRefTranType(String refTranType) {
		this.refTranType = refTranType;
	}

	public Integer getRefSaleDtId() {
		return refSaleDtId;
	}

	public void setRefSaleDtId(Integer refSaleDtId) {
		this.refSaleDtId = refSaleDtId;
	}
	
	
}
