package com.example.foss.service;


import com.example.foss.pojo.Activity;

import java.util.List;

public interface ActivityService {
    public List<Activity> queryActivity(Activity activity);
    public int insertActivity(Activity activity);
    public int updateActivity(Activity activity);
    public int deleteActivity(Activity activity);
}
