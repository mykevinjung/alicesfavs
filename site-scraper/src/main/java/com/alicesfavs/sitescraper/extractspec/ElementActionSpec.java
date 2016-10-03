package com.alicesfavs.sitescraper.extractspec;

/**
 * Created by kjung on 10/2/16.
 */
public class ElementActionSpec
{
    // element with id
    public String id;

    // element with tag
    public String tagName;

    // element with class name
    public String className;

    // element with name
    public String name;

    // element with link text
    public String linkText;

    // element with partial link text
    public String partialLinkText;

    public ElementAction elementAction;

    public String toString()
    {
        return "ElementActionSpec[id=" + id + ", tagName=" + tagName + ", className=" + className
            + ", name=" + name + ", linkText=" + linkText + ", partialLinkText=" + partialLinkText
            + ", elementAction=" + elementAction + "]";
    }

}
