package com.gm.gant1213.controller;

import com.gm.gant1213.domain.User;
import com.gm.gant1213.domain.article;
import com.gm.gant1213.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
public class Usercontroller {

    @Autowired
    private UserServiceimpl userServiceimpl;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/lindex")
    public String lindex(){
        return "lindex";
    }

    @RequestMapping("/findex")
    public String findex() {
        return "f-index";
    }

    @RequestMapping("/ftable")
    public String ftable() {
        return "tables";
    }

    @RequestMapping("/fblank")
    public String fblank() {
        return "blank";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/a")
    public String a() {
        return "/article/a";
    }
    @RequestMapping("/b")
    public String b() {
        return "/article/b";
    }
    @RequestMapping("/c")
    public String c() {
        return "/article/c";
    }
    @RequestMapping("/d")
    public String d() {
        return "/article/d";
    }
    @RequestMapping("/e")
    public String e() {
        return "/article/e";
    }
    @RequestMapping("/regist")
    public String regist(Model model,String username, String password, String email,HttpSession session){
        Map <String,Object> map=userServiceimpl.regist(username,password,email);
        if ((boolean)map.get( "flag" )){
            model.addAttribute( "user", map.get( "user" ) );
            session.setAttribute( "user", map.get( "user" ) );
            return "lindex";
        }else {
            model.addAttribute( "error",map.get( "errorMsg" ) );
            return "index";
        }

    }

    @RequestMapping("/login")
    public ModelAndView login(HttpSession session, String username, String password, ModelAndView model) {
        List<User> list=userServiceimpl.login(username, password);
        if (list.size()!=0){
            model.addObject( "flag",true );
            model.addObject( "user",list.get( 0 ));
            model.addObject( "error","" );
            session.setAttribute( "user", list.get( 0 ) );
            model.setViewName( "lindex" );
            return model;
        }else {
            model.addObject( "error","用户名或密码不正确" );
            model.addObject( "flag",false );
            model.setViewName( "index" );
            return model;
        }
    }

    @RequestMapping("/f-table")
    @ResponseBody
    public Object table(){
        Map<String, Object> map = new HashMap<>();
        List<User> list=userServiceimpl.findAll();
        map.put( "data" ,list);
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String list,Model model,HttpSession session){
        List<String> s=Arrays.asList(list.split( "," ));
        List<String> c=new ArrayList <>( new HashSet <>( s ) );
        List<Integer> uidlist=new ArrayList <>();
        for (String a: c) {
                uidlist.add( Integer.valueOf( a ) );
        };
        StringBuilder stringBuffer=new StringBuilder(  );
        stringBuffer.append( new java.util.Date() );
        User u=(User)session.getAttribute( "user" );
        stringBuffer.append( "\t" ).append( "UID:" ).append( u.getId() ).append( "，用户名：" ).append( u.getUsername() ).append( "：删除了" ).append( c.size() ).append( "个用户信息" );
        model.addAttribute( "msg",stringBuffer );
        return stringBuffer.toString();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:index";
    }


    @RequestMapping("/uploadImage")
    @ResponseBody
    public Map<String,Object> uploadMultipleFileHandler(HttpServletRequest request) throws IOException {
        Map<String,Object> map = new HashMap <>();
        MultipartHttpServletRequest multipartRequest=((MultipartHttpServletRequest) request);
        List<MultipartFile> files=multipartRequest.getFiles( "file" );
        for(MultipartFile file:files){
            if(file!=null){
                String  myFilename=file.getOriginalFilename();
                System.out.println("上传时的文件名："+myFilename);
                String filename=file.getOriginalFilename();
                String filePath="C://Users/G/IdeaProjects/gant1213/src/main/resources/static/f/html/uploadimg/"+filename;
                System.out.println("保存的路径:"+filePath);
                File targetFile = new File(filePath);
                if(!targetFile.exists()){
                    //先得到文件的上级目录，并创建上级目录，在创建文件
                    targetFile.getParentFile().mkdir();
                    try {
                        //创建文件
                        targetFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                file.transferTo(targetFile);
                String saveUrl =request.getContextPath()+"/f/html/uploadimg/"+filename;
                System.out.print( "访问路径："+saveUrl );
                map.put("url", saveUrl);
                map.put( "success",1 );
                map.put( "message","image is upload" );

            }else {
                map.put( "message","image is null" );
                map.put( "success",0 );

            }


        }
        return map;
    }


    @RequestMapping("/upload")
    public String upload(Model model, String title,@RequestParam(name = "my-editormd-html-code") String text) throws Exception {
        System.out.println( text );
        article a=new article(title,text);
        model.addAttribute( "article",a);
        return "article";
    }
}
