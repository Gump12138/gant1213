package com.gm.gant1213.controller;

import com.gm.gant1213.domain.User;
import com.gm.gant1213.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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

    @RequestMapping("/regist")
    public ModelAndView regist(ModelAndView modelAndView,String username, String password, String email,HttpSession session){
        Map <String,Object> map=userServiceimpl.regist(username,password,email);
        if ((boolean)map.get( "flag" )){
            modelAndView.addObject( "user", map.get( "user" ) );
            session.setAttribute( "user", map.get( "user" ) );
            modelAndView.setViewName( "lindex" );
        }else {
            modelAndView.addObject( "error",map.get( "errorMsg" ) );
            modelAndView.setViewName( "index" );
        }
        return modelAndView;
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

    @RequestMapping("/ftable")
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
        stringBuffer.append( "\t" ).append( "UID:"+u.getId() ).append( "，用户名：" ).append( u.getUsername() ).append( "：删除了" ).append( c.size() ).append( "个用户信息" );
        model.addAttribute( "msg",stringBuffer );
        return stringBuffer.toString();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:index";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(String article){
        return article;
    }
}
