package administrator.example.com.myapp10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import administrator.example.com.myapp10.functions.AddActivity;
import administrator.example.com.myapp10.functions.DeleteActivity;
import administrator.example.com.myapp10.functions.ModifyActivity;
import cn.bmob.v3.Bmob;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener{

    /*
    *数据成员
     */
    private Button bt_add;
    private Button bt_delete;
    private Button bt_modify;
    private Button bt_manager_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Bmob.initialize(this, "cca41faf164311879f11c2582f784b46");

        iniviews();

    }

    /*
    *绑定控件
     */
    private void iniviews(){
        bt_add = (Button)findViewById(R.id.bt_add);
        bt_delete = (Button)findViewById(R.id.bt_delete);
        bt_modify = (Button)findViewById(R.id.bt_modify);
        bt_manager_back = (Button)findViewById(R.id.bt_manager_back);

        bt_manager_back.setOnClickListener(ManagerActivity.this);
        bt_add.setOnClickListener(ManagerActivity.this);
        bt_delete.setOnClickListener(ManagerActivity.this);
        bt_modify.setOnClickListener(ManagerActivity.this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add:
                Intent intent_add = new Intent(ManagerActivity.this,AddActivity.class);
                startActivity(intent_add);
                break;
            case R.id.bt_delete:
                Intent intent_delete = new Intent(ManagerActivity.this,DeleteActivity.class);
                startActivity(intent_delete);
                break;
            case R.id.bt_modify:
                Intent intent_modify = new Intent(ManagerActivity.this,ModifyActivity.class);
                startActivity(intent_modify);
                break;
            case R.id.bt_manager_back:
                Intent intent_manager_back = new Intent(ManagerActivity.this,LoginActivity.class);
                startActivity(intent_manager_back);
                break;
            default:
                break;
        }
    }
}
