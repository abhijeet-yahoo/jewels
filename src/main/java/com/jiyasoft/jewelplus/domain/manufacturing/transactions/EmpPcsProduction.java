package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.sql.Time;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProdLabourType;

@Entity
@Table(name = "emppcsprod")
public class EmpPcsProduction {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TranMtId")
	private TranMt tranMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeptId")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EmployeeId")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProdLabTypeId")
	private ProdLabourType prodLabType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOrdDtId")
	private OrderDt orderDt;

	@Column(name = "BagPcs")
	private Double bagPcs = 0.0;

	@Column(name = "ProdPcs")
	private Double prodPcs = 0.0;

	@Column(name = "Time")
	private Time time;

	@Column(name = "RecWt")
	private Double recWt = 0.0;

	@Column(name = "IssWt")
	private Double issWt = 0.0;

	@Column(name = "Loss")
	private Double loss = 0.0;

	@Column(name = "Rate")
	private Double rate = 0.0;

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

	public TranMt getTranMt() {
		return tranMt;
	}

	public void setTranMt(TranMt tranMt) {
		this.tranMt = tranMt;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public BagMt getBagMt() {
		return bagMt;
	}

	public void setBagMt(BagMt bagMt) {
		this.bagMt = bagMt;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ProdLabourType getProdLabType() {
		return prodLabType;
	}

	public void setProdLabType(ProdLabourType prodLabType) {
		this.prodLabType = prodLabType;
	}

	public OrderDt getOrderDt() {
		return orderDt;
	}

	public void setOrderDt(OrderDt orderDt) {
		this.orderDt = orderDt;
	}

	public Double getBagPcs() {
		return bagPcs;
	}

	public void setBagPcs(Double bagPcs) {
		this.bagPcs = bagPcs;
	}

	public Double getProdPcs() {
		return prodPcs;
	}

	public void setProdPcs(Double prodPcs) {
		this.prodPcs = prodPcs;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Double getRecWt() {
		return recWt;
	}

	public void setRecWt(Double recWt) {
		this.recWt = recWt;
	}

	public Double getIssWt() {
		return issWt;
	}

	public void setIssWt(Double issWt) {
		this.issWt = issWt;
	}

	public Double getLoss() {
		return loss;
	}

	public void setLoss(Double loss) {
		this.loss = loss;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
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
