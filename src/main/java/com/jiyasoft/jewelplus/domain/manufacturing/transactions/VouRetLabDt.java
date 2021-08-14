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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;

@Entity
@Table(name ="vouretlabdt")
public class VouRetLabDt {

	@Id
	@GeneratedValue
	@Column(name ="VouRetLabId")
	private Integer id;
	
	@Column(name ="GramWise")
	private Boolean gramWise = false;

	@Column(name ="LabourRate")
	private Double labourRate = 0.0;

	@Column(name ="LabourValue")
	private Double LabourValue = 0.0;
	
	@Column(name ="PcsWise")
	private Boolean pcsWise =false;
	
	@Column(name ="PerCaratRate")
	private Boolean perCarataRate =false;
	
	@Column(name ="PercentWise")
	private Boolean percentWise = false;
	
	@Column(name ="RLock")
	private Boolean rLock = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="LabTypeId")
	private LabourType labourtype;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="MetalId")
	private Metal metal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="DtId")
	private VouRetDt vouRetDt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="MtId")
	private VouRetMt vouRetMt;
	
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
		return LabourValue;
	}

	public void setLabourValue(Double labourValue) {
		LabourValue = labourValue;
	}

	public Boolean getPcsWise() {
		return pcsWise;
	}

	public void setPcsWise(Boolean pcsWise) {
		this.pcsWise = pcsWise;
	}

	public Boolean getPerCarataRate() {
		return perCarataRate;
	}

	public void setPerCarataRate(Boolean perCarataRate) {
		this.perCarataRate = perCarataRate;
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

	public LabourType getLabourtype() {
		return labourtype;
	}

	public void setLabourtype(LabourType labourtype) {
		this.labourtype = labourtype;
	}

	public Metal getMetal() {
		return metal;
	}

	public void setMetal(Metal metal) {
		this.metal = metal;
	}

	public VouRetDt getVouRetDt() {
		return vouRetDt;
	}

	public void setVouRetDt(VouRetDt vouRetDt) {
		this.vouRetDt = vouRetDt;
	}

	public VouRetMt getVouRetMt() {
		return vouRetMt;
	}

	public void setVouRetMt(VouRetMt vouRetMt) {
		this.vouRetMt = vouRetMt;
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
	
	
}
