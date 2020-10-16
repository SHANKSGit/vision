package com.student.vision.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.student.vision.dao.VisionDao;
import com.student.vision.model.Vision;
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
        return visionDao.insert(vision)>0;
    }

    @Override
    public boolean update(Vision vision) {
        vision.setUpdateTime(new Date());
        return visionDao.update(vision)==1;
    }

    @Override
    public boolean delete(Long id) {
        return visionDao.delete(id)==1;
    }

    @Override
    public Page<Vision> search(Long sId, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return visionDao.search(sId);
    }

}
