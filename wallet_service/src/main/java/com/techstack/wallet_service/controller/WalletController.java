package com.techstack.wallet_service.controller;

import com.techstack.wallet_service.entity.Wallet;
import com.techstack.wallet_service.exception.WalletNotFoundException;
import com.techstack.wallet_service.repository.WalletRepository;
import com.techstack.wallet_service.service.WalletModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class WalletController {
    
    @Autowired
    WalletRepository walletRepository;
    
    @Autowired
    WalletModelAssembler assembler;
    
    @GetMapping("/wallets")
    public CollectionModel<EntityModel<Wallet>> getAll() {
        List<EntityModel<Wallet>> users = walletRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(users, linkTo(methodOn(this.getClass()).getAll()).withSelfRel());
    }
    
    @GetMapping("/wallets/{id}")
    public EntityModel<Wallet> getWalletById(@PathVariable Long id) {
        return walletRepository.findById(id)
                .map(assembler::toModel)
                .orElseThrow(WalletNotFoundException::new);
        
    }
    
    @PutMapping("/wallets/{id}")
    public ResponseEntity<?> updateWalletAmount(@PathVariable Long walletId, @RequestBody BigDecimal amount) {
        EntityModel<Wallet> walletEntity = walletRepository.findById(walletId)
                .map(wallet -> {
                    wallet.setAmount(amount);
                    return walletRepository.save(wallet);
                })
                .map(assembler::toModel)
                .orElseThrow(WalletNotFoundException::new);
    
        return ResponseEntity
                .created(walletEntity.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(walletEntity);
    }
    
    @PostMapping("/wallets")
    ResponseEntity<?> newWallet(@RequestBody Wallet newWallet) {
        EntityModel<Wallet> entityModel = assembler.toModel(walletRepository.save(newWallet));
        
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    
    @DeleteMapping("/wallets/{id}")
    ResponseEntity<?> deleteWallet(@PathVariable Long id) {
        walletRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
