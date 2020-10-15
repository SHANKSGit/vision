package com.student.vision.service;

import com.github.pagehelper.Page;
import com.student.vision.model.Student;

public interface StudentService {

    boolean insert(Student student);
    boolean update(Student student);
    boolean delete(Long id);
    Page<Student> search(Student student,int page,int pageSize);
    Student searchById(Long id);

}
