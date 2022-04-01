package com.vrfvitor.knwallets.service;

import com.vrfvitor.knwallets.model.*;
import com.vrfvitor.knwallets.repository.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository repository;

    public List<Wallet> getAll() {
        return repository.findAll();
    }

    public Optional<Wallet> getById(UUID id) {
        return repository.findById(id);
    }

    public Wallet save(Wallet wallet) {
        return repository.save(wallet);
    }

    public Wallet deposit(Wallet wallet, Long amountCents) {
        var newBalance = wallet.getBalanceCents() + amountCents;
        wallet.setBalanceCents(newBalance);
        return repository.save(wallet);
    }

    public Wallet withdraw(Wallet wallet, Long withdrawCents) {
        var newBalance = wallet.getBalanceCents() - withdrawCents;
        if (newBalance < 0)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        wallet.setBalanceCents(newBalance);
        return repository.save(wallet);
    }
}
