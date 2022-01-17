package markup;

import java.util.List;

public class Strikeout extends AbstractElement {
    public Strikeout(List<AbstractElement> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append("~");
        super.toMarkdown(stringBuilder);
        stringBuilder.append("~");
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append("<s>");
        super.toHtml(stringBuilder);
        stringBuilder.append("</s>");
    }
}
