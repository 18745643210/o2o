package com.linda.o2o.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

public class Area {
    //id
    private Integer AreaId;
    //名称
    private  String areaName;
    //权重
    private  Integer priority;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;

    public Integer getAreaId() {
        return AreaId;
    }

    public void setAreaId(Integer areaId) {
        AreaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }


    @Override
    public String toString() {
        return areaName;
    }
}
