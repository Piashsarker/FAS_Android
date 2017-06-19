package nextinnnovationsoft.com.finalyearproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import nextinnnovationsoft.com.finalyearproject.adapter.LogAdapter;
import nextinnnovationsoft.com.finalyearproject.model.Log;

public class LogActivity extends AppCompatActivity {

    private ArrayList<Log> logList =new ArrayList();
    private LogAdapter adapter ;
    private RecyclerView recyclerView;
    private Context context = LogActivity.this;
    private DatabaseHelper databaseHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        databaseHelper = new DatabaseHelper(context);

        logList = databaseHelper.getAllLog();

        Collections.reverse(logList);

        adapter = new LogAdapter(context ,logList);
        recyclerView = (RecyclerView)findViewById(R.id.log_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}
