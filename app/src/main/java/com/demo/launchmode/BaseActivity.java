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
        title.setText(TAG+":"+getTaskId());
        Log.e("open activity", "open : " + TAG + " taskId ： " + getTaskId());
        Log.d(TAG,"生命周期：onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG,"oNewIntent， open ： "+TAG);
        setIntent(intent);
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
            +            " task name : "+runningTask.description);
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"生命周期：onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"生命周期：onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"生命周期：onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"生命周期：onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"生命周期：onDestroy");
    }
}
