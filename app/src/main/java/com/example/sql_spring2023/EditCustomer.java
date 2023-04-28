package com.example.sql_spring2023;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class EditCustomer extends Activity {

    EditText First;
    EditText Last;
    EditText Email;
    EditText Date;
    EditText Phone;
    EditText Zip;

    SQL_Database sql_database = new SQL_Database(this);


    private TextView editMessage;

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcustomer);
        editMessage = (TextView) findViewById(R.id.edit_message);
        First = (EditText) findViewById(R.id.editFirstName);
        Last = (EditText) findViewById(R.id.editLastName);
        Email = (EditText) findViewById(R.id.editEmail);
        Date = (EditText) findViewById(R.id.editDate);
        Phone = (EditText) findViewById(R.id.editPhone);
        Zip = (EditText) findViewById(R.id.editZip);

        Intent theIntent = getIntent();
        String ID = theIntent.getStringExtra("ID");
        HashMap<String,String> customerList = sql_database.getCustomerInfo(ID);
        if (customerList.size() != 0 ){
            First.setText(customerList.get("First"));
            Last.setText(customerList.get("Last"));
            Email.setText(customerList.get("Email"));
            Date.setText(customerList.get("Date"));
            Phone.setText(customerList.get("Phone"));
            Zip.setText(customerList.get("Zip"));
        }




    }

    public void editCustomer(View view){

        HashMap<String,String> queryValuesMap = new HashMap<>();

        First = (EditText) findViewById(R.id.editFirstName);
        Last = (EditText) findViewById(R.id.editLastName);
        Email = (EditText) findViewById(R.id.editEmail);
        Date = (EditText) findViewById(R.id.editDate);
        Phone = (EditText) findViewById(R.id.editPhone);
        Zip = (EditText) findViewById(R.id.editZip);

        Intent theIntent = getIntent();
        String ID = theIntent.getStringExtra("ID");
        editMessage = (TextView) findViewById(R.id.edit_message);

        if (!Utility.validString(First.getText().toString())){
            First.setText("");
            First.setError("Invalid First Name");
            editMessage.setText("");
        }

        if (!Utility.validString(Last.getText().toString())){
            Last.setText("");
            Last.setError("Invalid First Name");
            editMessage.setText("");
        }

        if (!Utility.validCred(Email.getText().toString())){
            Email.setText("");
            Email.setError("Invalid First Name");
            editMessage.setText("");
        }

        if (!Utility.isNumeric(Date.getText().toString())){
            Date.setText("");
            Date.setError("Invalid First Name");
            editMessage.setText("");
        }
        if (!Utility.isNumeric(Phone.getText().toString())){
            Phone.setText("");
            Phone.setError("Invalid First Name");
            editMessage.setText("");
        }
        if (!Utility.isNumeric(Zip.getText().toString())){
            Zip.setText("");
            Zip.setError("Invalid First Name");
            editMessage.setText("");
        }

        if (Utility.validString(First.getText().toString())&&Utility.isNumeric(Zip.getText().toString())
                &&Utility.isNumeric(Phone.getText().toString())&&Utility.isNumeric(Date.getText().toString())&&
                Utility.validString(Last.getText().toString())&&Utility.validCred(Email.getText().toString())){

            queryValuesMap.put("ID", ID);
            queryValuesMap.put("First", First.getText().toString());
            queryValuesMap.put("Last", Last.getText().toString());
            queryValuesMap.put("Email", Email.getText().toString());
            queryValuesMap.put("Date", Date.getText().toString());
            queryValuesMap.put("Phone", Phone.getText().toString());
            queryValuesMap.put("Zip", Zip.getText().toString());

            sql_database.updateCustomer(queryValuesMap);
            editMessage.setText("Customer Edited.");
        }
        hideSoftKeyboard();
    }

    public void removeCustomer(View view){
        Intent theIntent = getIntent();
        String ID = theIntent.getStringExtra("ID");
        sql_database.deleteCustomer(ID);
        editMessage.setText("Customer removed");
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
