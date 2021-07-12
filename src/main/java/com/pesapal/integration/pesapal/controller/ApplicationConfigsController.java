package com.pesapal.integration.pesapal.controller;

import com.pesapal.integration.pesapal.dao.AppConfigDao;
import com.pesapal.integration.pesapal.dao.DonationDao;
import com.pesapal.integration.pesapal.services.AppConfigsService;
import com.pesapal.integration.pesapal.wrapper.GenericResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ApplicationConfigsController {
    @Autowired
    private AppConfigsService appConfigsService;

    @PostMapping("/set-app-configs")
    public ResponseEntity<GenericResponseWrapper> setApplicationsConfigs(@RequestBody AppConfigDao appConfigDao) {
        GenericResponseWrapper genericResponseWrapper = appConfigsService.setAppsConfig(appConfigDao);
        return ResponseEntity.status(genericResponseWrapper.getCode()).body(genericResponseWrapper);
    }

}
