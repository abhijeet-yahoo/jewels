package com.jiyasoft.jewelplus.domain.marketing.transactions;

import java.text.SimpleDateFormat;
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
@Table(name = "stockmeltingmt")
public class StockMeltingMt {
	
	@Id
	@GeneratedValue
	@Column(name = "MtId")
	private Integer id;

	@Column(name = "InvNo")
	private String invNo;

	@Column(name = "InvSrNo")
	private Integer invSrNo;

	
	@Column(name = "InvDate")
	private Date invDate;



	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="LocationId", referencedColumnName ="DeptId")
	private Department location;
	
	

	public String getInvDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(invDate);
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getInvNo() {
		return invNo;
	}



	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}



	public Integer getInvSrNo() {
		return invSrNo;
	}



	public void setInvSrNo(Integer invSrNo) {
		this.invSrNo = invSrNo;
	}



	public Date getInvDate() {
		return invDate;
	}



	public void setInvDate(Date invDate) {
		this.invDate = invDate;
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



	public Department getLocation() {
		return location;
	}



	public void setLocation(Department location) {
		this.location = location;
	}
	
	
	


}
