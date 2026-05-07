package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("animal")
public class AnimalEntity implements Serializable {
    @TableId
    private Long id;
    private String animalNo;
    private String name;
    private String category;
    private String breed;
    private String color;
    private String ageDesc;
    private String gender;
    private BigDecimal weight;
    private Integer sterilized;
    private Integer vaccinated;
    private String healthStatus;
    private String personality;
    private String foundLocation;
    private Date foundTime;
    private Date rescueTime;
    private String currentLocation;
    private String emergencyLevel;
    private String rescueDesc;
    private String coverImage;
    private String adoptRequirements;
    private String status;
    private Long createdBy;
    private Date createTime;
    private Date updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAnimalNo() { return animalNo; }
    public void setAnimalNo(String animalNo) { this.animalNo = animalNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getAgeDesc() { return ageDesc; }
    public void setAgeDesc(String ageDesc) { this.ageDesc = ageDesc; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }
    public Integer getSterilized() { return sterilized; }
    public void setSterilized(Integer sterilized) { this.sterilized = sterilized; }
    public Integer getVaccinated() { return vaccinated; }
    public void setVaccinated(Integer vaccinated) { this.vaccinated = vaccinated; }
    public String getHealthStatus() { return healthStatus; }
    public void setHealthStatus(String healthStatus) { this.healthStatus = healthStatus; }
    public String getPersonality() { return personality; }
    public void setPersonality(String personality) { this.personality = personality; }
    public String getFoundLocation() { return foundLocation; }
    public void setFoundLocation(String foundLocation) { this.foundLocation = foundLocation; }
    public Date getFoundTime() { return foundTime; }
    public void setFoundTime(Date foundTime) { this.foundTime = foundTime; }
    public Date getRescueTime() { return rescueTime; }
    public void setRescueTime(Date rescueTime) { this.rescueTime = rescueTime; }
    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }
    public String getEmergencyLevel() { return emergencyLevel; }
    public void setEmergencyLevel(String emergencyLevel) { this.emergencyLevel = emergencyLevel; }
    public String getRescueDesc() { return rescueDesc; }
    public void setRescueDesc(String rescueDesc) { this.rescueDesc = rescueDesc; }
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public String getAdoptRequirements() { return adoptRequirements; }
    public void setAdoptRequirements(String adoptRequirements) { this.adoptRequirements = adoptRequirements; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
