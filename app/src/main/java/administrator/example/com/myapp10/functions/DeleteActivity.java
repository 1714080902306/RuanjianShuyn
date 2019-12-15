package administrator.example.com.myapp10.functions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import administrator.example.com.myapp10.bean.DataSourse;
import administrator.example.com.myapp10.R;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_delete_china;
    private TextView tv_delete_query;
    private Button bt_delete_ok;
    private Button bt_delete_query;
    private DataSourse dataSourse;
    //private ListView listView;

    private List<BmobObject> queryBeans = new ArrayList<BmobObject>();

    private List<DataSourse> list;



    //protected MyAdapter<DataSourse> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Bmob.initialize(this, "cca41faf164311879f11c2582f784b46");

        iniviews();
    }

    private void iniviews(){
        et_delete_china = (EditText)findViewById(R.id.et_delete_china);
        tv_delete_query = (TextView)findViewById(R.id.tv_delete_query);
        bt_delete_ok = (Button)findViewById(R.id.bt_delete_ok);
        bt_delete_query = (Button)findViewById(R.id.bt_delete_query);
        //listView = (ListView)findViewById(R.id.list_item) ;

        //listView.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) DeleteActivity.this);
        bt_delete_ok.setOnClickListener(DeleteActivity.this);
        bt_delete_query.setOnClickListener(DeleteActivity.this);
    }

    /*
    *根据name(中文术语)删除一行数据
     */
    private void delete() {


        //设置ObjectId信息
        dataSourse.setObjectId(list.get(0).getObjectId());
        //执行删除方法，第一个参数为上下文，第二个参数为删除的回调

        dataSourse.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(DeleteActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DeleteActivity.this,"删除失败",Toast.LENGTH_LONG).show();
                }
            }

        });



    }

    /*
    *根据name(中文术语)查询对应的内容
     */
    private void deleteQuery(){
        String inputQuery = et_delete_china.getText().toString().trim();
        final BmobQuery<DataSourse> deleteBmobQuery = new BmobQuery<>();
        deleteBmobQuery.addWhereEqualTo("ChinaShuyu", inputQuery);
        deleteBmobQuery.findObjects(new FindListener<DataSourse>() {
            @Override
            public void done(List<DataSourse> object, BmobException e) {
                if (e == null) {
                    Toast.makeText(DeleteActivity.this,"查询成功",Toast.LENGTH_LONG).show();
                    list = object;
                    tv_delete_query.setText("中文术语："+object.get(0).getChinaShuyu()+"\n英文术语："+object.get(0).getEnglishShuyu()+"\n相应解释："+object.get(0).getExplain());
                    //et_delete_query.setText(object.get(0).getChinaShuyu());
                    //listView.setAdapter(new MyAdapter(DeleteActivity.this , object));
                } else {

                    Toast.makeText(DeleteActivity.this,"查询失败！该内容不存在",Toast.LENGTH_LONG).show();

                }
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_delete_ok:
                //deleteQuery();
                //delete();
                Toast.makeText(DeleteActivity.this,"该功能待开发中",Toast.LENGTH_LONG).show();
                break;
            case R.id.bt_delete_query:
                deleteQuery();
                //et_delete_query.setText(list.get(0).getChinaShuyu());
                break;
        }
    }
}
