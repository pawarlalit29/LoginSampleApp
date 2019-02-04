package com.lalitp.myapplication.UserInterface.FailureLogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.lalitp.myapplication.LoginSampleApp;
import com.lalitp.myapplication.Pojo.LogsResult;
import com.lalitp.myapplication.R;
import com.lalitp.myapplication.UserInterface.Adaptor.LogsCursorAdapter;
import com.lalitp.myapplication.Utils.SecurePreferences;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FailureLogsActivity extends AppCompatActivity {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    private LogsCursorAdapter logsCursorAdapter;
    private Dao<LogsResult, Integer> logsResultIntegerDao;
    private SecurePreferences securePreferences;
    private List<LogsResult> logsResultList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_logs);
        context = this;
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recycleview.setLayoutManager(layoutManager);

        logsResultList = new ArrayList<>();
        logsCursorAdapter = new LogsCursorAdapter(logsResultList);
        recycleview.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.setAdapter(logsCursorAdapter);
        getNotificationList();
    }

    private void getNotificationList() {

        try {
            logsResultIntegerDao = LoginSampleApp.getHelper().getStoreLogsDao();
            // build your query
            QueryBuilder<LogsResult, Integer> queryBuilder = logsResultIntegerDao.queryBuilder();
            // when you are done, prepare your query and build an iterator
            List<LogsResult> notificationResults = queryBuilder.orderBy(LogsResult.COL_TIMESTAMP, false).query();

            if (!notificationResults.isEmpty()) {
                logsResultList.addAll(notificationResults);
                logsCursorAdapter.notifyDataSetChanged();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
