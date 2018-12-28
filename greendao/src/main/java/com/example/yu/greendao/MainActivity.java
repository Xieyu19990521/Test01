package com.example.yu.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yu.greendao.greendao.DaoBeanDao;
import com.example.yu.greendao.greendao.DaoMaster;
import com.example.yu.greendao.greendao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button add,update,del,select;
    private DaoBeanDao daoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.add);
        update=findViewById(R.id.update);
        del=findViewById(R.id.del);
        select=findViewById(R.id.select);

        initDbHelp();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaoBean bean = new DaoBean(Long.valueOf(1), "张三");
                daoBean.insert(bean);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaoBean bean = new DaoBean(1, "李四");
                daoBean.insertOrReplace(bean);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoBean.deleteByKey(Long.valueOf(1));
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="";
                QueryBuilder<DaoBean> builder = daoBean.queryBuilder();
                List<DaoBean> list = builder.where(DaoBeanDao.Properties.Id.eq(1)).list();
                for (DaoBean dao:list){
                    s+=dao.getId()+dao.getName();
                }
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDbHelp() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "recluse-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        daoBean = daoSession.getDaoBeanDao();
    }

}
