package com.alicesfavs.service.impl;

import com.alicesfavs.dataaccess.AliceCategoryDao;
import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.service.AliceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by kjung on 6/15/15.
 */
public class AliceCategoryServiceImpl implements AliceCategoryService
{

    @Autowired
    private AliceCategoryDao aliceCategoryDao;

    @Override public List<AliceCategory> findAllAliceCategories()
    {
        return aliceCategoryDao.selectAliceCategoriesByDisplay(true);
    }

}