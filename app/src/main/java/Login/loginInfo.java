package Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class loginInfo {

   /* public final int result;
    public loginInfo(int result){
        this.result=result;
    }
    */

    @Expose
    @SerializedName("result") private  int result;

    public int getResult(){return result;}
    public void setResult(int result){this.result=result;}
}
