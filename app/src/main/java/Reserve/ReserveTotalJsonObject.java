package Reserve;

import lombok.Getter;
import lombok.Setter;

public class ReserveTotalJsonObject {
    @Getter
    @Setter
    private String userId;

    @Override
    public String toString(){return "ClassPojo [ userId = "+userId+"]";}

    public ReserveTotalJsonObject(String userId){
        this.userId = userId;
    }
}
