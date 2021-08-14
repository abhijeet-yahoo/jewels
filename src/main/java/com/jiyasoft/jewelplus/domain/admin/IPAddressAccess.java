package com.jiyasoft.jewelplus.domain.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ipaddressaccess")
public class IPAddressAccess {

	@Id
	@GeneratedValue
	@Column(name = "IPAddressAccessId")
	private Integer ipAddressAccessId;

	@Column(name = "StaticIP")
	private String staticIP;

	public Integer getIpAddressAccessId() {
		return ipAddressAccessId;
	}

	public void setIpAddressAccessId(Integer ipAddressAccessId) {
		this.ipAddressAccessId = ipAddressAccessId;
	}

	public String getStaticIP() {
		return staticIP;
	}

	public void setStaticIP(String staticIP) {
		this.staticIP = staticIP;
	}

}
