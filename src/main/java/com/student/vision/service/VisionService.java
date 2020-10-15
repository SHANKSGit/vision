package com.student.vision.service;

import com.student.vision.model.Student;
import com.student.vision.model.Vision;

import java.util.List;

public interface VisionService {

    boolean insert(Vision vision);
    boolean update(Vision vision);
    boolean delete(Long id);
    List<Vision> search(Long sId);
}
