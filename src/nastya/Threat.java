package nastya;

import java.util.ArrayList;

public class Threat {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private ArrayList<String> reactions;

    public Threat() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getReactions() {
        return reactions;
    }

    public void setReactions(ArrayList<String> reactions) {
        this.reactions = reactions;
    }
}
