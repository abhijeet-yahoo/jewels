package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.util.Date;

import javax.persistence.CascadeType;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;

@Entity
@Table(name = "impstnadj")
public class DiamondBagging {
	@Id
	@GeneratedValue
	@Column(name = "ImpStnAdjId")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BagId")
	private BagMt bagMt;

	@Column(name = "BagPcs")
	private Double bagPcs;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ShapeId")
	private Shape shape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SubShapeId")
	private SubShape subShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QualityId")
	private Quality quality;

	@Column(name = "Size")
	private String size;

	@Column(name = "Sieve")
	private String sieve;

	@Column(name = "Stone")
	private Integer stone;

	@Column(name = "BalStone")
	private Integer balStone;

	@Column(name = "Carat")
	private Double carat;

	@Column(name = "BalCarat")
	private Double balCarat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SettingId")
	private Setting setting;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SettypeId")
	private SettingType settingType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EmployeeId")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StnRecDtId")
	private StoneInwardDt stoneInwardDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ChngDeptId")
	private Department department;

	@Column(name = "InOutFld")
	private String inOutFld = "C";

	@Column(name = "StnRate")
	private Double stnRate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOrdDtId")
	private OrderDt orderDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOrdStnDtId")
	private OrderStnDt orderStnDt;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDt;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

	@Column(name = "Deactive")
	private Boolean deactive = false;
	
	@Column(name = "CenterStone")
	private Boolean centerStone = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	@Column(name = "BagSrNo")
	private Integer bagSrNo = 0;

	@Column(name = "TranType")
	private String tranType;

	@Column(name = "RefTranId")
	private Integer refTranId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StoneTypeId")
	private StoneType stoneType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SizeGroupId")
	private SizeGroup sizeGroup;

	@Column(name = "Transfered")
	private Boolean transfered = false;

	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;

	@Column(name = "BaggingReturn")
	private Boolean baggingReturn = false;

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

	public Double getBagPcs() {
		return bagPcs;
	}

	public void setBagPcs(Double bagPcs) {
		this.bagPcs = bagPcs;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public SubShape getSubShape() {
		return subShape;
	}

	public void setSubShape(SubShape subShape) {
		this.subShape = subShape;
	}

	public Quality getQuality() {
		return quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
	}

	
	
	public Boolean getCenterStone() {
		return centerStone;
	}

	public void setCenterStone(Boolean centerStone) {
		this.centerStone = centerStone;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSieve() {
		return sieve;
	}

	public void setSieve(String sieve) {
		this.sieve = sieve;
	}

	public Integer getStone() {
		return stone;
	}

	public void setStone(Integer stone) {
		this.stone = stone;
	}

	public Integer getBalStone() {
		return balStone;
	}

	public void setBalStone(Integer balStone) {
		this.balStone = balStone;
	}

	public Double getCarat() {
		return carat;
	}

	public void setCarat(Double carat) {
		this.carat = carat;
	}

	public Double getBalCarat() {
		return balCarat;
	}

	public void setBalCarat(Double balCarat) {
		this.balCarat = balCarat;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public SettingType getSettingType() {
		return settingType;
	}

	public void setSettingType(SettingType settingType) {
		this.settingType = settingType;
	}

	public StoneInwardDt getStoneInwardDt() {
		return stoneInwardDt;
	}

	public void setStoneInwardDt(StoneInwardDt stoneInwardDt) {
		this.stoneInwardDt = stoneInwardDt;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getInOutFld() {
		return inOutFld;
	}

	public void setInOutFld(String inOutFld) {
		this.inOutFld = inOutFld;
	}

	public Double getStnRate() {
		return stnRate;
	}

	public void setStnRate(Double stnRate) {
		this.stnRate = stnRate;
	}

	public OrderDt getOrderDt() {
		return orderDt;
	}

	public void setOrderDt(OrderDt orderDt) {
		this.orderDt = orderDt;
	}

	public OrderStnDt getOrderStnDt() {
		return orderStnDt;
	}

	public void setOrderStnDt(OrderStnDt orderStnDt) {
		this.orderStnDt = orderStnDt;
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

	public Integer getBagSrNo() {
		return bagSrNo;
	}

	public void setBagSrNo(Integer bagSrNo) {
		this.bagSrNo = bagSrNo;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public Integer getRefTranId() {
		return refTranId;
	}

	public void setRefTranId(Integer refTranId) {
		this.refTranId = refTranId;
	}

	public StoneType getStoneType() {
		return stoneType;
	}

	public void setStoneType(StoneType stoneType) {
		this.stoneType = stoneType;
	}

	public SizeGroup getSizeGroup() {
		return sizeGroup;
	}

	public void setSizeGroup(SizeGroup sizeGroup) {
		this.sizeGroup = sizeGroup;
	}

	public Boolean getTransfered() {
		return transfered;
	}

	public void setTransfered(Boolean transfered) {
		this.transfered = transfered;
	}

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Boolean getBaggingReturn() {
		return baggingReturn;
	}

	public void setBaggingReturn(Boolean baggingReturn) {
		this.baggingReturn = baggingReturn;
	}

}
