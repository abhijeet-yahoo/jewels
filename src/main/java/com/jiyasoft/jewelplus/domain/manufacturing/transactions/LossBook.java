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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProdLabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

@Entity
@Table(name = "lossBook")
public class LossBook{
	
	@Id
	@GeneratedValue
	@Column(name = "LossBookId")
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
	@JoinColumn(name = "ProdLabourId")
	private ProdLabourType prodLabourType;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartNm")
	private LookUpMast partNm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EmployeeId")
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PurityId")
	private Purity purity;
	
	@Column(name="Qty")
	private Double qty=0.0;
	
	@Column(name="GrossWt")
	private Double grossWt;
	
	@Column(name="partWt")
	private Double partWt;
	
	@Column(name = "PurityConv")
	private Double purityConv = 0.0;
	
	@Column(name = "extraWt")
	private Double extraWt = 0.0;
	
	@Column(name="CurrentWt")
	private Double currentWt;
	
	@Column(name="Loss")
	private Double loss;

	@Column(name="LossPercOnIn")
	private Double lossPercOnIn;
	
	@Column(name="LossPercOnOut")
	private Double lossPercOnOut;
	
	@Column(name = "CurrStk")
	private Boolean currStk;
	
	@Column(name = "TranDate")
	private Date trandate;

	
	@Column(name = "CreatedBy" , updatable = false)
	private String createdBy;
	
	@Column(name = "CreatedDt" , updatable = false)
	private Date createdDate;
	
	@Column(name = "ModiBy" , insertable = false)
	private String modiBy;
	
	@Column(name = "ModiDt" , insertable = false)
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


	public ProdLabourType getProdLabourType() {
		return prodLabourType;
	}


	public void setProdLabourType(ProdLabourType prodLabourType) {
		this.prodLabourType = prodLabourType;
	}


	public LookUpMast getPartNm() {
		return partNm;
	}


	public void setPartNm(LookUpMast partNm) {
		this.partNm = partNm;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Purity getPurity() {
		return purity;
	}


	public void setPurity(Purity purity) {
		this.purity = purity;
	}




	public Double getGrossWt() {
		return grossWt;
	}


	public void setGrossWt(Double grossWt) {
		this.grossWt = grossWt;
	}


	


	public Double getPartWt() {
		return partWt;
	}


	public void setPartWt(Double partWt) {
		this.partWt = partWt;
	}


	public Double getPurityConv() {
		return purityConv;
	}


	public void setPurityConv(Double purityConv) {
		this.purityConv = purityConv;
	}


	public Double getCurrentWt() {
		return currentWt;
	}


	public void setCurrentWt(Double currentWt) {
		this.currentWt = currentWt;
	}


	public Double getLoss() {
		return loss;
	}


	public void setLoss(Double loss) {
		this.loss = loss;
	}


	public Boolean getCurrStk() {
		return currStk;
	}


	public void setCurrStk(Boolean currStk) {
		this.currStk = currStk;
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


	public Double getLossPercOnIn() {
		return lossPercOnIn;
	}


	public void setLossPercOnIn(Double lossPercOnIn) {
		this.lossPercOnIn = lossPercOnIn;
	}


	public Double getLossPercOnOut() {
		return lossPercOnOut;
	}


	public void setLossPercOnOut(Double lossPercOnOut) {
		this.lossPercOnOut = lossPercOnOut;
	}


	public Date getTrandate() {
		return trandate;
	}


	public void setTrandate(Date trandate) {
		this.trandate = trandate;
	}


	public Double getExtraWt() {
		return extraWt;
	}


	public void setExtraWt(Double extraWt) {
		this.extraWt = extraWt;
	}


	public Double getQty() {
		return qty;
	}


	public void setQty(Double qty) {
		this.qty = qty;
	}


	


	
	
	
}
