package com.lzg.api.entity.po;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lzg.api.annotation.PoiExcelAnnotation;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("ssm_springboot_student")
@PoiExcelAnnotation(title = "学生信息展示",sheetName = "学生信息",mkdir = "/commons/excel")
public class StudentPoBean {
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;
    @PoiExcelAnnotation("学生姓名")
    private  String name;
    @PoiExcelAnnotation("学生年龄")
    private  Integer age;
    @PoiExcelAnnotation("学生生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @PoiExcelAnnotation("地区")
    private  String address;
    @PoiExcelAnnotation("图片")
     private  String imgPath;

     private  Integer isDel;

     private  String ipress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getIpress() {
        return ipress;
    }

    public void setIpress(String ipress) {
        this.ipress = ipress;
    }
}
