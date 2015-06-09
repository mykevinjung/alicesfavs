package com.alicesfavs.service;

/**
 * Created by kjung on 6/7/15.
 */
public interface CryptoService
{

    String encrypt(String plainText);
    String decrypt(String encryptedText);

}
