package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//feilds
@Entity
@Table(name="stonetypemast")
public class StoneType {
	
	@Id
	@GeneratedValue
	@Column(name="TypeId")
	private Integer id;
	
	@Column(name = "TypeNm")
	private String name;

	@Column(name = "Code")
	private String code;

	@Column(name = "TransferRatePerc")
	private Double transferRatePerc=0.0;

	@Column(name = "FactoryRatePerc")
	private Double factoryRatePerc=0.0;

	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private boolean deactive=false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;
	
	@Column(name = "CompId")
	private Integer compId;
	
	//getters and setters

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public boolean isDeactive() {
		return deactive;
	}

	public void setDeactive(boolean deactive) {
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

	public Double getTransferRatePerc() {
		return transferRatePerc;
	}

	public void setTransferRatePerc(Double transferRatePerc) {
		this.transferRatePerc = transferRatePerc;
	}

	public Double getFactoryRatePerc() {
		return factoryRatePerc;
	}

	public void setFactoryRatePerc(Double factoryRatePerc) {
		this.factoryRatePerc = factoryRatePerc;
	}


	
	

}
