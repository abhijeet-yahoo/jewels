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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;


@Entity
@Table(name="packmetalrate")
public class PackMetalRate {

	@Id
	@GeneratedValue
	@Column(name = "PackMetalRateId")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MtId")
	private PackMt packMt;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MetalId")
	private Metal metal;
	
	
	@Column(name = "Rate")
	private Double rate = 0.0;
	
	
	@Column(name = "LossPerc")
	private Double lossPerc = 0.0;
	
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PackMt getPackMt() {
		return packMt;
	}

	public void setPackMt(PackMt packMt) {
		this.packMt = packMt;
	}

	public Metal getMetal() {
		return metal;
	}

	public void setMetal(Metal metal) {
		this.metal = metal;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getLossPerc() {
		return lossPerc;
	}

	public void setLossPerc(Double lossPerc) {
		this.lossPerc = lossPerc;
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

	
	
	
	
}
