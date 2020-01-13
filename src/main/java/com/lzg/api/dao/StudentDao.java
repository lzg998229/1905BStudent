package com.lzg.api.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzg.api.entity.po.StudentPoBean;
import com.lzg.api.util.PageBen;

import java.util.List;

public interface StudentDao extends BaseMapper<StudentPoBean> {
    long queryStudentCount();

    List<StudentPoBean> queryStudentlist(PageBen<StudentPoBean> poBeanPageBen);

    void delStudent(Integer id);
}
