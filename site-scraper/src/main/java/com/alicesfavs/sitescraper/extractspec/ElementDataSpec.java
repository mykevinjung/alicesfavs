package com.alicesfavs.sitescraper.extractspec;

public class ElementDataSpec
{

    /**
     * spec of container element<br>
     * This is needed usually for <a> tag because there can be many <a> tags and we need to be able identify using a
     * containing element
     */
    public ElementExtractSpec containerSpec;

    /**
     * The element where we are trying to extract data from
     */
    public ElementExtractSpec elementSpec;

    /**
     * Specification on how to extract data from the extracted element
     */
    public DataExtractSpec dataSpec;

    public String toString()
    {
        return "ElementDataSpec[containerSpec=" + containerSpec + ", elementSpec=" + elementSpec + ", dataSpec="
                + dataSpec + "]";
    }

}
