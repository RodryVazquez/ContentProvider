package com.example.rodrigo.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rodrigo.contentprovider.Adapter.ContactPhoneAdapter;
import com.example.rodrigo.contentprovider.Models.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {

    private ListView lstContacts;
    List<ContactModel> contactModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        contactModelList = new ArrayList<>();
        lstContacts = (ListView) findViewById(R.id.lstContacts);
        //Obtenemos el numero y tipo de llamada(Entrante,Saliente,Perdida)
        String[] projection = new String[]{CallLog.Calls.NUMBER, CallLog.Calls.TYPE, CallLog.Calls.CACHED_NAME};
        //Referencia del URI del content provider
        Uri callsUri = CallLog.Calls.CONTENT_URI;

        //Obtenemos la referncia del Content Resolver
        ContentResolver contentResolver = getContentResolver();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor cur = contentResolver.query(
                callsUri,//Uri del content provider del CallLog
                projection,//Columnas a devolver
                null,//Condicion del query
                null,//Argumentos variables del query
                null);//Orden de los resultados
        if (cur.moveToFirst()) {

            int columnType = cur.getColumnIndex(CallLog.Calls.TYPE);
            int columnPhone = cur.getColumnIndex(CallLog.Calls.NUMBER);
            int columnName = cur.getColumnIndex(CallLog.Calls.CACHED_NAME);

            do {
                ContactModel contactModel = new ContactModel();
                contactModel.setType(cur.getInt(columnType));
                contactModel.setPhone(cur.getString(columnPhone));
                contactModel.setNameContact(cur.getString(columnName));

                switch (contactModel.getType()){

                    case CallLog.Calls.INCOMING_TYPE:
                        contactModel.setTypeOfCall("Entrante");
                        break;
                    case CallLog.Calls.OUTGOING_TYPE:
                        contactModel.setTypeOfCall("Saliente");
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        contactModel.setTypeOfCall("Perdida");
                        break;
                    default:
                        break;
                }

                contactModelList.add(contactModel);

            } while (cur.moveToNext());

            ContactPhoneAdapter adapter = new ContactPhoneAdapter(ContentActivity.this,contactModelList);
            lstContacts.setAdapter(adapter);
        }
    }
}
