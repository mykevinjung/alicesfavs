package com.alicesfavs.webapp.service;

import com.alicesfavs.webapp.config.WebAppConfig;
import com.alicesfavs.webapp.exception.EncryptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by kjung on 1/29/16.
 */
@Component
public class Encryptor {

    private static final String AES = "AES";
    private static final String UTF8 = "UTF-8";
    private static final String CIPHER_INSTANCE = "AES/CBC/PKCS5PADDING";

    @Autowired
    private WebAppConfig webAppConfig;

    private IvParameterSpec iv;
    private SecretKeySpec keySpec;

    @PostConstruct
    public void init() throws Exception
    {
        iv = new IvParameterSpec(webAppConfig.getEncryptorInitVector().getBytes(UTF8));
        keySpec = new SecretKeySpec(webAppConfig.getEncryptorKey().getBytes(UTF8), AES);
    }

    public String encrypt(String value) throws EncryptionException
    {
        try
        {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        }
        catch (Exception e)
        {
            throw new EncryptionException("Encryption error. Input: " + value, e);
        }
    }

    public String decrypt(String encrypted) throws EncryptionException
    {
        try
        {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

            byte[] encryptedTextByte = Base64.getDecoder().decode(encrypted);
            byte[] original = cipher.doFinal(encryptedTextByte);

            return new String(original);
        }
        catch (Exception e)
        {
            throw new EncryptionException("Decryption error. Input: " + encrypted, e);
        }
    }

}
