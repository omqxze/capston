package MyPage;

import lombok.Getter;
import lombok.Setter;

public class MyPageJsonObject {
    @Getter
    @Setter
    private String userId;

    @Override
    public String toString(){return "ClassPojo [userId="+userId+"]";}

    public MyPageJsonObject(String userId){
        this.userId = userId;
    }
}
