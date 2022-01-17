package markup;

import java.util.List;

public class Strong extends AbstractElement {
    public Strong(List<AbstractElement> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append("__");
        super.toMarkdown(stringBuilder);
        stringBuilder.append("__");
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append("<strong>");
        super.toHtml(stringBuilder);
        stringBuilder.append("</strong>");
    }
}
