package com.PaymentController;

import com.C10G14.WorldFitBackend.controller.PaymentController;
import com.C10G14.WorldFitBackend.dto.PaymentDto;
import com.C10G14.WorldFitBackend.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Test
    public void testAddPayment() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(100.0);
        paymentDto.setDescription("Test payment");

        when(paymentService.createPayment(paymentDto)).thenReturn(paymentDto);

        ResponseEntity<PaymentDto> responseEntity = paymentController.addPayment(paymentDto);

        verify(paymentService).createPayment(paymentDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(paymentDto, responseEntity.getBody());
    }

    @Test
    public void testGetPaymentById() {
        Long id = 1L;
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(100.0);
        paymentDto.setDescription("Test payment");

        when(paymentService.getPaymentById(id)).thenReturn(paymentDto);

        ResponseEntity<PaymentDto> responseEntity = paymentController.getPaymentById(id);

        verify(paymentService).getPaymentById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(paymentDto, responseEntity.getBody());
    }

    @Test
    public void testUpdatePayment() {
        Long id = 1L;
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(200.0);
        paymentDto.setDescription("Updated payment");

        when(paymentService.updatePayment(id, paymentDto)).thenReturn(paymentDto);

        ResponseEntity<PaymentDto> responseEntity = paymentController.updatePayment(id, paymentDto);

        verify(paymentService).updatePayment(id, paymentDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(paymentDto, responseEntity.getBody());
    }

    @Test
    public void testDeletePayment() {
        Long id = 1L;

        ResponseEntity<Void> responseEntity = paymentController.deletePayment(id);

        verify(paymentService).deletePayment(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
