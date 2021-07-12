package com.pesapal.integration.pesapal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DONATIONS")
public class Donations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DONATION_ID")
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstname;
    @Column(name = "LAST_NAME")
    private String lastname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;
    @Column(name = "REFERENCE")
    private String reference;
    @Column(name = "PESAPAL_TRACKING_ID")
    private String pesapalTrackingId;
    @Column(name = "CREATION_DATE")
    private Date insertionDate= new Date();
}
