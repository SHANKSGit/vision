package com.student.vision.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseQuery {
    private int page;
    private int pageSize;
    private Student search;

}
