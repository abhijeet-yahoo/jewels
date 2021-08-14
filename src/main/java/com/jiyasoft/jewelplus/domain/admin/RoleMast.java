package com.jiyasoft.jewelplus.domain.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rolemast")
public class RoleMast {

	@Id
	@GeneratedValue
	@Column(name = "RoleMastId")
	private Integer id;

	@Column(name = "RoleNm")
	private String roleNm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleNm() {
		return roleNm;
	}

	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}

}
