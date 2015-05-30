package com.alicesfavs.service;

import com.alicesfavs.datamodel.User;
import com.alicesfavs.datamodel.UserVerification;

/**
 * Created by kjung on 5/30/15.
 */
public interface EmailService
{

    void sendUserVerificationEmail(User user, UserVerification userVerification);

}
