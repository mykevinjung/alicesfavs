package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

public class Site extends ModelBase
{
    public Country country;
    public final String stringId;
    public String displayName;
    public String url;
    public boolean display;
    public Integer displayWeight;
    public boolean useStoredImage;
    public String currency;

    public Site(long id, LocalDateTime createdDate, String stringId)
    {
        this(id, createdDate, stringId, Country.US);
    }

    public Site(long id, LocalDateTime createdDate, String stringId, Country country)
    {
        super(id, createdDate);
        Assert.hasText(stringId);
        this.stringId = stringId;
        this.country = country;
    }

    public Site(ModelBase modelBase, String stringId)
    {
        super(modelBase);
        Assert.hasText(stringId);
        this.stringId = stringId;
    }

    public String toString()
    {
        return "Site[" + super.toString() + ", country=" + country + ", stringId=" + stringId + ", displayName="
            + displayName + ", url=" + url + ", display=" + display + ", displayWeight=" + displayWeight
            + ", useStoredImage=" + useStoredImage + ", currency=" + currency + "]";
    }

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }

    public String getStringId()
    {
        return stringId;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public boolean isDisplay()
    {
        return display;
    }

    public void setDisplay(boolean display)
    {
        this.display = display;
    }

    public Integer getDisplayWeight()
    {
        return displayWeight;
    }

    public void setDisplayWeight(Integer displayWeight)
    {
        this.displayWeight = displayWeight;
    }

    public boolean isUseStoredImage()
    {
        return useStoredImage;
    }

    public void setUseStoredImage(boolean useStoredImage)
    {
        this.useStoredImage = useStoredImage;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

}
