package administrator.example.com.myapp10.functions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import administrator.example.com.myapp10.bean.DataSourse;
import administrator.example.com.myapp10.MainActivity;
import administrator.example.com.myapp10.R;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SelfLearningActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_self_china;
    private TextView tv_self_query;
    private Button bt_self_back;
    private Button bt_self_query;
    private DataSourse dataSourse;
    private List<DataSourse> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_learning);

        Bmob.initialize(this, "cca41faf164311879f11c2582f784b46");

        iniviews();
    }

    private void iniviews(){
        et_self_china = (EditText)findViewById(R.id.et_self_china);
        tv_self_query = (TextView)findViewById(R.id.tv_self_query);
        bt_self_back = (Button)findViewById(R.id.bt_self_back);
        bt_self_query = (Button)findViewById(R.id.bt_self_query);

        bt_self_back.setOnClickListener(SelfLearningActivity.this);
        bt_self_query.setOnClickListener(SelfLearningActivity.this);
    }

    /*
     *根据name(中文术语)查询对应的内容
     */
    private void selfQuery(){
        String inputQuery = et_self_china.getText().toString().trim();
        final BmobQuery<DataSourse> deleteBmobQuery = new BmobQuery<>();
        deleteBmobQuery.addWhereEqualTo("ChinaShuyu", inputQuery);
        deleteBmobQuery.findObjects(new FindListener<DataSourse>() {
            @Override
            public void done(List<DataSourse> object, BmobException e) {
                if (e == null) {
                    Toast.makeText(SelfLearningActivity.this,"查询成功",Toast.LENGTH_LONG).show();
                    list = object;
                    tv_self_query.setText("中文术语："+object.get(0).getChinaShuyu()+"\n英文术语："+object.get(0).getEnglishShuyu()+"\n相应解释："+object.get(0).getExplain());
                } else {

                    Toast.makeText(SelfLearningActivity.this,"查询失败！该内容不存在",Toast.LENGTH_LONG).show();

                }
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_self_query:
                selfQuery();
                break;
            case R.id.bt_self_back:
                Intent in_self_back = new Intent(SelfLearningActivity.this, MainActivity.class);
                startActivity(in_self_back);
                break;
            default:
                break;
        }
    }
}
