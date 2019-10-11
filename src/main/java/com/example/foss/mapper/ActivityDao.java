package com.example.foss.mapper;

import com.example.foss.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityDao {
    public List<Activity> queryActivity(Activity activity);
    public int insertActivity(Activity activity);
    public int updateActivity(Activity activity);
    public int deleteActivity(Activity activity);
}
