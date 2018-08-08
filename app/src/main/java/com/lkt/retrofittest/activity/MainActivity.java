package com.lkt.retrofittest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lkt.retrofittest.R;
import com.lkt.retrofittest.bean.POWeather;
import com.lkt.retrofittest.server.WeatherServer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        listView.setAdapter(new MyAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, ActivityVolleyTest.class));
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            WeatherServer.getBook(MainActivity.this, new WeatherServer.IGetBookSre() {
                @Override
                public void onSuccess(POWeather poWeather) {
                    Toast.makeText(MainActivity.this, ((POWeather) poWeather).getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFaile(String faileRes) {
                }
            });

        }
    }
}

class MyAdapter extends BaseAdapter {


    private List<String> list;

    Context context;

    public MyAdapter(Context context) {
        this.context = context;
        addData();
    }

    private List<String> addData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("di" + i + "条数据");
        }
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = View.inflate(context, R.layout.item_view, null);
            holder.textView = view.findViewById(R.id.textView);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.textView.setText(list.get(i));
        return view;
    }
}

class Holder {
    TextView textView;
}
