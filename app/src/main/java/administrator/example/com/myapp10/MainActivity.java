package administrator.example.com.myapp10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import administrator.example.com.myapp10.functions.QuickLearningActivity;
import administrator.example.com.myapp10.functions.SelfLearningActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_self;
    private Button bt_quick;
    private Button bt_main_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniviews();
    }

    public void iniviews(){
        bt_self = (Button)findViewById(R.id.bt_self);
        bt_quick = (Button)findViewById(R.id.bt_quick);
        bt_main_back = (Button)findViewById(R.id.bt_main_back);

        bt_main_back.setOnClickListener(MainActivity.this);
        bt_self.setOnClickListener(MainActivity.this);
        bt_quick.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_self:
                Intent in_self = new Intent(MainActivity.this,SelfLearningActivity.class);
                startActivity(in_self);
                break;
            case R.id.bt_quick:
                //Intent in_quick = new Intent(MainActivity.this,QuickLearningActivity.class);
                //startActivity(in_quick);
                Toast.makeText(MainActivity.this,"该功能待开发中",Toast.LENGTH_LONG).show();
                break;
            case R.id.bt_main_back:
                Intent in_back = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(in_back);
        }
    }
}
