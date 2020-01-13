package com.lzg.api.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//图片上传
public class uploadUtil {

    public static  String uploadFile(CommonsMultipartFile photo, String mkdirName) throws IOException {
        //给临时文件重命名
        //获取老文件名+后缀
        String oldName = photo.getOriginalFilename();
        //截取后缀名   suffixName：文件后缀名
        String suffixName = oldName.substring(oldName.lastIndexOf("."));
        //给前缀名重命名   uuID/时间戳
        long time=System.currentTimeMillis();
        //新文件名
        String newName=time+suffixName;
        //获取request对象
        HttpServletRequest request=((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
        //获取绝对路径
        String realPath = request.getServletContext().getRealPath("/");
        //重新赋值
        realPath=realPath+"common/"+mkdirName;
        //判断文件路径是否存在如果不存在则创建文件夹
        File file =new File(realPath);
        if (!file.exists()){
         file.mkdirs();
        }

        //构建流复制文件
        photo.transferTo(new File(realPath+"/"+newName));

        //返回相对路径
        //返回的是一个路径   mkdirName:你想要保存的路径   newName：文件名
        return "common/"+mkdirName+"/"+newName;
    }

    public static void downLoad(String url){
        //获取request对象
        HttpServletRequest request=((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
        //获取绝对路径
        String realPath = request.getServletContext().getRealPath("/");
        //
        String ur=realPath+url;
        File file=new File(ur);
        if(file.exists()){
            //获取response对象
            HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("applicatin/octet-stream");
            //获取文件后缀名
            String houzhui = url.substring(url.lastIndexOf("."));
            //设置下载文件名
            response.addHeader("Content-Disposition", "attachment; filename=\""+System.currentTimeMillis()+houzhui+"\"");
            //获取相应流
            try {
                //构建输出流
                ServletOutputStream os = response.getOutputStream();
                //构建文件输入流
                FileInputStream is = new FileInputStream(file);

                //构建字节
                byte[] bs=new byte[2048];
                while (is.read(bs) != -1){
                    os.write(bs);
                }
                //关闭相应流
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
