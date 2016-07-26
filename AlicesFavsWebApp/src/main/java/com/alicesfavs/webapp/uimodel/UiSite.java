package com.alicesfavs.webapp.uimodel;

/**
 * Created by kjung on 11/6/15.
 */
public class UiSite
{

    private String stringId;
    private String displayName;
    private boolean newSite;

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getStringId()
    {
        return stringId;
    }

    public void setStringId(String stringId)
    {
        this.stringId = stringId;
    }

    public boolean isNewSite()
    {
        return newSite;
    }

    public void setNewSite(boolean newSite)
    {
        this.newSite = newSite;
    }
}
