package com.vrfvitor.knwallets.resource;

import com.vrfvitor.knwallets.dto.*;
import com.vrfvitor.knwallets.model.*;
import com.vrfvitor.knwallets.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;
import org.springframework.web.util.*;

import javax.validation.*;
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

    @PostMapping
    private ResponseEntity<Wallet> create(@RequestBody @Valid WalletForm form, UriComponentsBuilder uriBuilder) {
        var wallet = this.service.save(form.converter());
        var uri = uriBuilder.path("/wallets/{id}").buildAndExpand(wallet.getId()).toUri();
        return ResponseEntity.created(uri).body(wallet);
    }

    @PostMapping("{id}/deposit")
    private ResponseEntity<Wallet> deposit(@PathVariable("id") UUID id, @RequestBody @Valid ValueForm form) {
        var optionalWallet = service.getById(id);
        if (optionalWallet.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        var wallet = service.deposit(optionalWallet.get(), form.getAmountCents());
        return ResponseEntity.ok(wallet);
    }
}
