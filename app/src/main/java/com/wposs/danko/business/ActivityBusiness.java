package com.wposs.danko.business;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.wposs.danko.R;
import com.wposs.danko.business.adapter.AdapterBusiness;
import com.wposs.danko.business.interfaces.Business_adaper;
import com.wposs.danko.login.dto.BusinessDTO;
import com.wposs.danko.utils.Global;

import java.util.ArrayList;

public class ActivityBusiness extends AppCompatActivity implements Business_adaper.View {

    private ArrayList<BusinessDTO> businessDTO = Global.BusinessDTO;
    private int id_categoria = Global.TIPO_CATEGORIA;
    private String nameCategoria = Global.NAME_CATEGORIA;
    private RecyclerView recyclerView;
    private TextView name_categoria;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        init();
        generarLista();

    }

    private void init() {
        name_categoria = findViewById(R.id.nameCategoria);
        recyclerView = findViewById(R.id.list_business);
    }

    private void generarLista() {
        name_categoria.setText(nameCategoria);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        AdapterBusiness adapterBusiness = new AdapterBusiness(businessDTO, this, id_categoria);
        recyclerView.setAdapter(adapterBusiness);
    }

    @Override
    public void showResponse(String url, String name, Context context) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }
}