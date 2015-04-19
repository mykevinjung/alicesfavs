package com.alicesfavs.dataaccess;

import java.util.List;

import com.alicesfavs.datamodel.PriceHistory;

public interface PriceHistoryDao
{

    PriceHistory insertPriceHistory(long productId, String oldPriceExtract, String newPriceExtract, Double oldPrice,
            Double newPrice);

    List<PriceHistory> selectPriceHistoryByProductId(long productId);

}
