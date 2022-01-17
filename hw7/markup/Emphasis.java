package markup;

import java.util.List;

public class Emphasis extends AbstractElement {
    public Emphasis(List<AbstractElement> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append("*");
        super.toMarkdown(stringBuilder);
        stringBuilder.append("*");
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append("<em>");
        super.toHtml(stringBuilder);
        stringBuilder.append("</em>");
    }
}
