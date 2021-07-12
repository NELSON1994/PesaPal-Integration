package com.pesapal.integration.pesapal.services;


import com.pesapal.integration.pesapal.dao.DonationDao;
import com.pesapal.integration.pesapal.model.Donations;
import com.pesapal.integration.pesapal.repository.DonationRepository;
import com.pesapal.integration.pesapal.wrapper.GenericResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public GenericResponseWrapper saveDonation(DonationDao donationDao){
        GenericResponseWrapper genericResponseWrapper=new GenericResponseWrapper();
        try{
            Donations donations=new Donations();
            donations.setAmount(donationDao.getAmount());
            donations.setEmail(donationDao.getEmail());
            donations.setFirstname(donationDao.getFirstname());
            donations.setLastname(donationDao.getLasttname());
            donations.setPhoneNumber(donationDao.getPhoneNumber());
            donations.setPaymentStatus("PENDING");

            // ===integrations to paypal

            //
            donationRepository.save(donations);
            genericResponseWrapper.setCode(200);
            genericResponseWrapper.setStatus("SUCCESS");
            genericResponseWrapper.setMessage("Saved Successfully");
        }
        catch(Exception e){
            genericResponseWrapper.setCode(400);
            genericResponseWrapper.setStatus("FAILED");
            genericResponseWrapper.setMessage("System error Occurred");
        }
        return genericResponseWrapper;
    }


    public GenericResponseWrapper viewAll(){
        GenericResponseWrapper genericResponseWrapper=new GenericResponseWrapper();
        try{
            List<Donations> donations=donationRepository.findAll();
            genericResponseWrapper.setCode(200);
            genericResponseWrapper.setStatus("SUCCESS");
            genericResponseWrapper.setMessage("Saved Successfully");
            genericResponseWrapper.setData(donations);
        }
        catch(Exception e){
            genericResponseWrapper.setCode(400);
            genericResponseWrapper.setStatus("FAILED");
            genericResponseWrapper.setMessage("System error Occurred");
        }
        return genericResponseWrapper;
    }
}
