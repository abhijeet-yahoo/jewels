package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//fields

@Entity
@Table(name="colormast")
public class Color {
	
	
	@Id
	@GeneratedValue
	@Column(name = "ColorId")
	private Integer id;
	
	@Column(name = "ColorNm")
	private String name;
	
	@Column(name = "ColorDesc")
	private String desc;
	
	@Column(name = "TwoTone")
	private String twoTone;
	
	@Column(name = "ColorTone")
	private String colorTone;
	
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
	
	@Column(name = "DefColor")
	private Boolean defColor =false;
	
	
	
	//getters and setters
	
	
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTwoTone() {
		return twoTone;
	}

	public void setTwoTone(String twoTone) {
		this.twoTone = twoTone;
	}

	public String getColorTone() {
		return colorTone;
	}

	public void setColorTone(String colorTone) {
		this.colorTone = colorTone;
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

	public void setModiBy(String modiby) {
		this.modiBy = modiby;
	}

	public Date getModiDate() {
		return modiDate;
	}

	public void setModiDate(Date modidate) {
		this.modiDate = modidate;
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

	public Boolean getDefColor() {
		return defColor;
	}

	public void setDefColor(Boolean defColor) {
		this.defColor = defColor;
	}

	
}
