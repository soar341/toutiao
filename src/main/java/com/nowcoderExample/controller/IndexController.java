package com.nowcoderExample.controller;

import com.nowcoderExample.model.User;
import com.nowcoderExample.service.ToutiaoService;
import com.nowcoderExample.service.aspect.LogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import static java.awt.SystemColor.info;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
//@Controller
public class IndexController {
    //AOP面向切面编程，打印log
    private static final Logger logger= LoggerFactory.getLogger(LogAspect.class);

    //控制反转IOC
    @Autowired
    private ToutiaoService toutiaoService;

    @RequestMapping(path={"/","/index"})
    @ResponseBody
    public String index(HttpSession session){
        logger.info("Visit Index");
        return "Hello World,"+session.getAttribute("msg")+"<br> Say:"+toutiaoService.say();
    }

    @RequestMapping(path={"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value="type",defaultValue = "1")int type,
                          @RequestParam(value="key",defaultValue ="nowcoder" )String key){
        return String.format("GID{%s},UID{%d},TYPE{%d},KEY{%s}",groupId,userId,type,key);
        //return String.format("{%s},{%d},{%d},{%s}",groupId,userId,type,key);
    }

    /*****模板*****/
    @RequestMapping(value={"/vm"})
    public String news(Model model){
        model.addAttribute("value1","vvl");
        List<String> colors= Arrays.asList(new String[]{"RED","Green","BLUE"});
        Map<String,String> map=new HashMap<String,String>();
        for(int i=0;i<4;i++){
            map.put(String.valueOf(i),String.valueOf(i*i));
        }
        model.addAttribute("colors",colors);
        model.addAttribute("map",map);
        //model.addAttribute("user",new user("Jim"));
        /*map.entrySet();
        for(Map.Entry entry:map.entrySet()){
            entry.getKey();
            entry.getValue();
        }
        //Map.Entry entry=map.entrySet().iterator();
        */
        return "news";
    }

    @RequestMapping(value={"/request"})
    @ResponseBody
    public String request(HttpServletRequest request,
                          HttpServletResponse reponse,
                          HttpSession session){
        StringBuilder sb=new StringBuilder();
        Enumeration<String> headerNames=request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name=headerNames.nextElement();
            sb.append(name+":"+request.getHeader(name)+"<br>");
        }
        for(Cookie cookie:request.getCookies()){
            sb.append("Cookie:");
            sb.append(cookie.getName());
            sb.append(cookie.getValue());
            sb.append("<br>");
        }
        sb.append("getMethod:"+request.getMethod()+"<br>");
        sb.append("getPathInfo:"+request.getPathInfo()+"<br>");
        sb.append("getQueryString:"+request.getQueryString()+"<br>");
        sb.append("getRequestURI:"+ request.getRequestURI()+"<br>");


        return sb.toString();
    }
    @RequestMapping(value={"/response"})
    @ResponseBody
    public String response(@CookieValue(value="nowcoderid",defaultValue="a")String nowcoderId,
                           @RequestParam(value="key",defaultValue="key")String key,
                           @RequestParam(value="value",defaultValue="value")String value,
                           HttpServletResponse response){
        response.addCookie(new Cookie(key,value));
        response.addHeader(key,value);
        return "NowcoderId from Cookie:"+nowcoderId;
    }

    //重定向
    @RequestMapping("/redirect/{code}")
    public String redirect(@PathVariable("code")int code,
                           HttpSession session){
        /*
        RedirectView red=new RedirectView("/",true);
        if(code==301){
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
    return red;*/
        session.setAttribute("msg","Jump from redirect.");
        return "redirect:/";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(@RequestParam(value="key",required = false)String key){
        if("admin".equals(key)){
            return "hello admin";
        }
        throw new IllegalArgumentException("key 错误");
    }
    //自定义错误处理类
    @ExceptionHandler
    @ResponseBody
    public String error(Exception e){
        return "error；"+e.getMessage();
    }

}
