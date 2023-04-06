package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.PaymentDto;
import com.C10G14.WorldFitBackend.entity.Payment;
import com.C10G14.WorldFitBackend.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @Operation(summary = "add a new payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new payment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PostMapping("")
    public ResponseEntity<PaymentDto> addPayment(@RequestBody PaymentDto payment) {
        PaymentDto newPayment = paymentService.createPayment(payment);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a payment by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A payment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: The payment does not exist",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("id") Long id) {
        PaymentDto payment = paymentService.getPaymentById(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @Operation(summary = "Update a existing payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A updated payment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: The payment does not exist",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable("id") Long id, @RequestBody PaymentDto payment) {
        PaymentDto updatedPayment = paymentService.updatePayment(id, payment);
        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
    }

    @Operation(summary = "Delete a payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: The payment does not exist",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable("id") Long id) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

