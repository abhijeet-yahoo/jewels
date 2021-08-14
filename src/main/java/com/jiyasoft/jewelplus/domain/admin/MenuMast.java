package com.jiyasoft.jewelplus.domain.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menumast")
public class MenuMast {

	@Id
	@GeneratedValue
	@Column(name = "MenuMastId")
	private Integer id;

	@Column(name = "Url")
	private String url;

	@Column(name = "MenuNm")
	private String menuNm;

	@Column(name = "MenuHeading")
	private String menuHeading;

	@Column(name = "ParentId")
	private Integer parentId;

	@Column(name = "HasChild")
	private Boolean hasChild;

	@Column(name = "SeqNo")
	private Integer seqNo;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getMenuHeading() {
		return menuHeading;
	}

	public void setMenuHeading(String menuHeading) {
		this.menuHeading = menuHeading;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public Boolean getDeactive() {
		return deactive;
	}

	public void setDeactive(Boolean deactive) {
		this.deactive = deactive;
	}

}
