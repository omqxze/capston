package Join;

import android.text.Editable;

import com.example.capston.databinding.ActivityJoinBinding;

import Util.BASE64;
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
    private String userName;
    @Getter
    @Setter
    private String userStat;
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
    private String userLicence;
    @Getter
    @Setter
    private String userDate;
    @Getter
    @Setter
    private String userEmailHash;
    @Getter
    @Setter
    private String userEmailCheck;
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
    private String carLicence;
    @Getter
    @Setter
    private String carInsurance;

    public JoinJsonObject(ActivityJoinBinding user) {
        setUserId(user.userId.getText().toString());
        setUserPass(new BASE64().encode(user.userPass.getText().toString()));
        setUserName(user.userName.getText().toString());
        if(user.carNum==null)
            setUserStat("1");
        else setUserStat("2");
        setUserEmail(user.userEmail.getText().toString());
        setUserBirth(user.birthYear.getSelectedItem().toString()+"-"+user.birthMonth.getSelectedItem().toString()+"-"+user.birthDay.getSelectedItem().toString());
        setUserMajor(user.userMajor.getText().toString());
        setUserStunum(user.userstunum.getText().toString());
        setUserLicence(user.userLicense.getText().toString());
        setUserDate(null);
        setUserEmailHash(null);
        setUserEmailCheck(null);
        setUserQR(null);
        setCarNum(user.carNum.getText().toString());
        setCarType(user.carType.getText().toString());
        setCarLicence(null);
        setCarInsurance(null);
    }

    @Override
    public String toString() {
        return "ClassPojo [userId =" + userId + ", userPass = " + userPass + ", userEmail = " + userEmail + ", userBirth = " + userBirth + " ,userMajor = " + userMajor + ",userStunum=" + userStunum + ",userLicence=" + userLicence + ",userDate=" + userDate + ",userName=" + userName + ",userQR=" + userQR + ",carNum=" + carNum + ",carType=" + carType + ",carLicence=" + carLicence + ",carInsurance=" + carInsurance + "]";
    }


}
