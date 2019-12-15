package administrator.example.com.myapp10.functions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import administrator.example.com.myapp10.bean.DataSourse;
import administrator.example.com.myapp10.ManagerActivity;
import administrator.example.com.myapp10.R;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_add_china;
    private EditText et_add_english;
    private EditText et_add_explain;
    private Button bt_add_query;
    private Button bt_add_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bmob.initialize(this, "cca41faf164311879f11c2582f784b46");

        iniviews();

    }

    private void iniviews(){
        et_add_china = (EditText)findViewById(R.id.et_add_china);
        et_add_english = (EditText)findViewById(R.id.et_add_english);
        et_add_explain =(EditText)findViewById(R.id.et_add_explain);
        bt_add_ok = (Button)findViewById(R.id.bt_add_ok);
        bt_add_query = (Button)findViewById(R.id.bt_add_query);

        bt_add_ok.setOnClickListener(AddActivity.this);
        bt_add_query.setOnClickListener(AddActivity.this);
    }

    private void add(){
        String chinaShuyu = et_add_china.getText().toString().trim();
        String englishShuyu = et_add_english.getText().toString().trim();
        String explain = et_add_explain.getText().toString().trim();

        if (chinaShuyu.isEmpty()||englishShuyu.isEmpty()||explain.isEmpty()){
            Toast.makeText(AddActivity.this,"添加失败！信息不完整",Toast.LENGTH_LONG).show();
            return;
        }

        DataSourse dataSourse = new DataSourse();
        dataSourse.setChinaShuyu(chinaShuyu);
        dataSourse.setEnglishShuyu(englishShuyu);
        dataSourse.setExplain(explain);

        dataSourse.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(AddActivity.this,"添加失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*
     *查询一行数据
     */
    private void addQuery(){
        String inputQuery = et_add_china.getText().toString().trim();
        BmobQuery<DataSourse> deleteBmobQuery = new BmobQuery<>();
        deleteBmobQuery.addWhereEqualTo("ChinaShuyu", inputQuery);
        deleteBmobQuery.findObjects(new FindListener<DataSourse>() {
            @Override
            public void done(List<DataSourse> object, BmobException e) {
                if (e == null) {
                    Toast.makeText(AddActivity.this,"查询成功",Toast.LENGTH_LONG).show();
                    et_add_english.setText(object.get(0).getEnglishShuyu());
                    et_add_explain.setText(object.get(0).getExplain());
                } else {
                    Toast.makeText(AddActivity.this,"查询失败！该内容不存在",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add_ok:
                add();
                break;
            case R.id.bt_add_query:
                addQuery();
                break;
            default:
                break;
        }
    }
}
