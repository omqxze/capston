package QR;

import lombok.Getter;
import lombok.Setter;

public class QRJsonObject {
    @Getter
    @Setter
    private String userId;

    @Override
    public String toString() {return "ClassPojo [ userId ="+userId+"]";}

    public QRJsonObject(String userId){
        this.userId = userId;
    }
}
