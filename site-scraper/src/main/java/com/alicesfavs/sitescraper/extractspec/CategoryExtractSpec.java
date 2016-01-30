package com.alicesfavs.sitescraper.extractspec;

public class CategoryExtractSpec
{

    public String baseUrl;

    /**
     * spec of an element that contains categories, such as top navigation, left navigation
     */
    public ElementExtractSpec containerSpec;

    /**
     * spec to extract category element within the container
     */
    public ElementExtractSpec categorySpec;

    // spec to extract category name data within the category element
    public ElementDataSpec nameSpec;

    // spec to extract category url data within the category element
    public ElementDataSpec urlSpec;

    // spec to extract subcategory if exists
    public CategoryExtractSpec subcategorySpec;

    public String toString()
    {
        return "CategoryExtractSpec[baseUrl=" + baseUrl + ", containerSpec=" + containerSpec + ", categorySpec=" + categorySpec
            + ", nameSpec=" + nameSpec + ", urlSpec=" + urlSpec + ", subcategorySpec=" + subcategorySpec + "]";
    }

}
