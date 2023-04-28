package com.example.sql_spring2023;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class AddCustomer extends Activity {
    EditText First;
    EditText Last;
    EditText Email;
    EditText Date;
    EditText Phone;
    EditText Zip;

    SQL_Database sql_database = new SQL_Database(this);

    private TextView addMessage;

    Boolean added_Job = false;

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcustomer);

        First = (EditText) findViewById(R.id.addFirstName);
        Last = (EditText) findViewById(R.id.addLastName);
        Email = (EditText) findViewById(R.id.addEmail);
        Date = (EditText) findViewById(R.id.addDate);
        Phone = (EditText) findViewById(R.id.addPhone);
        Zip = (EditText) findViewById(R.id.addZip);
    }

    public void addCustomer(View view){

        HashMap<String,String> queryValuesMap = new HashMap<>();

        addMessage = (TextView) findViewById(R.id.add_message);

        if (!Utility.validString(First.getText().toString())){
            First.setText("");
            First.setError("Invalid First Name");
            addMessage.setText("");
        }

        if (!Utility.validString(Last.getText().toString())){
            Last.setText("");
            Last.setError("Invalid First Name");
            addMessage.setText("");
        }

        if (!Utility.validCred(Email.getText().toString())){
            Email.setText("");
            Email.setError("Invalid First Name");
            addMessage.setText("");
        }

        if (!Utility.isNumeric(Date.getText().toString())){
            Date.setText("");
            Date.setError("Invalid First Name");
            addMessage.setText("");
        }
        if (!Utility.isNumeric(Phone.getText().toString())){
            Phone.setText("");
            Phone.setError("Invalid First Name");
            addMessage.setText("");
        }
        if (!Utility.isNumeric(Zip.getText().toString())){
            Zip.setText("");
            Zip.setError("Invalid First Name");
            addMessage.setText("");
        }

        if (Utility.validString(First.getText().toString())&&Utility.isNumeric(Zip.getText().toString())
                &&Utility.isNumeric(Phone.getText().toString())&&Utility.isNumeric(Date.getText().toString())&&
                Utility.validString(Last.getText().toString())&&Utility.validCred(Email.getText().toString())){

            queryValuesMap.put("First", First.getText().toString());
            queryValuesMap.put("Last", Last.getText().toString());
            queryValuesMap.put("Email", Email.getText().toString());
            queryValuesMap.put("Date", Date.getText().toString());
            queryValuesMap.put("Phone", Phone.getText().toString());
            queryValuesMap.put("Zip", Zip.getText().toString());

            sql_database.insertCustomer(queryValuesMap);
            addMessage.setText("Customer Added.");
            added_Job = true;
        }

        hideSoftKeyboard();
    }

   public  void hideSoftKeyboard(){
        View view = this.getCurrentFocus();
        if (view!=null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
   }

   public void showExitToCustomerList(View view){

        Intent theIntent = new Intent(getApplication(),CustomerList.class);
        startActivity(theIntent);
   }
}
