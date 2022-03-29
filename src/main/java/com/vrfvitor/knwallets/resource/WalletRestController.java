package com.vrfvitor.knwallets.resource;

import com.vrfvitor.knwallets.model.*;
import com.vrfvitor.knwallets.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallets")
public class WalletRestController {

    private final WalletService service;

    @GetMapping
    private ResponseEntity<List<Wallet>> index() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Wallet> show(@PathVariable("id") UUID id) {
        var optionalWallet = service.getById(id);
        if (optionalWallet.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(optionalWallet.get());
    }

}
