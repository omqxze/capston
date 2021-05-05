package Login;

import lombok.Getter;
import lombok.Setter;

public class SearchIdJsonObject {
    @Getter @Setter
    public String userStunum;
    @Override
    public String toString(){
        return "ClassPojo [userStunum = "+userStunum+"]";
    }

    public SearchIdJsonObject(String userStunum){
        this.userStunum = userStunum;
    }
}