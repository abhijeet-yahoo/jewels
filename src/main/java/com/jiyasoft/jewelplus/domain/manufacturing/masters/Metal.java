package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//fields

@Entity
@Table(name = "metalmast")
public class Metal 
{
	@Id
	@GeneratedValue
	@Column(name = "MetalId")
	private Integer id;
	
	@Column(name = "MetalNm")
	private String name;
	
	@OneToMany(mappedBy = "metal", cascade = CascadeType.DETACH)
	private List<Purity> purity;
	
	@Column(name = "TzRate")
	private  Double tzRate;
	
	@Column(name = "SpecificGravity")
	private  Double specificGravity;
	
	@Column(name = "CreatedBy" , updatable = false)
	private String createdBy;
	
	@Column(name = "CreatedDt" , updatable = false)
	private Date createdDate;
	
	@Column(name = "ModiBy" , insertable = false)
	private String modiBy;
	
	@Column(name = "ModiDt" , insertable = false)
	private Date modiDate;
	
	@Column(name = "Deactive")
	private Boolean deactive;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name = "CompId")
	private Integer compId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Purity> getPurity() {
		return purity;
	}

	public void setPurity(List<Purity> purity) {
		this.purity = purity;
	}

	public Double getTzRate() {
		return tzRate;
	}

	public void setTzRate(Double tzRate) {
		this.tzRate = tzRate;
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

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Double getSpecificGravity() {
		return specificGravity;
	}

	public void setSpecificGravity(Double specificGravity) {
		this.specificGravity = specificGravity;
	}
	
	
	

	
}
