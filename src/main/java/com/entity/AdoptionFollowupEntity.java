package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("adoption_followup")
public class AdoptionFollowupEntity implements Serializable {
    @TableId
    private Long id;
    private Long animalId;
    private Long adopterUserId;
    private Long volunteerUserId;
    private Date followupTime;
    private String followupResult;
    private String followupContent;
    private Date createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public Long getAdopterUserId() { return adopterUserId; }
    public void setAdopterUserId(Long adopterUserId) { this.adopterUserId = adopterUserId; }
    public Long getVolunteerUserId() { return volunteerUserId; }
    public void setVolunteerUserId(Long volunteerUserId) { this.volunteerUserId = volunteerUserId; }
    public Date getFollowupTime() { return followupTime; }
    public void setFollowupTime(Date followupTime) { this.followupTime = followupTime; }
    public String getFollowupResult() { return followupResult; }
    public void setFollowupResult(String followupResult) { this.followupResult = followupResult; }
    public String getFollowupContent() { return followupContent; }
    public void setFollowupContent(String followupContent) { this.followupContent = followupContent; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
