package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "puritymast")
public class Purity {

	@Id
	@GeneratedValue
	@Column(name = "PurityId")
	private Integer id;

	@Column(name = "PurityNm")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MetalId")
	private Metal metal;

	@Column(name = "WaxWtConv")
	private Double waxWtConv = 0.0;

	@Column(name = "PurityConv")
	private Double purityConv = 0.0;

	@Column(name = "Purity")
	private Double vPurity = 0.0;

	@Column(name = "DefPurity")
	private Boolean defPurity;

	@Column(name = "Pure")
	private Boolean pure =false;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

	@Column(name = "Deactive")
	private Boolean deactive =false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

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

	public Metal getMetal() {
		return metal;
	}

	public void setMetal(Metal metal) {
		this.metal = metal;
	}

	public Double getWaxWtConv() {
		return waxWtConv;
	}

	public void setWaxWtConv(Double waxWtConv) {
		this.waxWtConv = waxWtConv;
	}

	public Double getPurityConv() {
		return purityConv;
	}

	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}

	public Double getvPurity() {
		return vPurity;
	}

	public void setvPurity(Double vPurity) {
		this.vPurity = vPurity;
	}

	public Boolean getDefPurity() {
		return defPurity;
	}

	public void setDefPurity(Boolean defPurity) {
		this.defPurity = defPurity;
	}

	public Boolean getPure() {
		return pure;
	}

	public void setPure(Boolean pure) {
		this.pure = pure;
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
