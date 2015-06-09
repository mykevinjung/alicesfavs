package com.alicesfavs.service.impl;

import com.alicesfavs.service.CryptoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;
import java.util.Base64;

/**
 * Created by kjung on 6/7/15.
 */
@Component("cryptoService")
public class CryptoServiceImpl implements InitializingBean, CryptoService
{

    private Cipher cipher;
    private SecretKey secretKey;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        // TODO if this service is really need, change this key code
        // http://www.avajava.com/tutorials/lessons/how-do-i-encrypt-and-decrypt-files-using-des.html
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        secretKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance("AES");
    }

    @Override
    public String encrypt(String plainText)
    {
        try
        {
            byte[] plainTextByte = plainText.getBytes();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedByte = cipher.doFinal(plainTextByte);
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(encryptedByte);
        }
        catch (GeneralSecurityException e)
        {
            throw new RuntimeException("Exception in encrypting", e);
        }
    }

    @Override
    public String decrypt(String encryptedText)
    {
        try
        {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encryptedTextByte = decoder.decode(encryptedText);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
            return new String(decryptedByte);
        }
        catch (GeneralSecurityException e)
        {
            throw new RuntimeException("Exception in decrypting", e);
        }
    }

}
