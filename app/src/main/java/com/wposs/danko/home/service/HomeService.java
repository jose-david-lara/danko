package com.wposs.danko.home.service;


import android.content.Context;

import com.wposs.danko.home.repository.HomeRepository;
import com.wposs.danko.home.view.ActivityHome;
import com.wposs.danko.parameters.dto.CityDTO;
import com.wposs.danko.parameters.dto.CountryDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeService {

    private HomeRepository homeRepository = HomeRepository.getInstance();

    public Map<String, ArrayList<String>> getLocationsDefaultService(Context context){

        Map<String, ArrayList<String>> responseService = new HashMap<>();
        List<CountryDTO> listCountrys;
        List<CityDTO> listCities;
        ArrayList<String> arrayListCountrys = new ArrayList<>();
        ArrayList<String> arrayListStates = new ArrayList<>();
        ArrayList<String> arrayListCities = new ArrayList<>();

        listCountrys = homeRepository.getLocationsDefaultRepository(context);
        listCities = homeRepository.getLocationsDefaultCitiesRepository(context);

        for(CountryDTO countryDTO : listCountrys){
            arrayListCountrys.add(countryDTO.getName());
            countryDTO.setCities(listCities);
            if(countryDTO.getCities() != null) {
                for (CityDTO cityDTO : countryDTO.getCities()) {
                    if (countryDTO.getIdCountry().equals(cityDTO.getIdCountry())) {
                        arrayListCities.add(cityDTO.getName());
                    }
                }
            }
        }

        responseService.put("countries",arrayListCountrys);
        responseService.put("cities",arrayListCities);

        return responseService;
    }



}
