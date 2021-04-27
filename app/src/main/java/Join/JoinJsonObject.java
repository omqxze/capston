package Join;

import lombok.Getter;
import lombok.Setter;

public class JoinJsonObject {
    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private String userPass;
    @Getter
    @Setter
    private String userEmail;
    @Getter
    @Setter
    private String userBirth;
    @Getter
    @Setter
    private String userMajor;
    @Getter
    @Setter
    private String userStunum;
    @Getter
    @Setter
    private String userLisence;
    @Getter
    @Setter
    private String userDate;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String userQR;
    @Getter
    @Setter
    private String carNum;
    @Getter
    @Setter
    private String carType;
    @Getter
    @Setter
    private String carLicense;
    @Getter
    @Setter
    private String carInsurance;

    public JoinJsonObject JoinJsonObject( ){
        return this;
    }
}
