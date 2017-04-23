package gr.codingschool.iwg.model;

/**
 * Created by christos_georgiadis on 24/04/2017.
 */
public class SortedOption {
    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected(String lastSelected) {
        return lastSelected != null && lastSelected.equals(this.value);
    }
}
