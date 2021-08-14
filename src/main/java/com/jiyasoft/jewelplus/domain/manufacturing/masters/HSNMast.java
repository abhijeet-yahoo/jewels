package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="hsnmast")
public class HSNMast {

	@Id
	@GeneratedValue
	@Column(name = "HSNId")
	private Integer id;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "HSNDesc")
	private String desc;
	
	@Column(name = "HSNNo ")
	private Integer hsnNo;
	
	@Column(name ="CGST")
	private Double cGST =0.0;
	
	@Column(name ="SGST")
	private Double sGST =0.0;
	
	@Column(name ="IGST")
	private Double iGST =0.0;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getHsnNo() {
		return hsnNo;
	}

	public void setHsnNo(Integer hsnNo) {
		this.hsnNo = hsnNo;
	}

	public Double getcGST() {
		return cGST;
	}

	public void setcGST(Double cGST) {
		this.cGST = cGST;
	}

	public Double getsGST() {
		return sGST;
	}

	public void setsGST(Double sGST) {
		this.sGST = sGST;
	}

	public Double getiGST() {
		return iGST;
	}

	public void setiGST(Double iGST) {
		this.iGST = iGST;
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
