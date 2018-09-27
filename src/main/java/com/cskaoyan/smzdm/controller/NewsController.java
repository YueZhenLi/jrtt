package com.cskaoyan.smzdm.controller;

import com.cskaoyan.smzdm.common.Constant;
import com.cskaoyan.smzdm.domain.News;
import com.cskaoyan.smzdm.domain.User;
import com.cskaoyan.smzdm.domain.vo.VO;
import com.cskaoyan.smzdm.service.NewsService;
import com.cskaoyan.smzdm.service.UserService;
import com.cskaoyan.smzdm.util.RedisUtil;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/12
 */

@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @Value("${web.upload-path}")
    String uploadPath;

    @RequestMapping("/")
    public String home(HttpSession session, HttpServletRequest request){

        String username = null;
        String password = null;
        //判断是否有用户信息的cookie,如果有则直接登陆
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies){
                if (Constant.LOGIN_USERNAME.equals(cookie.getName())){
                    username = cookie.getValue();
                }
                if (Constant.LOGIN_PASSWORD.equals(cookie.getName())){
                    password= cookie.getValue();
                }
            }
            if (username!=null&&password!=null){
                User user = userService.findUserByUsernameAndPassword(username, password);
                session.setAttribute("user", user);

            }
        }

        List<VO> vos = new ArrayList<>();
        List<News> newz = newsService.showNews();
        for (News news : newz) {
//            //获取newsId并从redis中得到出点赞人数
//            int id = news.getId();
//            String idLike = String.valueOf(id)+"Like";
//            Long scard = RedisUtil.scard(idLike);
            int uid = news.getUid();
            User userById = userService.findUserById(uid);
            VO vo = new VO();
//            vo.setLike(scard.intValue());
            vo.setLike(news.getLikeCount());
            vo.setUser(userById);
            vo.setNews(news);
            vos.add(vo);
        }
        session.setAttribute("date", new DateTool());
        session.setAttribute("vos", vos);
        return "home";
    }

    /*
    第一种：图片上传到target.classes.static.images.img中
    @RequestMapping("/uploadImage")
    @ResponseBody
    public Map uploadImage(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();

        File savePath = ResourceUtils.getFile("classpath:static/images/img");

        File saveFile = new File(savePath, originalFilename);
        file.transferTo(saveFile);

        String saveFilePath = saveFile.getPath();

        System.out.println("saveFilePath=" +saveFilePath);

        String[] split = saveFilePath.split("static");
        String referencePath = split[1];

        String imgUrl = referencePath.replace("\\", "/");

        HashMap<Object, Object> hashMap = new HashMap<>();

        hashMap.put("msg", imgUrl);
        hashMap.put("code", 0);
        return hashMap;
    }*/

    //第二种：图片上传到 d:/upload
    @RequestMapping("/uploadImage")
    @ResponseBody
    public Map uploadImage(MultipartFile file, HttpServletRequest request) throws IOException {

        File savaDir = new File(uploadPath);

        String filename = file.getOriginalFilename();
        File saveFile = new File(savaDir,filename);

        String filePath = saveFile.getPath();

        file.transferTo(saveFile);

        String host = request.getHeader("Host");

//      http://192.168.2.100:8080/wangdaonews/image?name=a3e912afada146b883c170b3faadea83.jpg

        String msg = "http://"+host+"/jrtt/image?name="+ filename;
        HashMap<Object, Object> hashMap = new HashMap<>();

        hashMap.put("msg", msg);
        hashMap.put("code", 0);
        return hashMap;
    }

    @RequestMapping("jrtt/image")
    public void showImage (String name, HttpServletResponse response) throws IOException {
        File imgaDir = new File(uploadPath);

        File file = new File(imgaDir, name);

        FileInputStream fis= new FileInputStream(file);

        ServletOutputStream fos = response.getOutputStream();

        int len =0;
        byte[] buffer= new byte[1024];
        while ((len=fis.read(buffer))!=-1){
            fos.write(buffer, 0, len);
        }
        fos.flush();
    }


   /* //第三种，存储在云盘：will-jrtt.oss-cn-shenzhen.aliyuncs.com
    @RequestMapping("/uploadImage")
    @ResponseBody
    public Map uploadImage(MultipartFile file) throws IOException {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
        // 强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIXt0hu3knZ7qW";
        String accessKeySecret = "KVm8SFM7voXIcyRtNFqoyM0Hi4QBcw";
        String bucketName = "will-jrtt";
        String objectName = file.getOriginalFilename();
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件。
        byte[] content = file.getBytes();
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        // 关闭OSSClient。
        ossClient.shutdown();

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("msg", "http://will-jrtt.oss-cn-shenzhen.aliyuncs.com/"+objectName);
        hashMap.put("code", 0);
        return hashMap;
    }*/

    @RequestMapping("/like")
    @ResponseBody
    public Map like(String newsId, HttpSession session){
        HashMap<Object, Object> hashMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        //若用户没有登陆，则只返回查询结果，不做其他操作
        if (user==null){
            Long scard = RedisUtil.scard(newsId + "Like");
            hashMap.put("msg", scard);
            hashMap.put("code", 0);
            return hashMap;
        }
        boolean likeResult = newsService.incrLikeByNewId(newsId, user);
        if (likeResult){
            Long scard = RedisUtil.scard(newsId + "Like");
            hashMap.put("msg", scard);
            hashMap.put("code", 0);
            return hashMap;
        }else
            hashMap.put("code", 1);
        return hashMap;
    }

    @RequestMapping("/dislike")
    @ResponseBody
    public Map dislike(String newsId, HttpSession session){
        HashMap<Object, Object> hashMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        //若用户没有登陆，则只返回查询结果，不做其他操作
        if (user==null){
            Long scard = RedisUtil.scard(newsId + "Like");
            hashMap.put("msg", scard);
            hashMap.put("code", 0);
            return hashMap;
        }
        boolean dislikeResult = newsService.reduceLikeByNewId(newsId, user);

        //发挥赞的总数
        if (dislikeResult){
            Long scard = RedisUtil.scard(newsId + "Like");
            hashMap.put("msg", scard);
            hashMap.put("code", 0);
            return hashMap;
        }else
            hashMap.put("code", 1);
        return hashMap;
    }

}

