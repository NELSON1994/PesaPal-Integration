package com.pesapal.integration.pesapal.repository;

import com.pesapal.integration.pesapal.model.Donations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donations,Long> {
}
