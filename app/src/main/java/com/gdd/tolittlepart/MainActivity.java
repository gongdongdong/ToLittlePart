package com.gdd.tolittlepart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gdd.tolittlepart.entity.NewsChannelTable;
import com.gdd.tolittlepart.entity.StudentBean;
import com.gdd.tolittlepart.greendao.NewsChannelTableDao;
import com.gdd.tolittlepart.greendao.StudentBeanDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NewsChannelTableDao dao = App.getNewsChannelTableDao();
        NewsChannelTable newsChannelTable = new NewsChannelTable();
        newsChannelTable.setNewsChannelFixed(false);
        newsChannelTable.setNewsChannelId("1update");
        newsChannelTable.setNewsChannelIndex(1);
        newsChannelTable.setNewsChannelName("gddddddtest");
        newsChannelTable.setNewsChannelSelect(false);
        newsChannelTable.setNewsChannelType("1");
//        dao.insert(newsChannelTable);
        dao.getKey(newsChannelTable);
        StudentBeanDao daostu = App.getStudentBeanTableDao();
        StudentBean studentBean = new StudentBean();
        studentBean.set_id("22");
        studentBean.setName("gdd");
        daostu.insert(studentBean);
    }
}
