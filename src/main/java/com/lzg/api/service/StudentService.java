package com.lzg.api.service;

import com.lzg.api.entity.po.StudentPoBean;
import com.lzg.api.util.PageBen;

import java.util.List;

public interface StudentService {
    PageBen<StudentPoBean> queryStudentlist(PageBen<StudentPoBean> poBeanPageBen);

    void addStudent(StudentPoBean stu);

    StudentPoBean queryStudentById(Integer id);

    void delStudent(Integer id);

    List<StudentPoBean> queryStudentList();

}
