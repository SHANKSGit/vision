package com.student.vision.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.student.vision.dao.StudentDao;
import com.student.vision.dao.VisionDao;
import com.student.vision.model.Student;
import com.student.vision.model.Vision;
import com.student.vision.service.StudentService;
import com.student.vision.service.VisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VisionServiceImpl implements VisionService {
    @Autowired
    private VisionDao visionDao;


    @Override
    public boolean insert(Vision vision) {
        return false;
    }

    @Override
    public boolean update(Vision vision) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Vision> search(Long sId) {
        return visionDao.search(sId);
    }
}
