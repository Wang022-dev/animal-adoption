package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("adoption_application")
public class AdoptionApplicationEntity implements Serializable {
    @TableId
    private Long id;
    private Long animalId;
    private Long applicantUserId;
    private String applyReason;
    private String housingInfo;
    private String familyInfo;
    private String petExperience;
    private String status;
    private String auditRemark;
    private Long auditBy;
    private Date auditTime;
    private Date createTime;
    private Date updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public Long getApplicantUserId() { return applicantUserId; }
    public void setApplicantUserId(Long applicantUserId) { this.applicantUserId = applicantUserId; }
    public String getApplyReason() { return applyReason; }
    public void setApplyReason(String applyReason) { this.applyReason = applyReason; }
    public String getHousingInfo() { return housingInfo; }
    public void setHousingInfo(String housingInfo) { this.housingInfo = housingInfo; }
    public String getFamilyInfo() { return familyInfo; }
    public void setFamilyInfo(String familyInfo) { this.familyInfo = familyInfo; }
    public String getPetExperience() { return petExperience; }
    public void setPetExperience(String petExperience) { this.petExperience = petExperience; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getAuditRemark() { return auditRemark; }
    public void setAuditRemark(String auditRemark) { this.auditRemark = auditRemark; }
    public Long getAuditBy() { return auditBy; }
    public void setAuditBy(Long auditBy) { this.auditBy = auditBy; }
    public Date getAuditTime() { return auditTime; }
    public void setAuditTime(Date auditTime) { this.auditTime = auditTime; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
