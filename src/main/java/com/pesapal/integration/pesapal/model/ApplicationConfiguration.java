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
@Table(name="APP_CONFIGS")
public class ApplicationConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "APP_CONFIG_ID")
    private Long id;
    @Column(name = "CONSUMER_KEY")
    private String consumerKey;
    @Column(name = "CONSUMER_SECRET")
    private String consumerSecret;
    @Column(name = "CREATION_DATE")
    private Date insertionDate= new Date();
}
