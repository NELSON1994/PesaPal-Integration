package com.pesapal.integration.pesapal.repository;

import com.pesapal.integration.pesapal.model.ApplicationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppsConfigRepository extends JpaRepository<ApplicationConfiguration,Long> {
}
