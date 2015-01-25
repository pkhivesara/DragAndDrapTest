package com.example.criSampleApp;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.*;
import responses.IpTest;
import retrofit.Callback;
import retrofit.RestClient;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.List;

import static com.example.criSampleApp.CardViewAdapter.*;

public class HelloAndroidActivity extends Activity{

    ArrayAdapter arrayAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        GridView gridview = (GridView) findViewById(R.id.gridView1);
        ListViewItem[] items = new ListViewItem[8];
        saveCardsBasedOnPosition(gridview, items);
        setOnLongClickListenerOnGridView(gridview);

    }

    private void setOnLongClickListenerOnGridView(GridView gridview) {
        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData.Item item = new ClipData.Item((String) view.getTag());
                ClipData clipData = new ClipData((CharSequence) view.getTag(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);
                view.startDrag(clipData, new View.DragShadowBuilder(view), null, 0);
                onDragListener(view);
                return true;
            }
        });
    }

    private void onDragListener(View view) {
        view.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent event) {
               switch (event.getAction()){
                   case DragEvent.ACTION_DRAG_STARTED:
                       if (view.getId() == R.id.ll) {
                           view.animate().scaleX(1.0f);
                           view.animate().scaleY(1.0f);
                           return true;
                       }
                   case DragEvent.ACTION_DRAG_ENTERED:

                       if (view.getId() == R.id.ll) {
                           view.animate().scaleX(1.5f);
                           view.animate().scaleY(1.5f);
                       }
                       return true;
                   case DragEvent.ACTION_DRAG_EXITED:
                       if (view.getId() == R.id.ll) {
                           view.animate().scaleX(1.0f);
                           view.animate().scaleY(1.0f);
                       }
                       view.invalidate();
                       return true;
                   case DragEvent.ACTION_DRAG_LOCATION:
                       return true;
                   case DragEvent.ACTION_DROP:
                       arrayAdapter.notifyDataSetChanged();
                   case DragEvent.ACTION_DRAG_ENDED:
                       view.setOnDragListener(null);
                       return true;

               }

                return true;
            }

        });
    }

    private void saveCardsBasedOnPosition(GridView gridview, ListViewItem[] items) {
        for (int i = 0; i < items.length; i++) {
            if (i == 0) {
                items[i] = new ListViewItem(TYPE_RED);
            } else if (i == 1) {
                items[i] = new ListViewItem(TYPE_ORANGE);
            } else if (i == 2) {
                items[i] = new ListViewItem(TYPE_YELLOW);
            } else if (i == 3) {
                items[i] = new ListViewItem(TYPE_GREEN);
            } else if (i == 4) {
                items[i] = new ListViewItem(TYPE_BLUE);
            } else if (i == 5) {
                items[i] = new ListViewItem(TYPE_PURPLE);
            } else if (i == 6) {
                items[i] = new ListViewItem(TYPE_WHITE);
            } else if (i == 7) {
                items[i] = new ListViewItem(TYPE_BLACK);
            }

            gridview.setAdapter(arrayAdapter=new CardViewAdapter(this, R.id.ll, items));

        }
    }




}
