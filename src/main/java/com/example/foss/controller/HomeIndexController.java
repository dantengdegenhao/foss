package com.example.foss.controller;

import com.example.foss.Utils.HttpUtils;
import com.example.foss.base.AjaxResult;
import com.example.foss.pojo.User;
import com.example.foss.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 功能描述
 *
 * @author lzh
 * @date 2019/9/10
 * @description 登录注册忘记密码
 */
@Controller
@Slf4j
public class HomeIndexController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserServiceImpl userServer;
    @GetMapping("/login")
    public String defaultLogin() {
        return "login";
    }
    @GetMapping("index")
    public String toHome(Model model, HttpServletRequest request){
        User user=(User) SecurityUtils.getSubject().getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        if (User.isAdmin(user.getId())){
            model.addAttribute("name",user.getName());
            model.addAttribute("mobile",user.getMobile());
            model.addAttribute("id",user.getId());
            return "index";
        }
        model.addAttribute("name",user.getName());
        model.addAttribute("mobile",user.getMobile());
        model.addAttribute("id",user.getId());
        return "qiantai/index";
    }

    @GetMapping("/")
    public String defaultLogin2() {
        return "redirect:index";
    }

    @GetMapping("/registered")
    public String defaultRegistered(){
        return "registered";
    }

    @GetMapping("/forgetPassword")
    public String defaultForgetPassword(){
        return "forgetpassword";
    }

    @PostMapping("/forgetPassword")
    public String forgetPassword(String mobile,String password,String determinePassword,
                                 String code,Model model){
        HashMap code2 = (HashMap)redisTemplate.opsForValue().get("code");
        User user=new User(mobile,password,determinePassword);
        if (userServer.getByMobile(mobile)==null){
            model.addAttribute("mobile","用户名不存在");
            model.addAttribute("user",user);
            return "forgetpassword";
        }else if (!StringUtils.equals(password,determinePassword)){
            model.addAttribute("password","密码不一致");
            model.addAttribute("user",user);
            return "forgetpassword";
        }
        else
        if (MapUtils.isEmpty(code2)){
            model.addAttribute("code","验证码已过期");
            model.addAttribute("user",user);
            return "forgetpassword";
        }else if (!StringUtils.equals(code,String.valueOf(code2.get("code")))){
            model.addAttribute("code","验证码不正确");
            model.addAttribute("user",user);
            return "forgetpassword";
        }
        userServer.revise(user);
        return "redirect:login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("mobile") String mobile, @RequestParam("password") String password,
                        String rememberMe, Model model) {

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(mobile,password,Boolean.parseBoolean(rememberMe));
        // 执行认证登陆操作
        try {
            subject.login(token);
            return "redirect:index";
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg","未知账户");
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg","密码不正确");
        } catch (LockedAccountException lae) {
            model.addAttribute("msg","账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            model.addAttribute("msg","用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            model.addAttribute("msg","用户名或密码不正确！");
        }
        model.addAttribute("mobile",mobile);
        return "login";
        /*if (!subject.isAuthenticated()) {
            token.clear();
            model.addAttribute("msg","未知错误登录失败，请联系管理员");
            return "login";
        }
        model.addAttribute("msg","登录失败，请联系管理员");
        return "login";*/
    }

    @PostMapping("/registered")
    public String registered(@Valid User user, Errors errors, Model model){
        /*if (
                userServer.getByUserName(user.getUserName())!=null&&user.getPassword().equals(user.getDeterminePassword())
                        &&redisTemplate.hasKey("code")
                &&(int)redisTemplate.opsForValue().get("code")==Integer.parseInt(user.getMobile())){


        }*/
        /*if (userServer.getByUserName(user.getUserName())==null){
            model.addAttribute("msg","用户名");
        }
        else{
            model.addAttribute("msg","验证码错误");
        }*/
        HashMap code = (HashMap) redisTemplate.opsForValue().get("code");
        if (userServer.getByMobile(user.getMobile())!=null){
            errors.rejectValue("mobile",null,"用户已存在");
        }else if (!user.getPassword().equals(user.getDeterminePassword())){
            errors.rejectValue("determinePassword",null,"两次密码不一致");
        }else if (!redisTemplate.hasKey("code")){
            errors.rejectValue("code",null,"验证码已过期");
        }else if ((int)code.get("code")!=Integer.parseInt(user.getCode())){
            errors.rejectValue("code",null,"验证码不正确");
        }else if (!user.getMobile().equals((String) code.get("mobile"))){
            errors.rejectValue("mobile",null,"手机号与验证码不对应");
        }
        if (errors.hasErrors()){
            List<ObjectError> allErrors = errors.getAllErrors();
            for (ObjectError oe:
                    allErrors) {
                String key=null;
                String msg=null;
                if (oe instanceof FieldError){
                    FieldError fieldError=(FieldError) oe;
                    key = fieldError.getField();
                }else{
                    key = oe.getObjectName();
                }
                msg=oe.getDefaultMessage();
                log.info(key+msg);
                model.addAttribute(key,msg);
                model.addAttribute("user",user);
            }
            return "registered";
        }else {
            userServer.add(user);
            return "redirect:login";
        }
        /*Map<String,Object> errMap=new HashMap();*/

    }

    @GetMapping("/code")
    @ResponseBody
    public AjaxResult code(@RequestParam("mobile") String mobile){
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        if (!p.matcher(mobile).matches()){
            return AjaxResult.error("手机格式不正确");
        }
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";
        String appcode = "4b39ae2257db4b4ebe879adceed955c9";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", mobile);
        /*if (redisTemplate.hasKey("code")){
            querys.put("param", "code:"+redisTemplate.opsForValue().get("code"));
        }else {
            int i = (int) ((Math.random() * 9 + 1) * 100000);
            querys.put("param", "code:"+ i);
            redisTemplate.opsForValue().set("code",i,5, TimeUnit.MINUTES);
        }*/
        int i = (int) ((Math.random() * 9 + 1) * 100000);
        HashMap code = new HashMap();
        code.put("code",i);
        code.put("mobile",mobile);
        querys.put("param", "code:"+i);
        querys.put("tpl_id", "TP1711063");
        redisTemplate.opsForValue().set("code",code,5, TimeUnit.MINUTES);
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            return AjaxResult.success();
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}
