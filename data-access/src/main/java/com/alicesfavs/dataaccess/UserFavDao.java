package com.alicesfavs.dataaccess;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.UserFav;

import java.util.List;

/**
 * Created by kjung on 5/17/15.
 */
public interface UserFavDao
{

    UserFav insertUserFav(long userId, Product product, String addOnName, Double addOnPrice);

    void deleteUserFav(UserFav userFav);

	UserFav selectUserFavById(long id);

	List<UserFav> selectUserFavByUserId(long userId);

}
