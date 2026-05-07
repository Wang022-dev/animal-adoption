package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("rescue_record")
public class RescueRecordEntity implements Serializable {
    @TableId
    private Long id;
    private Long animalId;
    private Long volunteerUserId;
    private String foundLocation;
    private Date foundTime;
    private Date rescueTime;
    private String emergencyLevel;
    private String initialHealthDesc;
    private String rescueDesc;
    private String auditStatus;
    private Long auditBy;
    private String auditRemark;
    private Date auditTime;
    private Date createTime;
    private Date updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public Long getVolunteerUserId() { return volunteerUserId; }
    public void setVolunteerUserId(Long volunteerUserId) { this.volunteerUserId = volunteerUserId; }
    public String getFoundLocation() { return foundLocation; }
    public void setFoundLocation(String foundLocation) { this.foundLocation = foundLocation; }
    public Date getFoundTime() { return foundTime; }
    public void setFoundTime(Date foundTime) { this.foundTime = foundTime; }
    public Date getRescueTime() { return rescueTime; }
    public void setRescueTime(Date rescueTime) { this.rescueTime = rescueTime; }
    public String getEmergencyLevel() { return emergencyLevel; }
    public void setEmergencyLevel(String emergencyLevel) { this.emergencyLevel = emergencyLevel; }
    public String getInitialHealthDesc() { return initialHealthDesc; }
    public void setInitialHealthDesc(String initialHealthDesc) { this.initialHealthDesc = initialHealthDesc; }
    public String getRescueDesc() { return rescueDesc; }
    public void setRescueDesc(String rescueDesc) { this.rescueDesc = rescueDesc; }
    public String getAuditStatus() { return auditStatus; }
    public void setAuditStatus(String auditStatus) { this.auditStatus = auditStatus; }
    public Long getAuditBy() { return auditBy; }
    public void setAuditBy(Long auditBy) { this.auditBy = auditBy; }
    public String getAuditRemark() { return auditRemark; }
    public void setAuditRemark(String auditRemark) { this.auditRemark = auditRemark; }
    public Date getAuditTime() { return auditTime; }
    public void setAuditTime(Date auditTime) { this.auditTime = auditTime; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
