package com.alicesfavs.dataaccess;

import com.alicesfavs.datamodel.User;

/**
 * Created by kjung on 5/23/15.
 */
public interface UserDao
{

    User insertUser(String emailAddress, String password, String name, Integer favLimit, User.Status status);

    User updateUser(User user);

    User selectUserById(long id);

    User selectUserByEmailAddress(String emailAddress);

}
