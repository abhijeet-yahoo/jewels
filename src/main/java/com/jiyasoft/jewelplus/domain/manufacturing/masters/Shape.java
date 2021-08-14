package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shapemast")
public class Shape {

	@Id
	@GeneratedValue
	@Column(name = "ShapeId")
	private Integer id;

	@Column(name = "ShapeNm")
	private String name;

	@Column(name = "Code")
	private String code;

	@Column(name = "UpperTolerance")
	private Double  upperTolerance=0.0;

	
	@Column(name = "LowerTolerance")
	private Double  lowerTolerance=0.0;
	
	@OneToMany(mappedBy = "shape")
	private List<SubShape> subShape;

	@OneToMany(mappedBy = "shape", cascade = CascadeType.DETACH)
	private List<Quality> quality;

	@OneToMany(mappedBy = "shape")
	private List<StoneChart> stoneChart;

	@OneToMany(mappedBy = "shape")
	private List<SizeGroup> sizeGroup;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	@Column(name = "Deactive")
	private Boolean deactive=false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

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

	public List<SubShape> getSubShape() {
		return subShape;
	}

	public void setSubShape(List<SubShape> subShape) {
		this.subShape = subShape;
	}

	public List<Quality> getQuality() {
		return quality;
	}

	public void setQuality(List<Quality> quality) {
		this.quality = quality;
	}

	public List<StoneChart> getStoneChart() {
		return stoneChart;
	}

	public void setStoneChart(List<StoneChart> stoneChart) {
		this.stoneChart = stoneChart;
	}

	public List<SizeGroup> getSizeGroup() {
		return sizeGroup;
	}

	public void setSizeGroup(List<SizeGroup> sizeGroup) {
		this.sizeGroup = sizeGroup;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Double getUpperTolerance() {
		return upperTolerance;
	}

	public void setUpperTolerance(Double upperTolerance) {
		this.upperTolerance = upperTolerance;
	}

	public Double getLowerTolerance() {
		return lowerTolerance;
	}

	public void setLowerTolerance(Double lowerTolerance) {
		this.lowerTolerance = lowerTolerance;
	}
	
	

}
