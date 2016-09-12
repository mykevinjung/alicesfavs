package com.alicesfavs.datamodel.html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjung on 9/10/16.
 */
public class Table
{
    private List<Cell[]> table = new ArrayList<>();

    public void addRow(Cell... cells)
    {
        table.add(cells);
    }

    public String getHtml()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("<table>");
        appendRow(builder, table.get(0), true);
        for (int index = 1; index < table.size(); index++)
        {
            appendRow(builder, table.get(index), false);
        }
        builder.append("</table>");

        return builder.toString();
    }

    private void appendRow(StringBuilder builder, Cell[] cells, boolean header)
    {
        builder.append("<tr>");
        for (Cell cell : cells)
        {
            appendCell(builder, header ? "th" : "td", cell);
        }
        builder.append("</tr>");
    }

    private void appendCell(StringBuilder builder, String tag, Cell cell)
    {
        builder.append("<").append(tag).append(" style=\"text-align: ").append(cell.alignment.toString()).append("\">")
            .append(cell.value).append("</").append(tag).append(">");
    }
}
