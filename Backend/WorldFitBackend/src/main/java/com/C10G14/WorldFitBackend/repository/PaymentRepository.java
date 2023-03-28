package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
