package com.example.foss.service.impl;

import com.example.foss.mapper.ActivityDao;
import com.example.foss.pojo.Activity;
import com.example.foss.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityDao activityDao;
    @Override
    public List<Activity> queryActivity(Activity activity) {
        return activityDao.queryActivity(activity);
    }

    @Override
    public int insertActivity(Activity activity) {
        return activityDao.insertActivity(activity);
    }

    @Override
    public int updateActivity(Activity activity) {
        return activityDao.updateActivity(activity);
    }

    @Override
    public int deleteActivity(Activity activity) {
        return activityDao.deleteActivity(activity);
    }
}
