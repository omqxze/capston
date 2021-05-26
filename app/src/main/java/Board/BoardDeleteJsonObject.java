package Board;

import lombok.Getter;
import lombok.Setter;

public class BoardDeleteJsonObject {
    @Getter
    @Setter
    private String confCode;

    @Override
    public String toString(){return "ClassPojo [ confCode="+confCode+"]";}

    public BoardDeleteJsonObject(String confCode){
        this.confCode = confCode;
    }
}