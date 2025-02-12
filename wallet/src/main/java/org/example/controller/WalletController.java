package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RequestOperation;
import org.example.service.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.UUID;

@Controller
@Validated
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/wallet")
    public void operationWallet (RequestOperation requestOperation){
        walletService.operation(requestOperation);
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public BigDecimal getBalance (@PathVariable UUID WALLET_UUID){
        return walletService.getBalance(WALLET_UUID);
    }
}
