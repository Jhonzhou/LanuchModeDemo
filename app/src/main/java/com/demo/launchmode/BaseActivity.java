package com.demo.launchmode;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        TextView title = findViewById(R.id.text_title);
        title.setText(TAG);
        Log.e("open activity", "open : " + TAG + "task Id ： " + getTaskId());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG,"on new Intent， open ： "+TAG);
    }

    private void print() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) {

            return;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(Integer.MAX_VALUE);
        if (runningTasks != null) {
            for (ActivityManager.RunningTaskInfo runningTask : runningTasks) {
                Log.e(TAG, "task Info   ~~~  numActivities : " + runningTask.numActivities+"~~~ taskId: "+runningTask.id+" ----topActivity: " + runningTask.topActivity.getShortClassName() + "~~~~~ baseActivity: " + runningTask.baseActivity.getShortClassName()
                        );
            }
        }
    }


    public void openSingleTask(View view) {
        Intent intent = new Intent(getApplicationContext(), SingleTaskActivity.class);
        startActivity(intent);
    }

    public void openTop(View view) {
        Intent intent = new Intent(getApplicationContext(), SingleTopActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void printMessage(View view) {
        print();
    }

    public void onOpenDefault(View view) {
        Intent intent = new Intent(getApplicationContext(), DefaultActivity.class);
        startActivity(intent);
    }

    public void openInstance(View view) {
        Intent intent = new Intent(getApplicationContext(), SingleInstanceActivity.class);
        startActivity(intent);
    }
}
