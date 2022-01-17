package markup;

public class Text extends AbstractElement {
    String str;
    public Text(String str) {
        super(str);
        this.str = str;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        super.toMarkdown(stringBuilder);
        stringBuilder.append(str);
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        super.toHtml(stringBuilder);
        stringBuilder.append(str);
    }
}
