package com.intershop.oms.test.businessobject;

/**
 * The sort direction the attribute should be sorted with.&lt;br&gt;&lt;br&gt;
 * &#x60;ASC&#x60; - Sort by the attribute ascending.&lt;br&gt; &#x60;DESC&#x60;
 * - Sort by the attribute descending.
 */
public enum OMSSortDirection
{
    ASC("ASC"),
    DESC("DESC");

    private String value;

    OMSSortDirection(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    public static OMSSortDirection fromValue(String text)
    {
        for (OMSSortDirection b : OMSSortDirection.values())
        {
            if (String.valueOf(b.value).equals(text))
            {
                return b;
            }
        }
        return null;
    }
}