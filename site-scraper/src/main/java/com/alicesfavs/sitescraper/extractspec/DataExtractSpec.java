package com.alicesfavs.sitescraper.extractspec;

public class DataExtractSpec
{

    // custom keyword for nested text in html tag
    public static String ATTRIBUTE_KEY_TEXT = "$nested_text";
    public static String ATTRIBUTE_KEY_OWNTEXT = "$own_text";

    // attribute key to extract data
    public String attributeKey;

    // attribute value that matches a regex pattern
    public String valuePattern;

    public String valueExcludePattern;

    // pattern to extract substring from the extracted value
    public String substringPattern;

    public String toString()
    {
        return "DataExtractSpec[attributeKey=" + attributeKey + ", valuePattern=" + valuePattern
                + ", valueExcludePattern=" + valueExcludePattern + ", substringPattern=" + substringPattern + "]";
    }

}
