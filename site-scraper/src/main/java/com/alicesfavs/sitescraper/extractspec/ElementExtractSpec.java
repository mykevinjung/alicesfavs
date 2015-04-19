package com.alicesfavs.sitescraper.extractspec;

/**
 * Extract specification to find an element
 */
public class ElementExtractSpec
{

    // element with id
    public String id;

    // element with tag
    public String tagName;

    // element with class name
    public String className;

    // element with specific attribute key
    public String attributeKey;

    // element with specific attribute value pattern
    public String valuePattern;

    public String toString()
    {
        return "ElementExtractSpec[id=" + id + ", tagName=" + tagName + ", className=" + className + ", attributeKey="
                + attributeKey + ", valuePattern=" + valuePattern + "]";
    }

}
