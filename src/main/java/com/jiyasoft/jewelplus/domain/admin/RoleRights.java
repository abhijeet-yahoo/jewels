package com.jiyasoft.jewelplus.domain.admin;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rolerights")
public class RoleRights {
	
	@Id
	@GeneratedValue
	@Column(name = "roleRightId")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RoleMastId")
	private RoleMast roleMast;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MenuMastId")
	private MenuMast menuMast;
	
	@Column(name = "CompId")
	private Integer compId;

	@Column(name = "CanAdd")
	private Boolean canAdd = false;

	@Column(name = "CanEdit")
	private Boolean canEdit = false;

	@Column(name = "CanDelete")
	private Boolean canDelete = false;

	@Column(name = "CanView")
	private Boolean canView = false;
	
	@Column(name = "CanCopy")
	private Boolean canCopy = false;
	
	@Column(name = "CanPreview")
	private Boolean canPreview = false;

	@Column(name = "Deactive")
	private Boolean deactive = false;

	@Column(name = "DeactiveDt")
	private Date deactiveDt;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleMast getRoleMast() {
		return roleMast;
	}

	public void setRoleMast(RoleMast roleMast) {
		this.roleMast = roleMast;
	}

	public MenuMast getMenuMast() {
		return menuMast;
	}

	public void setMenuMast(MenuMast menuMast) {
		this.menuMast = menuMast;
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Boolean getCanAdd() {
		return canAdd;
	}

	public void setCanAdd(Boolean canAdd) {
		this.canAdd = canAdd;
	}

	public Boolean getCanEdit() {
		return canEdit;
	}

	public void setCanEdit(Boolean canEdit) {
		this.canEdit = canEdit;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	public Boolean getCanView() {
		return canView;
	}

	public void setCanView(Boolean canView) {
		this.canView = canView;
	}

	public Boolean getCanCopy() {
		return canCopy;
	}

	public void setCanCopy(Boolean canCopy) {
		this.canCopy = canCopy;
	}

	public Boolean getCanPreview() {
		return canPreview;
	}

	public void setCanPreview(Boolean canPreview) {
		this.canPreview = canPreview;
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


	

}
