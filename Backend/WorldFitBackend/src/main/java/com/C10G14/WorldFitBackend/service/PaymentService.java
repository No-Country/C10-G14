package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.PaymentDto;
import com.C10G14.WorldFitBackend.dto.RoutineDto;
import com.C10G14.WorldFitBackend.entity.Payment;

import java.util.List;

public interface PaymentService {

    PaymentDto getPaymentById (Long id);

    PaymentDto createPayment (PaymentDto paymentDto);

    PaymentDto updatePayment (Long id, PaymentDto paymentDto);
    void deletePayment (Long id);
}
