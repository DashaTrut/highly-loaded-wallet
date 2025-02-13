package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RequestOperation;
import org.example.model.OperationType;
import org.example.service.WalletService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(WalletController.class)
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testGetBalance() throws Exception {
        UUID walletId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        // Замокали ответ сервиса
        when(walletService.getBalance(walletId)).thenReturn(new BigDecimal("100.00"));

        mockMvc.perform(get("/api/v1/wallets/{WALLET_UUID}", walletId))
                .andExpect(status().isOk()) // Ожидаем 200 OK
                .andExpect(content().string("100.00")); // Проверяем ответ
    }


    @Test
    void testOperationWallet() throws Exception {
        doNothing().when(walletService).operation(any(RequestOperation.class));

        String requestJson = mapper.writeValueAsString(new RequestOperation( UUID.randomUUID(), OperationType.DEPOSIT, new BigDecimal(50.00)));


        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }
}
