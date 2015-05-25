package com.alicesfavs.dataaccess;

import com.alicesfavs.datamodel.UserVerification;

import java.time.LocalDateTime;

/**
 * Created by kjung on 5/17/15.
 */
public interface UserVerificationDao
{

    UserVerification insertUserVerification(long userId, String emailAddressHash, String verificationCode,
        boolean verified, LocalDateTime expirationDate);

    UserVerification updateUserVerification(UserVerification userVerification);

    UserVerification selectUserVerificationByHash(String emailAddressHash);

}
