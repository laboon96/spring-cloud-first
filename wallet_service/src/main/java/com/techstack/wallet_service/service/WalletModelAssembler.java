package com.techstack.wallet_service.service;

import com.techstack.wallet_service.controller.WalletController;
import com.techstack.wallet_service.entity.Wallet;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class WalletModelAssembler implements RepresentationModelAssembler<Wallet, EntityModel<Wallet>> {
    
    @Override
    public EntityModel<Wallet> toModel(Wallet user) {
        
        return EntityModel.of(user,
                linkTo(methodOn(WalletController.class).getWalletById(user.getId())).withSelfRel(),
                linkTo(methodOn(WalletController.class).getAll()).withSelfRel());
    }
}
