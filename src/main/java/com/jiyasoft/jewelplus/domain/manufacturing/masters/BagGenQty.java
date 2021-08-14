package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "baggenqty")
public class BagGenQty {
	
	@Id
	@GeneratedValue
	@Column(name = "BagGenQtyId")
	private Integer id;
	
	@Column(name = "FromCtsWt")
	private Double fromCtsWt;
	
	@Column(name = "ToCtsWt")
	private Double toCtsWt;
	
	@Column(name = "Qty")
	private Double qty =0.0;;
	
	
	@Column(name = "CreatedBy" , updatable = false)
	private String createdBy;
	
	@Column(name = "CreatedDt" , updatable = false)
	private Date createdDate;
	
	@Column(name = "ModiBy" , insertable = false)
	private String modiBy;
	
	@Column(name = "ModiDt" , insertable = false)
	private Date modiDate;
	
	@Column(name = "Deactive")
	private boolean deactive =false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getFromCtsWt() {
		return fromCtsWt;
	}

	public void setFromCtsWt(Double fromCtsWt) {
		this.fromCtsWt = fromCtsWt;
	}



	public Double getToCtsWt() {
		return toCtsWt;
	}

	public void setToCtsWt(Double toCtsWt) {
		this.toCtsWt = toCtsWt;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
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
	

	
}
