package com.pesapal.integration.pesapal.services;

import com.pesapal.integration.pesapal.config.AppConfigs;
import com.pesapal.integration.pesapal.dao.AppConfigDao;
import com.pesapal.integration.pesapal.model.ApplicationConfiguration;
import com.pesapal.integration.pesapal.repository.AppsConfigRepository;
import com.pesapal.integration.pesapal.wrapper.GenericResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppConfigsService {

    @Autowired
    private AppsConfigRepository appsConfigRepository;

    public GenericResponseWrapper setAppsConfig(AppConfigDao appConfigDao){
        GenericResponseWrapper genericResponseWrapper=new GenericResponseWrapper();
        try{
            ApplicationConfiguration appConfigs=new  ApplicationConfiguration();
            appConfigs.setConsumerKey(appConfigDao.getConsumerKey());
            appConfigs.setConsumerSecret(appConfigDao.getConsumerSecret());
            appsConfigRepository.save(appConfigs);
            genericResponseWrapper.setCode(200);
            genericResponseWrapper.setStatus("SUCCESS");
            genericResponseWrapper.setMessage("Configurations Set Successfully");
            genericResponseWrapper.setData(appConfigs);
        }
        catch(Exception e){
            genericResponseWrapper.setCode(400);
            genericResponseWrapper.setStatus("FAILED");
            genericResponseWrapper.setMessage("System error Occurred");
        }
        return genericResponseWrapper;
    }

}
