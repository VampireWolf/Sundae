package com.orange.gws;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TrackStatus extends AppCompatActivity {

    private EditText serviceID;
    private String enteredID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackstatus);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void checkStatus(View view) {
        serviceID = (EditText)findViewById(R.id.service_id);
        enteredID = serviceID.getText().toString();
        final TextView StatusView1 = (TextView)findViewById(R.id.statusText1);
        final TextView StatusView2 = (TextView)findViewById(R.id.statusText2);
        if(enteredID.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter Service ID", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Status");
            query.getInBackground(enteredID, new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        String deviceName = object.getString("deviceName");
                        StatusView1.setText("Device Name : "+deviceName);
                        boolean statusOfService = object.getBoolean("serviceStatus");
                        String prob = object.getString("Probability");
                        if (statusOfService == true)
                            StatusView2.setText("Service Ready");
                        else
                            StatusView2.setText("Service Pending. Will be ready within "+prob);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Service ID", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

