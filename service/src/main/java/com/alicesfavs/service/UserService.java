package com.alicesfavs.service;

import com.alicesfavs.datamodel.User;

/**
 * Created by kjung on 5/16/15.
 */
public interface UserService
{

    User register(String email, String password, String name);

    User register(String email, String password, String name, Integer favLimit);

    User login(String email, String password, boolean keepMeSignedIn);

    // TODO
    void logout();

    String generateGuid();

}
