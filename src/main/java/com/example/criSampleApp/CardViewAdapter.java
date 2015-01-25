package com.example.criSampleApp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.view.*;
import android.view.animation.LinearInterpolator;
import android.widget.*;
import responses.HeadersTest;
import responses.IpTest;
import retrofit.AnotherRestClient;
import retrofit.Callback;
import retrofit.RestClient;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;


public class CardViewAdapter extends ArrayAdapter {
    public static final int TYPE_RED = 0;
    public static final int TYPE_ORANGE = 1;
    public static final int TYPE_YELLOW = 2;
    public static final int TYPE_GREEN = 3;
    public static final int TYPE_BLUE = 4;
    public static final int TYPE_PURPLE = 5;
    public static final int TYPE_WHITE = 6;
    public static final int TYPE_BLACK = 7;

    IpTest ipTest;
    HeadersTest headersTest;

    private ListViewItem[] objects;
    Context context;

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return objects[position].getType();
    }

    public CardViewAdapter(Context context, int resource, ListViewItem[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }


    private void valuesObtained() {
        createDialogWithRetrofitResponse();
    }

    private void createDialogWithRetrofitResponse() {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Details");
        alertDialog.setMessage(ipTest.ip + "\n" + headersTest.Host + "\n" + headersTest.UserAgent);
        alertDialog.show();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        int listViewItemType = getItemViewType(position);


        if (convertView == null) {
            convertView = displayDifferentColorCards(convertView, listViewItemType);
            final LinearLayout linearLayout = getLinearLayoutAndSetParams(convertView);
            convertView.setLongClickable(true);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new executeCalls().execute();
                }
            });
            viewHolder = new ViewHolder(linearLayout);
            convertView.setTag(String.valueOf(position));
        }

        return convertView;
    }

    private LinearLayout getLinearLayoutAndSetParams(View convertView) {
        final LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.ll);
        linearLayout.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
        return linearLayout;
    }

    private View displayDifferentColorCards(View convertView, int listViewItemType) {
        if (listViewItemType == TYPE_RED) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_red, null);
        } else if (listViewItemType == TYPE_ORANGE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_orange, null);
        } else if (listViewItemType == TYPE_YELLOW) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_yellow, null);
        } else if (listViewItemType == TYPE_GREEN) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_green, null);
        } else if (listViewItemType == TYPE_BLUE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_view_blue, null);
        } else if (listViewItemType == TYPE_PURPLE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_view_purple, null);
        } else if (listViewItemType == TYPE_WHITE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_view_white, null);
        } else if (listViewItemType == TYPE_BLACK) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_view_black, null);
        }
        return convertView;
    }

    private class executeCalls extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            ipTest = RestClient.get().getIpTestDetails();
            headersTest = AnotherRestClient.get().getHeadersTestDetails();
            return "done";
        }

        @Override
        protected void onPostExecute(String result) {
            valuesObtained();
        }

    }

    public class ViewHolder {
        LinearLayout text;
        public ViewHolder(LinearLayout text) {
            this.text = text;
        }

    }}

