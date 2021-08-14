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
@Table(name = "designmold")
public class DesignMold {

	@Id
	@GeneratedValue
	@Column(name = "DesignMoldId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StyleId")
	private Design design;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DesignMoldTypeId")
	private DesignMoldType designMoldType;

	@Column(name = "RackNo")
	private String rackNo;

	@Column(name = "DrawerNo")
	private String drawerNo;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;
	
	@Column(name="MouldQty")
	private Double mouldQty;

	public Integer getId() {
		return id;
	}

	public Double getMouldQty() {
		return mouldQty;
	}

	public void setMouldQty(Double mouldQty) {
		this.mouldQty = mouldQty;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public DesignMoldType getDesignMoldType() {
		return designMoldType;
	}

	public void setDesignMoldType(DesignMoldType designMoldType) {
		this.designMoldType = designMoldType;
	}

	public String getRackNo() {
		return rackNo;
	}

	public void setRackNo(String rackNo) {
		this.rackNo = rackNo;
	}

	public String getDrawerNo() {
		return drawerNo;
	}

	public void setDrawerNo(String drawerNo) {
		this.drawerNo = drawerNo;
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

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

}
