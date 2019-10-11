package com.example.foss.controller;

import com.example.foss.pojo.Activity;
import com.example.foss.pojo.JsonResult;
import com.example.foss.service.ActivityService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.example.foss.Utils.TimeUtil.getNowTime;


@Controller
@RequiresRoles("admin")
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @GetMapping("activity")
    public String toActivity(){
        return "manage_activity";
    }
    @GetMapping("queryActivity")
    @ResponseBody
    public List<Activity> querySupplier(@RequestParam(required = false,value = "aname") String aname){
        Activity activity = new Activity();
        activity.setAname(aname);
        return activityService.queryActivity(activity);
    }
    @PostMapping("insertActivity")
    @ResponseBody
    public JsonResult insertActivity(@RequestBody Activity activity){
        String time = getNowTime();
        activity.setAddDate(time);
        activity.setUpdateDate(time);
        JsonResult jsonResult = new JsonResult();
        if(activityService.insertActivity(activity)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("插入成功 ");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("插入失败");
        }
        return jsonResult;
    }
    @PostMapping("updateActivity/{aid}")
    @ResponseBody
    public JsonResult updateActivity(@PathVariable("aid") Integer aid, @RequestBody Activity activity){
        String time = getNowTime();
        activity.setUpdateDate(time);
        activity.setAid(aid);
        JsonResult jsonResult = new JsonResult();
        if(activityService.updateActivity(activity)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("修改成功 ");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("修改失败");
        }
        return jsonResult;
    }
    @PostMapping("deleteActivity/{aid}")
    @ResponseBody
    public JsonResult deleteActivity(@PathVariable("aid")Integer aid,@RequestBody Activity activity){
        String time = getNowTime();
        activity.setUpdateDate(time);
        activity.setAid(aid);
        JsonResult jsonResult = new JsonResult();
        if(activityService.deleteActivity(activity)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("删除成功 ");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("删除失败");
        }
        return jsonResult;
    }
}
