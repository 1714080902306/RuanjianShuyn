package administrator.example.com.myapp10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import administrator.example.com.myapp10.bean.User;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    /*
    数据成员
     */
    private Button bt_login;
    private EditText et_login_user;
    private EditText et_login_password;
    private TextView tv_regist;
    private CheckBox checkBox;
    private Boolean isClick = false; //登录按钮是否已经点击
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    //private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bmob.initialize(this,"cca41faf164311879f11c2582f784b46");

        iniviews();

    }



    private void iniviews(){
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        et_login_user = (EditText)findViewById(R.id.et_login_user);
        et_login_password = (EditText)findViewById(R.id.et_login_password);
        bt_login = (Button)findViewById(R.id.bt_login);
        tv_regist = (TextView)findViewById(R.id.tv_regist);
        checkBox = (CheckBox)findViewById(R.id.remember_button);
        boolean isRemenber = pref.getBoolean("remember_password", false);
        tv_regist.setOnClickListener(LoginActivity.this);
        bt_login.setOnClickListener(LoginActivity.this);

        if (isRemenber) {
            //将账号和密码都设置到文本中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            et_login_user.setText(account);
            et_login_password.setText(password);
            checkBox.setChecked(true);

        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_regist:
                Intent intent_regist = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent_regist);
                break;
            case R.id.bt_login:
                if (!isClick){//避免重复点击
                    logIn();
                }
                break;

            default:
                break;
        }
    }

    /*
    检查信息是否完整并登录
     */

    private void logIn(){
        final String user_name = et_login_user.getText().toString().trim();
        final String user_password = et_login_password.getText().toString().trim();
        editor =pref.edit();

        // 非空验证
        if (user_name.isEmpty() || user_password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checkBox.isChecked()){
            editor.putBoolean("remember_password",true);
            editor.putString("account",user_name);
            editor.putString("password",user_password);
        }else{
            editor.clear();
        }
        editor.apply();
        final BmobUser user = new User();
        //User user = new User();
        user.setUsername(user_name);
        user.setPassword(user_password);
        user.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser user, BmobException e) {
                if (e == null&&user_name.equals("admin")&&user_password.equals("admin")) {
                    Toast.makeText(LoginActivity.this, "管理员登陆成功", Toast.LENGTH_SHORT).show();
                    Intent in_success = new Intent(LoginActivity.this, ManagerActivity.class);
                    startActivity(in_success);

                }
                else if (e == null){
                    Toast.makeText(LoginActivity.this, "用户登录成功", Toast.LENGTH_SHORT).show();
                    Intent in_success = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(in_success);
                }else{
                    Toast.makeText(LoginActivity.this, "账户名或密码不正确", Toast.LENGTH_SHORT).show();
                    //loge(e);
                }
            }
        });
    }
}

