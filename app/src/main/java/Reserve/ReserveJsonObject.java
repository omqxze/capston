package Reserve;

import lombok.Getter;
import lombok.Setter;

public class ReserveJsonObject {
    @Getter
    @Setter
    private String confCode;

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String rsvDateTime;

    @Override
    public String toString() {return "ClassPojo [ confCode="+confCode+", userId="+userId+", rsvDateTime="+rsvDateTime+"]";}

    public ReserveJsonObject(String confCode, String userId, String rsvDateTime){
        this.confCode = confCode;
        this.userId = userId;
        this.rsvDateTime = rsvDateTime;
    }
}
