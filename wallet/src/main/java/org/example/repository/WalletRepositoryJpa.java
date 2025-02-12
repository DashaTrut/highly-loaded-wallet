package org.example.repository;

import org.example.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepositoryJpa extends JpaRepository<Wallet, UUID> {

}
