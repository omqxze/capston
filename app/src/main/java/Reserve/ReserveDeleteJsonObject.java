package Reserve;

import lombok.Getter;
import lombok.Setter;

public class ReserveDeleteJsonObject {
    @Getter
    @Setter
    private String confCode;

    @Getter
    @Setter
    private String userId;

    @Override
    public String toString() {return "ClassPojo [ confCode ="+confCode+", userId="+userId+"]";}

    public ReserveDeleteJsonObject(String confCode, String userId){
        this.confCode = confCode;
        this.userId = userId;
    }
}
