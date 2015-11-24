package com.alicesfavs.sitescraper.impl;

import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.datamodel.ProductExtract;
import com.alicesfavs.sitescraper.extractspec.CategoryExtractSpec;
import com.alicesfavs.sitescraper.extractspec.DataExtractSpec;
import com.alicesfavs.sitescraper.extractspec.ElementDataSpec;
import com.alicesfavs.sitescraper.extractspec.ElementExtractSpec;
import com.alicesfavs.sitescraper.extractspec.NextPageExtractSpec;
import com.alicesfavs.sitescraper.extractspec.ProductExtractSpec;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kjung on 10/24/14.
 */
public class DataExtractor
{

    public List<CategoryExtract> extractCategories(Element rootElement, CategoryExtractSpec categoryExtractSpec)
            throws ElementNotFoundException, DataNotFoundException
    {
        // TODO let's use set instead of list
        final Elements elements = extractElements(rootElement, categoryExtractSpec.containerSpec,
                categoryExtractSpec.categorySpec);

        final List<CategoryExtract> categoryList = new ArrayList<CategoryExtract>();
        for (final Element element : elements)
        {
            try
            {
                final String name = extractData(element, categoryExtractSpec.nameSpec);
                CategoryExtract categoryExtract = new CategoryExtract(name);
                if (categoryExtractSpec.urlSpec == null)
                {
                    categoryExtract.url = rootElement.baseUri();
                }
                else
                {
                    categoryExtract.url = extractData(element, categoryExtractSpec.urlSpec);
                }
                categoryList.add(categoryExtract);
                //System.out.println(categoryExtract);
            }
            catch (ElementNotFoundException | DataNotFoundException e)
            {
                // for those excluded data
            }
        }

        return categoryList;
    }

    public List<ProductExtract> extractProducts(Element rootElement, ProductExtractSpec productExtractSpec)
            throws ElementNotFoundException, DataNotFoundException
    {
        final Elements elements = extractElements(rootElement, productExtractSpec.containerSpec,
                productExtractSpec.productSpec);

        final List<ProductExtract> productList = new ArrayList<>();
        for (final Element element : elements)
        {
            try
            {
                // TODO Decode/encode name field!!!!!!!!!!
                String id = extractData(element, productExtractSpec.idSpec);
                ProductExtract productExtract = new ProductExtract(id);
                productExtract.name = extractData(element, productExtractSpec.nameSpec);
                productExtract.url = extractData(element, productExtractSpec.urlSpec);
                productExtract.imageUrl = extractData(element, productExtractSpec.imageUrlSpecList);
                productExtract.price = extractData(element, productExtractSpec.priceSpecList);
                try
                {
                    if (productExtractSpec.wasPriceSpecList != null)
                    {
                        productExtract.wasPrice = extractData(element, productExtractSpec.wasPriceSpecList);
                        if (productExtract.wasPrice.equals(productExtract.price))
                        {
                            productExtract.wasPrice = null;
                        }
                    }
                }
                catch (DataNotFoundException e)
                {
                    // ignore, optional
                }
                try
                {
                    if (productExtractSpec.brandNameSpec != null)
                    {
                        productExtract.brandName = extractData(element, productExtractSpec.brandNameSpec);
                    }
                }
                catch (DataNotFoundException e)
                {
                    // ignore, optional
                }
                productList.add(productExtract);
                //System.out.println(productExtract);
            }
            catch (DataNotFoundException e)
            {
                // for those excluded data
            }
        }

        return productList;
    }

    public String extractNextPageUrl(Element rootElement, NextPageExtractSpec nextPageExtractSpec)
            throws ElementNotFoundException, DataNotFoundException
    {
        return extractData(rootElement, nextPageExtractSpec.nextPageSpec);
    }

    private String extractData(Element rootElement, List<ElementDataSpec> elementDataSpecList)
            throws DataNotFoundException
    {
        for (ElementDataSpec elementDataSpec : elementDataSpecList)
        {
            try
            {
                return extractData(rootElement, elementDataSpec);
            }
            catch (ElementNotFoundException | DataNotFoundException e)
            {
                // this is possible especially for price when there is no sale price element
            }
        }

        // if no data found for any element data spec
        throw new DataNotFoundException("Data not found on " + rootElement.baseUri() + " - " + elementDataSpecList
                + " - " + rootElement);
    }

    private String extractData(Element rootElement, ElementDataSpec elementDataSpec) throws ElementNotFoundException,
            DataNotFoundException
    {
        // get the container element first
        if (!isEmptySpec(elementDataSpec.containerSpec))
        {
            rootElement = extractElements(rootElement, elementDataSpec.containerSpec).first();
        }
        final Element element = extractElements(rootElement, elementDataSpec.elementSpec).first();
        return extractData(element, elementDataSpec.dataSpec);
    }

    private String extractData(Element element, DataExtractSpec dataSpec) throws DataNotFoundException
    {
        Assert.hasText(dataSpec.attributeKey, "DataExtractSpec should have attributeKey");

        String data = getElementAttribute(element, dataSpec.attributeKey);
        if (StringUtils.hasText(data)
                && (!StringUtils.hasText(dataSpec.valuePattern) || data.matches(dataSpec.valuePattern))
                && (!StringUtils.hasText(dataSpec.valueExcludePattern) || !data.matches(dataSpec.valueExcludePattern)))
        {
            if (StringUtils.hasText(dataSpec.substringPattern))
            {
                Pattern pattern = Pattern.compile(dataSpec.substringPattern);
                Matcher matcher = pattern.matcher(data);
                if (matcher.find())
                {
                    data = matcher.group(1);
                }
                else
                {
                    data = null;
                }
            }
        }
        else
        {
            data = null;
        }

        if (StringUtils.hasText(data))
        {
            return data.trim();
        }
        else
        {
            throw new DataNotFoundException("Data not found on " + element.baseUri() + " - " + dataSpec + " - "
                    + element);
        }
    }

