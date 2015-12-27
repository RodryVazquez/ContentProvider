package com.example.rodrigo.contentprovider.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rodrigo.contentprovider.Models.ContactModel;
import com.example.rodrigo.contentprovider.R;

import java.util.List;

public class ContactPhoneAdapter extends ArrayAdapter<ContactModel> {

    private List<ContactModel> contactModelList;
    private Activity activityContext;

    //
    public ContactPhoneAdapter(Activity context, List<ContactModel> objects) {
        super(context, R.layout.contacts_adapter, objects);
        activityContext = context;
        this.contactModelList = objects;
    }

    //Guarda una refeferencia de los controles del layout
    static class ViewHolder {
        TextView txtPhone;
        TextView txtName;
    }

    //Se llama por cada elemento de la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Objeto tipo contactModel
        ContactModel contactModel = contactModelList.get(position);

        View item = convertView;
        ViewHolder holder;
        //Si el elemento es cargado por primera vez en la vista, si no lo es obtenemos su referecia por su Tag
        if (item == null) {
            LayoutInflater inflater = activityContext.getLayoutInflater();
            item = inflater.inflate(R.layout.contacts_adapter, null);

            //Inicializamos al crear un elemento de la lista
            holder = new ViewHolder();
            holder.txtPhone = (TextView) item.findViewById(R.id.txtPhoneNumber);
            holder.txtName = (TextView) item.findViewById(R.id.txtNameContact);
            //Guardamos el tag que contendra las referencias de los hijos
            item.setTag(holder);
        } else {

            holder = (ViewHolder) item.getTag();
        }

        if (contactModel != null) {
            holder.txtPhone.setText(contactModel.getPhone());
            holder.txtName.setText(contactModel.getNameContact());
        }

        return (item);
    }
}
