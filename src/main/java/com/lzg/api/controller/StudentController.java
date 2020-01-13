package com.lzg.api.controller;

import com.lzg.api.entity.po.StudentPoBean;
import com.lzg.api.entity.vo.StudentVoBean;
import com.lzg.api.service.StudentService;
import com.lzg.api.util.ExcelRefAnno;
import com.lzg.api.util.ExportExcelUtil;
import com.lzg.api.util.OSSClientUtil;
import com.lzg.api.util.PageBen;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("stu")
public class StudentController {
    @Resource
    private StudentService studentService;

    @Resource
    private HttpServletRequest request;
    @RequestMapping("queryStudentlist")
    public PageBen<StudentPoBean> queryStudentlist(PageBen<StudentPoBean> poBeanPageBen){
        PageBen<StudentPoBean> list=studentService.queryStudentlist(poBeanPageBen);
        return list;
    }
    @RequestMapping("addStudent")
    public  Map addStudent(StudentPoBean stu){
        Map map=new HashMap();
        Date birthday = stu.getBirthday();
        Date date=new Date();
        int year = birthday.getYear();
        int year1 = date.getYear();
        stu.setAge(year1-year);
        stu.setIsDel(1);
        stu.setIpress(request.getRemoteAddr());
        stu.setIpress(request.getLocalAddr());
        studentService.addStudent(stu);
        map.put("code",200);
        map.put("message","添加成功");
        return map;
    }

    /**
     * 增加图片
     * **/
    @RequestMapping("uploadFile")
    public Map<String,Object> uploadFile(@RequestParam(value = "photo") MultipartFile photo) throws IOException {
        Map<String,Object> map=new HashMap<String, Object>();
        OSSClientUtil oss=new OSSClientUtil();
        try {
            //上传文件
            String fileName = oss.uploadImg2Oss(photo);
            //获取上传玩的文件路径
            String imageUrl = oss.getImageUrl(fileName);
            map.put("img",imageUrl);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("queryStudentById")
    public  Map queryStudentById(Integer id){
        Map map=new HashMap();
         StudentPoBean stus=  studentService.queryStudentById(id);
        map.put("code",200);
        map.put("data",stus);
        return map;
    }

    @RequestMapping("delStudent")
    public  Map delStudent(Integer id){
        Map map=new HashMap();
         studentService.delStudent(id);
        map.put("code",200);
        map.put("message","删除成功");
        return map;
    }

    @RequestMapping("exportExcel")
    public  Map exportExcel()  {
        Map map=new HashMap();
        List<StudentPoBean> list= studentService.queryStudentList();
        String url = null;
        try {
            url = ExcelRefAnno.exportExcel(list, StudentPoBean.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        map.put("code",200);
        map.put("data",url);
        map.put("message","导出成功");
        return map;
    }


}
