package com.orange.gws;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private GridViewImageAdapter gridViewImageAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView companyName = (TextView) findViewById(R.id.aboutUs);
        companyName.setTypeface(EasyFonts.walkwayBlack(this));

        gridView = (GridView) findViewById(R.id.gridview);
        gridViewImageAdapter = new GridViewImageAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridViewImageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Intent myIntent = null;
                if (position == 0) {
                    myIntent = new Intent(v.getContext(), TrackStatus.class);
                    startActivity(myIntent);
                }
                if (position == 1) {
                    myIntent = new Intent(v.getContext(), Services.class);
                    startActivity(myIntent);
                }
                if (position == 2) {
                    myIntent = new Intent(v.getContext(), Login.class);
                    startActivity(myIntent);
                }
                if (position == 3) {
                    makeCall();
                }
                if (position == 4) {
                    sendEmail();
                }
                if (position == 5) {
                    myIntent = new Intent(v.getContext(), SMS.class);
                    startActivity(myIntent);
                }
            }
        });
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"senthil_ssv@rediffmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void makeCall() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Senthil Kumaran 9884776921");

        alertDialogBuilder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:9884776921"));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void aboutUs(View view) {
        startActivity(new Intent(view.getContext(), AboutUs.class));
    }

    private ArrayList<GridViewImageItem> getData () {
        final ArrayList<GridViewImageItem> gridViewImageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            if(i == 0) gridViewImageItems.add(new GridViewImageItem(bitmap, "Track Status"));
            if(i == 1) gridViewImageItems.add(new GridViewImageItem(bitmap, "Services"));
            if(i == 2) gridViewImageItems.add(new GridViewImageItem(bitmap, "Join the Forum"));
            if(i == 3) gridViewImageItems.add(new GridViewImageItem(bitmap, "Call us"));
            if(i == 4) gridViewImageItems.add(new GridViewImageItem(bitmap, "Mail us"));
            if(i == 5) gridViewImageItems.add(new GridViewImageItem(bitmap, "Message us"));
        }
        return gridViewImageItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

