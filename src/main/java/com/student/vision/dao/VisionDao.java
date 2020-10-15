package com.student.vision.dao;

import com.student.vision.model.Vision;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VisionDao {

    Long insert(Vision vision);
    int update(Vision vision);
    int delete(@Param("id") Long id);
    List<Vision> search(@Param("sId") Long sId);


}
