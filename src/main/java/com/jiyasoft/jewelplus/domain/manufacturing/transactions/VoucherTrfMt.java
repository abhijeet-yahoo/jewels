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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;

@Entity
@Table(name = "vouchertrfmt")
public class VoucherTrfMt {

	
	@Id
	@GeneratedValue
	@Column(name ="VoucherTrfMtId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptFrom", referencedColumnName = "DeptId")
	private Department deptFrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptTo", referencedColumnName = "DeptId")
	private Department deptTo;
	
	@Column(name = "VoucherNo")
	private String voucherno;
	
	@Column(name = "VoucherDate")
	private Date voucherdate;
	
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;
	
	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

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

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public Department getDeptFrom() {
		return deptFrom;
	}

	public void setDeptFrom(Department deptFrom) {
		this.deptFrom = deptFrom;
	}

	public Department getDeptTo() {
		return deptTo;
	}

	public void setDeptTo(Department deptTo) {
		this.deptTo = deptTo;
	}


	public Date getVoucherdate() {
		return voucherdate;
	}

	public void setVoucherdate(Date voucherdate) {
		this.voucherdate = voucherdate;
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

	public String getVoucherno() {
		return voucherno;
	}

	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}
	
	
}
