package administrator.example.com.myapp10.functions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;
import administrator.example.com.myapp10.bean.DataSourse;
import administrator.example.com.myapp10.R;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;


public class ModifyActivity extends AppCompatActivity implements View.OnClickListener{

    /*
    *数据成员
     */
    private EditText et_modify_china;
    private EditText et_modify_english;
    private EditText et_modify_explain;
    private Button bt_modify_ok;
    private Button bt_modify_query;
    //private DataSourse dataSourse;
    //private ListView listView;

    private List<DataSourse> list;



    //protected MyAdapter<DataSourse> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Bmob.initialize(this, "cca41faf164311879f11c2582f784b46");

        iniviews();
    }

    private void iniviews(){
        et_modify_china = (EditText)findViewById(R.id.et_modify_china);
        et_modify_english = (EditText)findViewById(R.id.et_modify_english);
        et_modify_explain = (EditText)findViewById(R.id.et_modify_explain);
        bt_modify_ok = (Button)findViewById(R.id.bt_modify_ok);
        bt_modify_query = (Button)findViewById(R.id.bt_modify_query);
        //listView = (ListView)findViewById(R.id.list_item) ;

        //listView.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) DeleteActivity.this);
        bt_modify_ok.setOnClickListener(ModifyActivity.this);
        bt_modify_query.setOnClickListener(ModifyActivity.this);
    }

    /*
     *修改一行数据
     */
    private void modify() {

        DataSourse dataSourse = new DataSourse();
        //modifyQuery();
        String modifyChina = et_modify_china.getText().toString().trim();
        String modifyEnglish = et_modify_english.getText().toString().trim();
        String modifyExplain = et_modify_explain.getText().toString().trim();
        String modifyObjectId = list.get(0).getObjectId();

        //dataSourse.setChinaShuyu(modifyChina);
        //dataSourse.setEnglishShuyu(modifyEnglish);
        //dataSourse.setExplain(modifyExplain);
        dataSourse.setValue("ChinaShuyu",modifyChina);
        dataSourse.setValue("EnglishShuyu",modifyEnglish);
        dataSourse.setValue("Explain",modifyExplain);

        dataSourse.update(modifyObjectId, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    //toast("更新成功:"+dataSourse.getUpdatedAt());
                    Toast.makeText(ModifyActivity.this,"修改成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ModifyActivity.this,"修改失败",Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    /*
     *查询一行数据
     */
    private void Query(){
        String inputQuery = et_modify_china.getText().toString().trim();
        BmobQuery<DataSourse> deleteBmobQuery = new BmobQuery<>();
        deleteBmobQuery.addWhereEqualTo("ChinaShuyu", inputQuery);
        deleteBmobQuery.findObjects(new FindListener<DataSourse>() {
            @Override
            public void done(List<DataSourse> object, BmobException e) {
                if (e == null) {
                    Toast.makeText(ModifyActivity.this,"查询成功",Toast.LENGTH_LONG).show();
                    list = object;
                    et_modify_english.setText(list.get(0).getEnglishShuyu());
                    et_modify_explain.setText(list.get(0).getExplain());
                } else {
                    Toast.makeText(ModifyActivity.this,"查询失败！该内容不存在",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void modifyQuery(){
        String inputQuery = et_modify_china.getText().toString().trim();
        BmobQuery<DataSourse> deleteBmobQuery = new BmobQuery<>();
        deleteBmobQuery.addWhereEqualTo("ChinaShuyu", inputQuery);
        deleteBmobQuery.findObjects(new FindListener<DataSourse>() {
            @Override
            public void done(List<DataSourse> object, BmobException e) {
                if (e == null) {
                    list = object;
                }
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_modify_ok:
                Toast.makeText(ModifyActivity.this,"该功能待开发中",Toast.LENGTH_LONG).show();
                //modify();
                break;
            case R.id.bt_modify_query:
                Query();
                //et_delete_query.setText(list.get(0).getChinaShuyu());
                break;
        }
    }
}
