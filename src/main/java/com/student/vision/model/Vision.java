package com.student.vision.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Vision {

    private Long id;
    @NotNull(message ="没有关联到对应的学生")
    private Long sId;
    /**
     * 右眼裸眼
     */
    private String rEye;
    /**
     * 左眼裸眼
     */
    private String lEye;
    /**
     * 右眼屈光
     */
    private String rEyeRef;
    /**
     * 左眼屈光
     */
    private String lEyeRef;

    private String lGlass;
    private String rGlass;

    /**
     * 其他
     */
    private String content;

    private Date createTime;
    private Date updateTime;

    public String getlGlass() {
        return lGlass;
    }

    public void setlGlass(String lGlass) {
        this.lGlass = lGlass;
    }

    public String getrGlass() {
        return rGlass;
    }

    public void setrGlass(String rGlass) {
        this.rGlass = rGlass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getrEye() {
        return rEye;
    }

    public void setrEye(String rEye) {
        this.rEye = rEye;
    }

    public String getlEye() {
        return lEye;
    }

    public void setlEye(String lEye) {
        this.lEye = lEye;
    }

    public String getrEyeRef() {
        return rEyeRef;
    }

    public void setrEyeRef(String rEyeRef) {
        this.rEyeRef = rEyeRef;
    }

    public String getlEyeRef() {
        return lEyeRef;
    }

    public void setlEyeRef(String lEyeRef) {
        this.lEyeRef = lEyeRef;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
