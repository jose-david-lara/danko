package com.wposs.danko.home.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.wposs.danko.R;
import com.wposs.danko.business.ActivityBusiness;
import com.wposs.danko.home.interfaces.Home_interface;
import com.wposs.danko.home.service.HomeService;
import com.wposs.danko.login.dto.BusinessDTO;
import com.wposs.danko.login.dto.CategoriasDTO;
import com.wposs.danko.parameters.dto.CityDTO;
import com.wposs.danko.parameters.dto.CountryDTO;
import com.wposs.danko.utils.Global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityHome extends AppCompatActivity implements Home_interface.View {

    private ArrayList<CategoriasDTO> categoriasDTO = Global.categoriasDTO;
    private ArrayList<String> paises ;
    private ArrayList<String> estados = Global.estado;
    private ArrayList<String> ciudades = Global.ciudad;
    private AutoCompleteTextView inputCountryComplete;
    private AutoCompleteTextView inputStatesComplete;
    private AutoCompleteTextView inputCityComplete;
    //private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComponents();
        fillAdapters();

        generarlista();
    }

    private void initComponents(){
        inputCountryComplete = findViewById(R.id.inputCountry);
        inputStatesComplete = findViewById(R.id.inputStates);
        inputCityComplete = findViewById(R.id.inputCity);


    }

    private void fillAdapters(){
        List<CountryDTO> listCountrys;
        ArrayList<String> arrayListCountrys;
        ArrayList<String> arrayListStates = new ArrayList<>();
        ArrayList<String> arrayListCities;

        Map<String, ArrayList<String>> response;

        response = new HomeService().getLocationsDefaultService(ActivityHome.this);

        arrayListCountrys  = response.get("countries");
        arrayListCities  = response.get("cities");

        ArrayAdapter<String> adapterCountry = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayListCountrys);
        inputCountryComplete.setAdapter(adapterCountry);

        /*ArrayAdapter<String> adapterStates = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayListStates);
        inputStatesComplete.setAdapter(adapterStates);*/

        ArrayAdapter<String> adapterCity = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayListCities);
        inputCityComplete.setAdapter(adapterCity);
        /*

        ArrayAdapter<String> adapter_states = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, estados);
        cap_estado.setAdapter(adapter_estado);

        ArrayAdapter<String> adapter_city = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ciudades);
        cap_city.setAdapter(adapter_ciudad);*/
    }

    private void generarlista(){
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
      //  AdapterHome adapterHome = new AdapterHome(categoriasDTO, this);
        //recyclerView.setAdapter(adapterHome);
    }


    @Override
    public void showResponse(ArrayList<BusinessDTO> businessDTO, String id_categoria, String name_categoria, Context contex) {
        Global.BusinessDTO = businessDTO;
        Global.TIPO_CATEGORIA = Integer.parseInt(id_categoria);
        Global.NAME_CATEGORIA = name_categoria;
        Intent intent = new Intent(contex, ActivityBusiness.class);
        contex.startActivity(intent);
    }

    public void onClickExplorer(View view) {
    }
}