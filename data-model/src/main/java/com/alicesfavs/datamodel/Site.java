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
    public String currency;
    public String cookies;
    public Long defaultAliceCategoryId;

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
            + ", currency=" + currency + ", cookies=" + cookies + ", defaultAliceCategoryId=" + defaultAliceCategoryId + "]";
    }

}
