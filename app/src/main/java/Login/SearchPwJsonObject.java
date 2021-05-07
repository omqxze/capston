package Login;

import lombok.Getter;
import lombok.Setter;

public class SearchPwJsonObject {
    @Getter
    @Setter
    public String userStunum;
    @Getter @Setter
    public String userId;
    @Getter @Setter
    public String userName;
    @Override
    public String toString(){
        return "ClassPojo [userStunum = "+userStunum+", userId = "+userId+", userName = "+userName+"]";
    }
    public SearchPwJsonObject(String userStunum, String userId, String userName){
        this.userStunum = userStunum;
        this.userId = userId;
        this.userName = userName;
    }
}