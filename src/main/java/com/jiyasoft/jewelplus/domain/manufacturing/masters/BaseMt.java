package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "basemt")
public class BaseMt {

	@Id
	@GeneratedValue
	@Column(name = "BaseId")
	private Integer id;

	@Column(name = "BaseNo")
	private Integer baseNo;

	@Column(name = "BaseWt")
	private Double baseWt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBaseNo() {
		return baseNo;
	}

	public void setBaseNo(Integer baseNo) {
		this.baseNo = baseNo;
	}

	public Double getBaseWt() {
		return baseWt;
	}

	public void setBaseWt(Double baseWt) {
		this.baseWt = baseWt;
	}

}
