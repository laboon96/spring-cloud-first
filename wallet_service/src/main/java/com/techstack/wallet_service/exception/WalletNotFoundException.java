package com.techstack.wallet_service.exception;

public class WalletNotFoundException extends RuntimeException{
    
    public WalletNotFoundException() {
        super("Can not find wallet data");
    }
}
