package com.example.sql_spring2023;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerList extends ListActivity {

    TextView custNum;

    SQL_Database sqlDB = new SQL_Database(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerlist);
        ArrayList<HashMap<String,String>> custList = sqlDB.getAllCustomers();

        custNum = (TextView)  findViewById(R.id.numCust);
        custNum.setText(String.valueOf(sqlDB.count));
        if(custList.size()!=0){
            ListView listView = getListView();
            listView.setOnItemClickListener(new OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3){

                    HashMap myMap  = (HashMap) arg0.getItemAtPosition(arg2);
                    String custIDValue = String.valueOf(myMap.get("ID"));
                    view.setSelected(true);
                    Intent theIntent = new Intent(getApplication(),EditCustomer.class);
                    theIntent.putExtra("ID", custIDValue);
                    startActivity(theIntent);

                }


            });

            ListAdapter adapter = new SimpleAdapter(CustomerList.this, custList, R.layout.customerlistentry,
                    new String[] {"ID", "First", "Last"}, new int[] {R.id.custIDEntry, R.id.firstNameEntry, R.id.lastNameEntry});
setListAdapter(adapter);
        }
    }
}
