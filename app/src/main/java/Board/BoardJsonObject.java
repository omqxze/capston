package Board;

import lombok.Getter;
import lombok.Setter;

public class BoardJsonObject {
    @Getter
    @Setter
    private String key;

    @Override
    public String toString(){return "ClassPojo [ key = "+key+"]";}

    public BoardJsonObject(String key){
        this.key = key;
    }
}