package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String textParagraph;
    private List<Tag> tags;


    public PairedTag(String p, Map<String, String> attributes, String textParagraph, List<Tag> tags) {
        super(p, attributes);

        this.textParagraph = textParagraph;
        this.tags = tags;

    }

    @Override
    public String toString() {
        String fullPairedTag = "<" + getTagName();

        for (Map.Entry<String, String> elem : getTagAttributes().entrySet()) {
            fullPairedTag = fullPairedTag
                    + " " + elem.getKey()
                    + "=" + "\"" + elem.getValue() + "\"";
        }
        fullPairedTag = fullPairedTag + ">";


        if (tags.size() > 0) {
           // fullPairedTag = fullPairedTag + ">";
            for (Tag tag : tags) {
                fullPairedTag = fullPairedTag
                        + tag;
            }
        }

        return fullPairedTag
                // + ">" + textParagraph
                + textParagraph
                + "</" + getTagName() + ">";
    }
}

// END
