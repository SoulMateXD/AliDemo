package com.soulmatexd.alidemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.android.vlayout.VirtualLayoutManager;

public class MainActivity extends ListActivity {

    private String one="asasaas";

    private String[] mTitles = new String[]{
            VLayoutActivity.class.getSimpleName(),
            TangramActivity.class.getSimpleName(),
            MemoryLeakActivity1.class.getSimpleName(),
    };

    private Class[] mActivities = new Class[]{
            VLayoutActivity.class,
            TangramActivity.class,
            MemoryLeakActivity1.class,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mTitles));
        InnerClass innerClass = new InnerClass();
        innerClass.printOuterClassPrivateFields();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(this, mActivities[position]));
    }

    private void useOuterPrivateMethod(){
        Log.d("MainActivity", "use outer private method");
    }

    public class InnerClass
    {
        public InnerClass() {}

        public void printOuterClassPrivateFields()
        {
            String field = "one=" + MainActivity.this.one;
            Log.d("MainActivity", "use outer attribute field = " + field);
            MainActivity.this.useOuterPrivateMethod();
        }
    }
}
