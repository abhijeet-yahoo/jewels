package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

@Entity
@Table(name = "emailconcept")
public class EmailConcept {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;

	@Column(name = "EmailConId")
	private Integer emailConIdMax;

	@Column(name = "Date")
	private Date eDate;

	@Column(name = "EmailConceptNm")
	private String name;

	@Column(name = "Version")
	private String version;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PartyId")
	private Party party;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategId")
	private Category category;

	@Column(name = "Remark")
	private String remark;

	@Column(name = "Hold")
	private Boolean hold;

	@Column(name = "Cancel")
	private Boolean cancel;

	@Column(name = "Done")
	private Boolean done;

	@Column(name = "Modify")
	private Boolean modify;

	@Column(name = "VersionFlg")
	private Boolean versionFlg;

	@Column(name = "UniqueId", updatable = false)
	private Long uniqueId;

	@Column(name = "EmailImage")
	private String emailImage;

	@Column(name = "CadImage")
	private String cadImage;

	@Column(name = "RoughImage")
	private String roughImage;

	@Column(name = "DesignImage")
	private String designImage;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "CreatedDt", updatable = false)
	private Date createdDate;

	@Column(name = "ModiBy", insertable = false)
	private String modiBy;

	@Column(name = "ModiDt", insertable = false)
	private Date modiDate;

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

	public Integer getEmailConIdMax() {
		return emailConIdMax;
	}

	public void setEmailConIdMax(Integer emailConIdMax) {
		this.emailConIdMax = emailConIdMax;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public String geteDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return (eDate == null ? null : dateFormat.format(eDate));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getHold() {
		return hold;
	}

	public void setHold(Boolean hold) {
		this.hold = hold;
	}

	public Boolean getCancel() {
		return cancel;
	}

	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Boolean getModify() {
		return modify;
	}

	public void setModify(Boolean modify) {
		this.modify = modify;
	}

	public Boolean getVersionFlg() {
		return versionFlg;
	}

	public void setVersionFlg(Boolean versionFlg) {
		this.versionFlg = versionFlg;
	}

	public Long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getEmailImage() {
		return emailImage;
	}

	public void setEmailImage(String emailImage) {
		this.emailImage = emailImage;
	}

	public String getCadImage() {
		return cadImage;
	}

	public void setCadImage(String cadImage) {
		this.cadImage = cadImage;
	}

	public String getRoughImage() {
		return roughImage;
	}

	public void setRoughImage(String roughImage) {
		this.roughImage = roughImage;
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


	public String getDesignImage() {
		return designImage;
	}

	public void setDesignImage(String designImage) {
		this.designImage = designImage;
	}

}
