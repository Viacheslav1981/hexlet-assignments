package exercise;

import javax.swing.text.html.parser.Entity;
import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String img, Map<String, String> attributes) {
        super(img, attributes);
    }

    @Override
    public String toString() {
        String fullSingleTag = "<" + getTagName();

        for (Map.Entry<String, String> elem : getTagAttributes().entrySet()) {
            fullSingleTag = fullSingleTag
                    +" " + elem.getKey()
                    + "=" + "\"" + elem.getValue() + "\"";
        }

        return fullSingleTag + ">";

    }
}

// END
