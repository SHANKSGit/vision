package com.student.vision.dao;

import com.github.pagehelper.Page;
import com.student.vision.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {

    Long insert(Student student);
    int update(Student student);
    int exist(Student student);
    int delete(@Param("id") Long id);
    Page<Student> search(Student student);
    Student searchById(Long id);


}
