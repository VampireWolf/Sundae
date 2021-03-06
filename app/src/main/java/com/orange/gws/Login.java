package com.orange.gws;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (ParseUser.getCurrentUser() != null) { // start with existing user
            Intent intent = new Intent(getApplicationContext(), Chat.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        setContentView(R.layout.activity_login);

        // All the views from our login form
        final EditText firstNameView = (EditText) findViewById(R.id.firstName);
        final EditText lastNameView = (EditText) findViewById(R.id.lastName);
        final Spinner countryView = (Spinner) findViewById(R.id.country);
        final EditText countryCodeView = (EditText) findViewById(R.id.countryCode);
        final EditText phoneNumberView = (EditText) findViewById(R.id.phoneNumber);
        Button loginButtonView = (Button) findViewById(R.id.loginButton);

        // Set items for the Spinner dropdown
        ArrayList<String> countries = new ArrayList<String>();
        countries.add("Australia");
        countries.add("Brazil");
        countries.add("China");
        countries.add("Canada");
        countries.add("India");
        countries.add("Russia");
        countries.add("Singapore");
        countries.add("United States");

        // Create the adapter for the spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the adapter to the spinner
        countryView.setAdapter(adapter);


        // On login button click
        loginButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the values of all the form fields

                final String phoneNumber = phoneNumberView.getText().toString().trim();
                String firstName = firstNameView.getText().toString().trim();
                String lastName = lastNameView.getText().toString().trim();
                String countryCode = countryCodeView.getText().toString().trim();
                String country = countryView.getSelectedItem().toString().trim();

                // Simple validation: if any field is empty then don't let the form submit
                // and show an alert dialog with error message
                if (phoneNumber.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || countryCode.isEmpty() || country.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("Please make sure you entered all the fields correctly.")
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    return;
                }

                // Create a ParseUser object to create a new user
                final ParseUser user = new ParseUser();

                user.setUsername(phoneNumber);
                user.setPassword("Fake Password");
                user.put("firstName", firstName);
                user.put("lastName", lastName);
                user.put("country", country);
                user.put("countryCode", countryCode);

                // First query to check whether a ParseUser with
                // the given phone number already exists or not
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", phoneNumber);

                query.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> parseUsers, ParseException e) {

                        if (e == null) {
                            // Successful Query

                            // User already exists ? then login
                            if (parseUsers.size() > 0) {
                                loginUser(phoneNumber, "Fake Password");
                            }
                            else {
                                // No user found, so signup
                                signupUser(user);
                            }
                        }
                        else {
                            // Shit happened!
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setMessage(e.getMessage())
                                    .setTitle("Oops!")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }
        });
    }

    private void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Intent intent = new Intent(getApplicationContext(), Chat.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    // Login failed!
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage(e.getMessage())
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private void signupUser(ParseUser user) {
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Signup successful!

                } else {
                    // Fail!
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage(e.getMessage())
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private void navigateToHome() {
        // Let's go to the MainActivity
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
