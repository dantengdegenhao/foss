package com.example.foss.controller;

import com.example.foss.pojo.FoodInformation;
import com.example.foss.pojo.FoodStatus;
import com.example.foss.pojo.FoodType;
import com.example.foss.pojo.JsonResult;
import com.example.foss.service.FoodService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.foss.Utils.TimeUtil.getNowTime;


@Controller
@RequiresRoles("admin")
public class FoodController {
    @Autowired
    FoodService foodService;
    @GetMapping("welcome")
    @ResponseBody
    public String toWelcome(){return "welcome";}
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("USER_SESSION");
        return "redirect:login";
    }
    @GetMapping("foodTypeManage")
    public String toFoodTypeManage(){
        return "manage_foodtype";
    }
    @GetMapping("foodInformationManage")
    public String toFoodInformation(){
        return "manage_foodInformation";
    }
    @GetMapping("foodStatus")
    public String toFoodStatus(){
        return "manage_foodStatus";
    }
    @GetMapping("queryFoodType")
    @ResponseBody
    public List<FoodType> queryFoodType(ServletRequest servletRequest){
        String typeName = servletRequest.getParameter("typeName");
        return foodService.queryFoodType(typeName);
    }
    @PostMapping("addFoodType")
    @ResponseBody
    public JsonResult insertNewType(@RequestBody FoodType foodType){
        foodType.setAddDate(getNowTime());
        foodType.setUpdateDate(getNowTime());
        JsonResult jsonResult = new JsonResult();
        try{
            if(foodService.insertNewType(foodType)>0){
                jsonResult.setCode(0);
                jsonResult.setMsg("插入成功");
            }else{
                jsonResult.setCode(-1);
                jsonResult.setMsg("插入失败");
            }
        }catch (Exception e){

            jsonResult.setCode(-2);
            jsonResult.setMsg("插入异常");
        }
        return jsonResult;
    }
    @PostMapping("deleteFoodType/{typeId}")
    @ResponseBody
    public JsonResult deleteFoodType(@PathVariable("typeId") Integer typeId,@RequestBody FoodType foodType){
        foodType.setTypeId(typeId);
        foodType.setUpdateDate(getNowTime());
        JsonResult jsonResult = new JsonResult();
        if(foodService.deleteFoodType(foodType)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("删除成功");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("删除失败");
        }
        return  jsonResult;
    }
    @PostMapping("updateFoodType/{typeId}")
    @ResponseBody
    public JsonResult updateFoodType(@PathVariable("typeId") Integer typeId, @RequestBody FoodType foodType){
        foodType.setTypeId(typeId);
        foodType.setUpdateDate(getNowTime());
        JsonResult jsonResult = new JsonResult();
        try{
            if(foodService.updateFoodType(foodType)>0){
                jsonResult.setCode(0);
                jsonResult.setMsg("修改成功");
            }else{
                jsonResult.setCode(-1);
                jsonResult.setMsg("修改失败");
            }
        }catch (Exception e){
            jsonResult.setCode(-2);
            jsonResult.setMsg("修改异常");
        }
        return  jsonResult;
    }
    @GetMapping("queryFoodInformation")
    @ResponseBody
    public List<FoodInformation> queryFoodInformation(@RequestParam(required = false,value="foodName")String foodName, @RequestParam(required = false,value="typeName")String typeName
            , @RequestParam(required = false,value="maxPrice") Float maxPrice, @RequestParam(required = false,value="minPrice") Float minPrice){
        FoodInformation foodInformation = new FoodInformation();
        if(foodName!=null){
            foodInformation.setFoodName(foodName);
        }
        if(maxPrice!=null){
            foodInformation.setMaxPrice(maxPrice);
        }
        if(minPrice!=null){
            foodInformation.setMinPrice(minPrice);
        }
        if(typeName!=null){

            foodInformation.setTypeName(typeName);
        }
        return foodService.queryFoodInformation(foodInformation);
    }
    @PostMapping("addFood")
    @ResponseBody
    public JsonResult addNewFood(@RequestBody FoodInformation foodInformation){
        String date = getNowTime();
        foodInformation.setAddDate(date);
        foodInformation.setUpdateDate(date);
        JsonResult jsonResult = new JsonResult();

        if(foodService.insertNewFood(foodInformation)>0){
            int foodId = foodInformation.getFoodId();
            int result = insertStatus(foodId,date,foodInformation.getOperator());
            if(result>0){
                jsonResult.setCode(0);
                jsonResult.setMsg("添加成功");
            }else{
                jsonResult.setCode(-1);
                jsonResult.setMsg("添加失败");
            }
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("添加失败");
        }
        return  jsonResult;
    }
    @PostMapping("deleteFood/{foodId}")
    @ResponseBody
    public JsonResult deleteFood(@PathVariable("foodId") Integer foodId,@RequestBody FoodInformation foodInformation){
        foodInformation.setFoodId(foodId);
        foodInformation.setUpdateDate(getNowTime());
        JsonResult jsonResult = new JsonResult();
        if(foodService.deleteFood(foodInformation)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("删除成功");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("删除失败");
        }
        return  jsonResult;
    }
    @PostMapping("updateFood/{foodId}")
    @ResponseBody
    public JsonResult updateFood(@PathVariable("foodId") Integer foodId, @RequestBody FoodInformation foodInformation){
        foodInformation.setFoodId(foodId);
        foodInformation.setUpdateDate(getNowTime());
        JsonResult jsonResult = new JsonResult();
        try{
            if(foodService.updateFood(foodInformation)>0){
                jsonResult.setCode(0);
                jsonResult.setMsg("修改成功");
            }else{
                jsonResult.setCode(-1);
                jsonResult.setMsg("修改失败");
            }
        }catch (Exception e){
            jsonResult.setCode(-2);
            jsonResult.setMsg("修改异常");
        }
        return  jsonResult;
    }
    @GetMapping("queryFoodStatus")
    @ResponseBody
    public List<FoodStatus> queryFoodStatus(@RequestParam(required = false,value="foodName")String foodName, @RequestParam(required = false,value="typeName")String typeName
            , @RequestParam("status") String status){
        FoodStatus foodStatus = new FoodStatus();
        if(foodName!=null){
            foodStatus.setFoodName(foodName);
        }
        if(typeName!=null){
            foodStatus.setTypeName(typeName);
        }
        if(status!=null){
            foodStatus.setStatus(status);
        }
        return foodService.queryFoodStatus(foodStatus);
    }
    @PostMapping("upperFood/{statusId}")
    @ResponseBody
    public JsonResult uppperFood(@PathVariable("statusId") Integer statusId,@RequestBody FoodStatus foodStatus){
        foodStatus.setStatusId(statusId);
        foodStatus.setUpdateDate(getNowTime());
        JsonResult jsonResult = new JsonResult();
        if(foodService.upperFood(foodStatus)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("操作成功");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("操作失败");
        }
        return  jsonResult;
    }
    @PostMapping("lowerFood/{statusId}")
    @ResponseBody
    public JsonResult lowerFood(@PathVariable("statusId") Integer statusId,@RequestBody FoodStatus foodStatus){
        foodStatus.setStatusId(statusId);
        foodStatus.setUpdateDate(getNowTime());
        JsonResult jsonResult = new JsonResult();
        if(foodService.lowerFood(foodStatus)>0){
            jsonResult.setCode(0);
            jsonResult.setMsg("操作成功");
        }else{
            jsonResult.setCode(-1);
            jsonResult.setMsg("操作失败");
        }
        return  jsonResult;
    }
    @PostMapping("upImg")
    @ResponseBody
    public JsonResult upImg(HttpServletRequest request, @RequestParam(required = false,value="file")MultipartFile file){
        String orginalFileName =file.getOriginalFilename();
        String dirPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/img/";
        File filePath =new File(dirPath);
        System.out.println(dirPath);
        System.out.println(filePath.toString());
        System.out.println(orginalFileName);
        JsonResult jsonResult = new JsonResult();
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        try{
            file.transferTo(new File(dirPath+orginalFileName));
            jsonResult.setCode(0);
            jsonResult.setMsg("上传成功");
        }catch (Exception e){
            jsonResult.setCode(-1);
            jsonResult.setMsg("上传失败");
            e.printStackTrace();
        }
        jsonResult.setObject(new String("static/img/"+orginalFileName));
        return jsonResult;
    }
    public int insertStatus(int foodId,String date,String operator){
        FoodStatus foodStatus = new FoodStatus();
        foodStatus.setFoodId(foodId);
        foodStatus.setAddDate(date);
        foodStatus.setUpdateDate(date);
        foodStatus.setOperator(operator);
        return foodService.insertFoodStatus(foodStatus);
    }
}
