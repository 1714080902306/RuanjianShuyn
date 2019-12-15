package administrator.example.com.myapp10.bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String userid;
    private String userpassword;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
