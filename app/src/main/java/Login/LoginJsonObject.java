package Login;

import lombok.Getter;
import lombok.Setter;

public class LoginJsonObject {
    public LoginJsonObject(String userId, String userPass){
        this.userId = userId;
        this.userPass = userPass;

    }
    @Getter @Setter
    public String userId;
    public String userPass;
    @Override
    public String toString(){
        return "ClassPojo [userId ="+userId+", userPass = "+userPass+"]";
    }
}