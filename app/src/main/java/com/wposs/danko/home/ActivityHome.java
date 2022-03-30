package com.wposs.danko.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.wposs.danko.R;
import com.wposs.danko.business.ActivityBusiness;
import com.wposs.danko.home.adapter.AdapterHome;
import com.wposs.danko.home.interfaces.Home_interface;
import com.wposs.danko.login.dto.BusinessDTO;
import com.wposs.danko.login.dto.CategoriasDTO;
import com.wposs.danko.utils.Global;

import java.util.ArrayList;

public class ActivityHome extends AppCompatActivity implements Home_interface.View {

    private ArrayList<CategoriasDTO> categoriasDTO = Global.categoriasDTO;
    private ArrayList<String> paises = Global.pais;
    private ArrayList<String> estados = Global.estado;
    private ArrayList<String> ciudades = Global.ciudad;
    private AutoCompleteTextView cap_pais;
    private AutoCompleteTextView cap_estado;
    private AutoCompleteTextView cap_city;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        generarlista();
    }

    private void init(){
        cap_pais = findViewById(R.id.capPais);
        cap_estado = findViewById(R.id.capEstado);
        cap_city = findViewById(R.id.capCiudad);
        recyclerView = findViewById(R.id.recycler);

        ArrayAdapter<String> adapter_pais = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, paises);
        cap_pais.setAdapter(adapter_pais);

        ArrayAdapter<String> adapter_estado = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, estados);
        cap_estado.setAdapter(adapter_estado);

        ArrayAdapter<String> adapter_ciudad = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ciudades);
        cap_city.setAdapter(adapter_ciudad);
    }

    private void generarlista(){
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        AdapterHome adapterHome = new AdapterHome(categoriasDTO, this);
        recyclerView.setAdapter(adapterHome);
    }


    @Override
    public void showResponse(ArrayList<BusinessDTO> businessDTO, String id_categoria, String name_categoria, Context contex) {
        Global.BusinessDTO = businessDTO;
        Global.TIPO_CATEGORIA = Integer.parseInt(id_categoria);
        Global.NAME_CATEGORIA = name_categoria;
        Intent intent = new Intent(contex, ActivityBusiness.class);
        contex.startActivity(intent);
    }
}