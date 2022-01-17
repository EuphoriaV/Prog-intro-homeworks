package markup;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElement {
    protected List<AbstractElement> list;

    public AbstractElement(List<AbstractElement> list) {
        this.list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            this.list.add(list.get(i));
        }
    }

    public AbstractElement(String str) {
        this.list = new ArrayList<>();
    }

    public void toMarkdown(StringBuilder stringBuilder) {
        for (AbstractElement el : list) {
            el.toMarkdown(stringBuilder);
        }
    }
    public void toHtml(StringBuilder stringBuilder) {
        for (AbstractElement el : list) {
            el.toHtml(stringBuilder);
        }
    }
}
