package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.RequestOperation;
import org.example.exception.EntityNotFoundException;
import org.example.exception.ValidationException;
import org.example.model.Wallet;
import org.example.repository.WalletRepositoryJpa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepositoryJpa walletRepositoryJpa;

    @Transactional
    public void operation(RequestOperation requestOperation) {

        Wallet wallet = checkWallet(requestOperation.getId());
        BigDecimal newBalance;
        switch (requestOperation.getOperationType()) {
            case DEPOSIT:
                newBalance = wallet.getBalance().add(requestOperation.getAmount());
                wallet.setBalance(newBalance);
                break;
            case WITHDRAW:
                newBalance = wallet.getBalance().subtract(requestOperation.getAmount());
                checkBalance(newBalance);
                wallet.setBalance(newBalance);
                break;
        }
        walletRepositoryJpa.save(wallet);
    }

    public BigDecimal getBalance (UUID uuid){
        Wallet wallet = checkWallet(uuid);
        return wallet.getBalance();
    }

    // Метод проверки существования кошелька
    public Wallet checkWallet(UUID id) {
        return walletRepositoryJpa.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Кошелька с таким UUID не существует."));
    }

    // Метод проверки положительного баланса
    private void checkBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Ошибка: Недостаточно средств. Баланс не может быть отрицательным.");
        }
    }
}
