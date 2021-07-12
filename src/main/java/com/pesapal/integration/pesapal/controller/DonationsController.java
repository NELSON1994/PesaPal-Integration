package com.pesapal.integration.pesapal.controller;

import com.pesapal.integration.pesapal.dao.DonationDao;
import com.pesapal.integration.pesapal.services.DonationService;
import com.pesapal.integration.pesapal.wrapper.GenericResponseWrapper;
import com.pesapal.integration.pesapal.wrapper.UserLoginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class DonationsController {
    @Autowired
    private DonationService donationService;

    @PostMapping("/donate")
    public ResponseEntity<GenericResponseWrapper> donate(@RequestBody DonationDao donationDao) {
        GenericResponseWrapper genericResponseWrapper = donationService.saveDonation(donationDao);
        return ResponseEntity.status(genericResponseWrapper.getCode()).body(genericResponseWrapper);
    }

    @GetMapping("/view-donations")
    public ResponseEntity<GenericResponseWrapper> viewDonations() {
        GenericResponseWrapper genericResponseWrapper = donationService.viewAll();
        return ResponseEntity.status(genericResponseWrapper.getCode()).body(genericResponseWrapper);
    }
}
