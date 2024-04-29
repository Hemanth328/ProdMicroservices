package com.pbs.repo;

import com.pbs.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> findByMobileNumber(String mobileNumber);
}
