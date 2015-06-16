package com.alicesfavs.dataaccess;

import com.alicesfavs.datamodel.AliceCategory;

import java.util.List;

/**
 * Created by kjung on 6/15/15.
 */
public interface AliceCategoryDao
{

    List<AliceCategory> selectAliceCategoriesByDisplay(boolean display);

}
