package com.student.vision.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Vision {

    private Long id;
    @NotNull(message ="没有关联到对应的学生")
    private Long sId;
    /**
     * 右眼裸眼
     */
    @NotNull(message = "右眼裸眼不能为空")
    private Float rEye;
    /**
     * 左眼裸眼
     */
    @NotNull(message = "左眼裸眼不能为空")
    private Float lEye;
    /**
     * 右眼屈光
     */
    @NotNull(message = "右眼屈光不能为空")
    private Float rEyeRef;
    /**
     * 左眼屈光
     */
    @NotNull(message = "左眼屈光不能为空")
    private Float lEyeRef;


}
