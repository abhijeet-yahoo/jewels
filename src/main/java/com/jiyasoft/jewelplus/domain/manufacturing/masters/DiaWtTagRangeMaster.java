package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diawttagrange")
public class DiaWtTagRangeMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "diaWtTagRangeId")
	private Integer id;
	
	@Column(name = "FromCts")
	private Double fromCts = 0.0;
	
	@Column(name = "ToCts")
	private Double toCts = 0.0;
	
	@Column(name = "AddedWt")
	private Double addedWt = 0.0;
	
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

	public Double getFromCts() {
		return fromCts;
	}

	public void setFromCts(Double fromCts) {
		this.fromCts = fromCts;
	}

	public Double getToCts() {
		return toCts;
	}

	public void setToCts(Double toCts) {
		this.toCts = toCts;
	}

	public Double getAddedWt() {
		return addedWt;
	}

	public void setAddedWt(Double addedWt) {
		this.addedWt = addedWt;
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
	
	
	
	


}
