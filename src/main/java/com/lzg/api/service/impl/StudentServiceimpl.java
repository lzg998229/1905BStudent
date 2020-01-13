package com.lzg.api.service.impl;

import com.lzg.api.dao.StudentDao;
import com.lzg.api.entity.po.StudentPoBean;
import com.lzg.api.service.StudentService;
import com.lzg.api.util.PageBen;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public PageBen<StudentPoBean> queryStudentlist(PageBen<StudentPoBean> poBeanPageBen) {
       long count=studentDao.queryStudentCount();
       poBeanPageBen.setRecordsTotal(count);
       poBeanPageBen.setRecordsFiltered(count);
        List<StudentPoBean> list=studentDao.queryStudentlist(poBeanPageBen);
        poBeanPageBen.setData(list);
        return poBeanPageBen;
    }

    @Override
    public void addStudent(StudentPoBean stu) {
        if(stu.getId()==null){
            studentDao.insert(stu);
        }else {
            studentDao.updateById(stu);
        }

    }

    @Override
    public StudentPoBean queryStudentById(Integer id) {
        return studentDao.selectById(id);
    }

    @Override
    public void delStudent(Integer id) {
        studentDao.delStudent(id);
    }

    @Override
    public List<StudentPoBean> queryStudentList() {
        return studentDao.selectList(null);
    }
}