    private Elements extractElements(Element rootElement, ElementExtractSpec containerSpec,
            ElementExtractSpec elementSpec) throws ElementNotFoundException
    {
        if (!isEmptySpec(containerSpec))
        {
            // get container elements and then find actual element
            Elements containerElements = extractElements(rootElement, containerSpec);
            return extractElements(containerElements, elementSpec);
        }
        else
        {
            return extractElements(rootElement, elementSpec);
        }
    }

    private Elements extractElements(Elements elements, ElementExtractSpec elementSpec) throws ElementNotFoundException
    {
        final Elements extractedElements = new Elements();
        for (Element element : elements)
        {
            extractedElements.addAll(extractElements(element, elementSpec));
        }
        if (extractedElements.size() == 0)
        {
            throw new ElementNotFoundException("Element not found on " + elements.first().baseUri() + " - "
                    + elementSpec);
        }

        return extractedElements;
    }

    /**
     * Extract elements that match with all the given element specification
     * 
     * @throws ElementNotFoundException
     */
    private Elements extractElements(Element rootElement, ElementExtractSpec elementSpec)
            throws ElementNotFoundException
    {
        Elements elements = null;

        // check with id
        if (StringUtils.hasText(elementSpec.id))
        {
            Element element = rootElement.getElementById(elementSpec.id);
            if (element != null)
            {
                elements = new Elements(element);
            }
            else
            {
                elements = new Elements();
            }
        }

        // check with tag name
        if (StringUtils.hasText(elementSpec.tagName))
        {
            if (elements != null)
            {
                elements = filterElementsByTagName(elements, elementSpec.tagName);
            }
            else
            {
                elements = rootElement.getElementsByTag(elementSpec.tagName);
            }
        }

        // check with class name
        if (StringUtils.hasText(elementSpec.className))
        {
            if (elements != null)
            {
                elements = filterElementsByClassName(elements, elementSpec.className);
            }
            else
            {
                elements = rootElement.getElementsByClass(elementSpec.className);
            }
        }

        // check with attribute
        if (StringUtils.hasText(elementSpec.attributeKey))
        {
            if (elements != null)
            {
                elements = filterElementsByAttribute(elements, elementSpec.attributeKey, elementSpec.valuePattern);
            }
            else
            {
                if (StringUtils.hasText(elementSpec.valuePattern))
                {
                    elements = rootElement.getElementsByAttributeValueMatching(elementSpec.attributeKey,
                            elementSpec.valuePattern);
                }
                else
                {
                    elements = rootElement.getElementsByAttribute(elementSpec.attributeKey);
                }
            }
        }

        if (elements == null || elements.size() == 0)
        {
            throw new ElementNotFoundException("Element not found on " + rootElement.baseUri() + " - " + elementSpec);
        }

        return elements;
    }

    private Elements filterElementsByTagName(Elements elements, String tagName)
    {
        final Elements filteredElements = new Elements();
        for (Element element : elements)
        {
            if (tagName.equals(element.tagName()))
            {
                filteredElements.add(element);
            }
        }

        return filteredElements;
    }

    private Elements filterElementsByClassName(Elements elements, String className)
    {
        final Elements filteredElements = new Elements();
        for (Element element : elements)
        {
            Set<String> classNames = element.classNames();
            if (classNames != null && classNames.contains(className))
            {
                filteredElements.add(element);
            }
        }

        return filteredElements;
    }

    private Elements filterElementsByAttribute(Elements elements, String attributeKey, String valuePattern)
    {
        final Elements filteredElements = new Elements();
        for (Element element : elements)
        {
            String value = getElementAttribute(element, attributeKey);
            if (!StringUtils.hasText(valuePattern) || (value != null && value.matches(valuePattern)))
            {
                filteredElements.add(element);
            }
        }

        return filteredElements;
    }

    private String getElementAttribute(Element element, String attributeKey)
    {
        String value;
        if (DataExtractSpec.ATTRIBUTE_KEY_TEXT.equalsIgnoreCase(attributeKey))
        {
            value = element.text();
        }
        else if (DataExtractSpec.ATTRIBUTE_KEY_OWNTEXT.equalsIgnoreCase(attributeKey))
        {
            value = element.ownText();
        }
        else
        {
            value = element.attr(attributeKey);
        }

        // replace &nbsp; with a space
        return value.replace("\u00a0", " ").trim();
    }

    /**
     * Check if this object contains any spec, i.e. non-empty condition
     */
    private boolean isEmptySpec(ElementExtractSpec elementSpec)
    {
        return (elementSpec == null)
                || (!StringUtils.hasText(elementSpec.id) && !StringUtils.hasText(elementSpec.tagName)
                        && !StringUtils.hasText(elementSpec.className)
                        && !StringUtils.hasText(elementSpec.attributeKey) && !StringUtils
                            .hasText(elementSpec.valuePattern));
    }

}
