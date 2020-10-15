package com.student.vision.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.student.vision.dao.StudentDao;
import com.student.vision.model.Student;
import com.student.vision.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;


    @Override
    public boolean insert(Student student) {
        return studentDao.insert(student)>0;
    }

    @Override
    public boolean update( Student student)  {
        if(student==null||student.getId()==null){
            return false;
        }
        student.setUpdateTime(new Date());
        return studentDao.update(student)==1;
    }

    @Override
    public boolean delete(Long id) {
        if(id==null){
            return false;
        }
        return studentDao.delete(id)==1;
    }

    @Override
    public Page<Student> search(Student student,int page,int pageSize) {
        PageHelper.startPage(page, pageSize);
        return studentDao.search(student);
    }

    @Override
    public Student searchById(Long id) {
        return studentDao.searchById(id);
    }
}
