package com.pesapal.integration.pesapal.services;

import com.pesapal.integration.pesapal.dao.DonationDao;
import com.pesapal.integration.pesapal.model.ApplicationConfiguration;
import com.pesapal.integration.pesapal.repository.AppsConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class IntegrationToPesaPal {

    @Autowired
    private AppsConfigRepository appsConfigRepository;
    public void sendDonationToPesaPal(DonationDao donationDao){
        List<ApplicationConfiguration> applicationConfigurations=appsConfigRepository.findAll();
        if(applicationConfigurations.size()>0){
            ApplicationConfiguration app=applicationConfigurations.get(0);
            String consumer_secr=app.getConsumerSecret();
            String consumer_ky=app.getConsumerKey();
        }
        else{

        }

        try {
            String url = "https://www.pesapal.com/API/PostPesapalDirectOrderV4";
            String oauth_callback="";//url to redirect
            String oauth_consumer_key="";
            String consumer_secret="";
            String oauth_nonce="";// date time based value
            String oauth_signature="";
            String oauth_signature_method="HMAC-SHA1";
            String oauth_timestamp="";
            String oauth_version="1.0";
            String pesapal_request_data="";
            String reference="";
            String type="MERCHANT";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
            con.setRequestProperty("oauth_callback",oauth_callback);
            con.setRequestProperty("consumer_secret",consumer_secret);
            con.setRequestProperty("oauth_consumer_key",oauth_consumer_key);
            con.setRequestProperty("oauth_nonce",oauth_nonce);
            con.setRequestProperty("oauth_signature",oauth_signature);
            con.setRequestProperty("oauth_signature_method",oauth_signature_method);
            con.setRequestProperty("oauth_timestamp",oauth_timestamp);
            con.setRequestProperty("oauth_version",oauth_version);
            String countryCode="Canada";
            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                    "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> " +
                    " <soap12:Body> " +
                    " <GetHolidaysAvailable xmlns=\"http://www.holidaywebservice.com/HolidayService_v2/\"> " +
                    " <countryCode>"+countryCode+"</countryCode>" +
                    " </GetHolidaysAvailable>" +
                    " </soap12:Body>" +
                    "</soap12:Envelope>";
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            System.out.println(responseStatus);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("response:" + response.toString());
            // read the response to java class

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
