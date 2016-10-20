package com.alicesfavs.sitescraper.extractspec;

/**
 * Created by kjung on 10/2/16.
 */
public class ElementActionSpec
{
    // xpath of the element
    public String xpath;

    public ElementAction elementAction;

    public String toString()
    {
        return "ElementActionSpec[xpath=" + xpath + elementAction + "]";
    }

}
