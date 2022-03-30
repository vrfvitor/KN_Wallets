package com.vrfvitor.knwallets.service;

import com.vrfvitor.knwallets.model.*;
import com.vrfvitor.knwallets.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

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
}
