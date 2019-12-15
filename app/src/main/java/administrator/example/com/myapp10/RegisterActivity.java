package administrator.example.com.myapp10;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import administrator.example.com.myapp10.bean.User;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_regist_user;
    private EditText et_regist_password;
    private Button bt_regist_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bmob.initialize(this, "cca41faf164311879f11c2582f784b46");
        iniviews();
    }

    private void iniviews(){
        et_regist_user = (EditText)findViewById(R.id.et_user);
        et_regist_password = (EditText)findViewById(R.id.et_pwd);
        bt_regist_save = (Button)findViewById(R.id.bt_register);
        bt_regist_save.setOnClickListener(this);
    }

    /**
     * 注册
     */
    private void register() {
        String loginId = et_regist_user.getText().toString();
        String password = et_regist_password.getText().toString().trim();
        if (loginId.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        BmobUser user = new BmobUser();
        //User user = new User();
        user.setUsername(loginId);
        user.setPassword(password);



        /**
         * 保存数据到Bmob服务器
         */
        user.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser user, BmobException e) {
                if(e==null)
                {
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }else
                {
                    Log.e("注册失败", "原因: ",e );
                }

            }
        });



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register:
                register();
                Intent in_regist = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(in_regist);
                break;

            default:
                break;


        }

    }
}