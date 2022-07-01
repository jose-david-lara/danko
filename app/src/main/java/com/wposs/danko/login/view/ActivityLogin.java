package com.wposs.danko.login.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.wposs.danko.R;
import com.wposs.danko.home.view.ActivityHome;
import com.wposs.danko.interfaces.DialogoInterface;
import com.wposs.danko.interfaces.ModelInterface;
import com.wposs.danko.login.dto.CategoriasDTO;
import com.wposs.danko.login.dto.UserDTO;
import com.wposs.danko.login.service.LoginService;
import com.wposs.danko.dto.JsonResponse;
import com.wposs.danko.parameters.view.ActivityParameters;
import com.wposs.danko.signup.view.ActivitySignUp;
import com.wposs.danko.utils.Defines;
import com.wposs.danko.utils.InfoDevice;
import com.wposs.danko.utils.UtilsClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    private Button buttonEnter;
    private Button buttonInvitado;
    private Button buttonSignUp;
    private EditText capUser;
    private EditText capPass;
    private final Context context = ActivityLogin.this;
    private UtilsClass utilsClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        requestPermission();
        initComponents();
        validateParameters();

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onClick(View v) {
        String msjRed  = InfoDevice.infoRedConnect(ActivityLogin.this);
        if(msjRed.isEmpty()) {
            if (v.getId() == buttonEnter.getId()) {
                if (capUser.getText().length() > 0 && capPass.getText().length() > 0)
                    login(Defines.USER_APP);
                else
                    new UtilsClass().dialogMessageSimple(ActivityLogin.this, "ERROR", "Escribe tus credenciales por favor");
            }

            if (v.getId() == buttonInvitado.getId()) {
                utilsClass.dialogMessageOptions(ActivityLogin.this, new DialogoInterface() {
                    @Override
                    public void accepted() {
                        login(Defines.USER_GUEST);
                    }

                    @Override
                    public void denied() {
                    }
                }, "¿Dese iniciar como invitado?", R.drawable.ic_alert);

            }
            if (v.getId() == buttonSignUp.getId()) {
                nextActivity(Defines.ACTIVITY_SIGNUP);
            }
        }else{
            new UtilsClass().dialogMessageSimple(ActivityLogin.this, "INFORMACION RED", msjRed);
        }

    }

    private void initComponents() {
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonInvitado = findViewById(R.id.buttonInvitado);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        capUser = (EditText) findViewById(R.id.capUser);
        capPass = (EditText) findViewById(R.id.capPass);
        utilsClass = new UtilsClass();

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(ActivityLogin.this,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                1);
    }

    private void validateParameters (){
        String msjRed  = InfoDevice.infoRedConnect(ActivityLogin.this);
        if(!msjRed.isEmpty())
            new UtilsClass().dialogMessageSimple(ActivityLogin.this, "INFORMACION RED", msjRed);
    }





    private void addLocation(ArrayList<CategoriasDTO> categoriasDTO) {
        /*for (int i = 0; i < categoriasDTO.size(); i++) {
            ArrayList<BusinessDTO> businessDTO = categoriasDTO.get(i).getBusinessDTOList();

            for (int j = 0; j < businessDTO.size(); j++) {
                String pais = businessDTO.get(j).getPais();
                String estado = businessDTO.get(j).getEstado();
                String ciudad = businessDTO.get(j).getCiudad();
                if (Global.pais.size() == 0) {
                    Global.pais.add(pais);
                } else {
                    boolean existe = false;
                    for (int x = 0; x < Global.pais.size(); x++) {
                        if (pais.equalsIgnoreCase(Global.pais.get(x))) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) Global.pais.add(pais);
                }
                if (Global.estado.size() == 0) {
                    Global.estado.add(estado);
                } else {
                    boolean existe = false;
                    for (int x = 0; x < Global.estado.size(); x++) {
                        if (estado.equalsIgnoreCase(Global.estado.get(x))) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) Global.estado.add(estado);
                }
                if (Global.ciudad.size() == 0) {
                    Global.ciudad.add(ciudad);
                } else {
                    boolean existe = false;
                    for (int x = 0; x < Global.ciudad.size(); x++) {
                        if (ciudad.equalsIgnoreCase(Global.ciudad.get(x))) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) Global.ciudad.add(ciudad);
                }
            }
        }*/
    }

    public void login(String typeUser) {
        utilsClass.progressbar(ActivityLogin.this,"Autenticando...", Defines.FLAG_SHOW_PROGRESS_BAR);



        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setUser_app(typeUser);
            userDTO.setUser((capUser.getText().length() > 0)?capUser.getText().toString():Defines.USER_GUEST);
            userDTO.setPassword((capPass.getText().length() > 0)?capPass.getText().toString():Defines.USER_GUEST);
            userDTO.setDevice(InfoDevice.getSerial(ActivityLogin.this));
            userDTO.setVersion(InfoDevice.getVersion(ActivityLogin.this));
            userDTO.setIp(InfoDevice.getIPAddressIPv4((ModelInterface.user.getName_interface_connect().equals("WIFI"))?"wlan":"radio", ActivityLogin.this));

            new LoginService().getUserService(ActivityLogin.this,userDTO, ActivityLogin.this);


        } catch (Exception e) {
            e.printStackTrace();
            utilsClass.progressbar(ActivityLogin.this,null, Defines.FLAG_CANCEL_PROGRESS_BAR);
            new UtilsClass().dialogMessageSimple(ActivityLogin.this, "INFORMACION", "Error inesperado, por favor intente de nuevo");
        }

    }


    public void responseAuthenticateUser(Map<String, Object> responseService){
        utilsClass.progressbar(ActivityLogin.this,null, Defines.FLAG_CANCEL_PROGRESS_BAR);
        if(responseService.get("error") != null){
            if(Objects.equals(responseService.get("error"), false) && Objects.equals(responseService.get("message"), "OKAY")){
                new UtilsClass().dialogMessage(this, new DialogoInterface() {
                    @Override
                    public void accepted() {
                        nextActivity(Defines.ACTIVITY_PARAMETERS);
                    }

                    @Override
                    public void denied() {

                    }
                }, "Login Exitoso", R.drawable.ic_alert);


            }else if(Objects.equals(responseService.get("error"), false) && !Objects.equals(responseService.get("message"), "OKAY")){
                new UtilsClass().dialogMessage(this, new DialogoInterface() {
                    @Override
                    public void accepted() {
                        nextActivity(Defines.ACTIVITY_PARAMETERS);
                    }

                    @Override
                    public void denied() {

                    }
                }, Objects.requireNonNull(responseService.get("message")).toString(), R.drawable.ic_alert);
                //new UtilsClass().dialogMessageSimple(ActivityLogin.this, "CUENTA", Objects.requireNonNull(responseService.get("message")).toString());

            }else if (!Objects.equals(responseService.get("error"), false)){
                new UtilsClass().dialogMessageSimple(ActivityLogin.this, "CUENTA", "Error inesperado, por favor intente de nuevo");
            }
        }else{
            new UtilsClass().dialogMessageSimple(ActivityLogin.this, "CUENTA", "Error inesperado, por favor intente de nuevo");
        }
    }
/*
    private void paramsCategories (){
        progressDialog = new ProgressDialog(ActivityLogin.this);
        progressDialog.show();
        try {

            new ConsumeServicesExpress().consume_api(Defines.PARAMS_CATEGORIES, new OnResponseInterface() {
                @Override
                public boolean finish_consumer_services(JsonResponse jsonResponse) {
                    if (jsonResponse.getJsonObjetResponse().get("message") == null) {
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }

                        fillInCategories(jsonResponse);
                        showCategories();
                    }
                    if (jsonResponse.getJsonObjetResponse().get("message") != null){
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }
                        showError(jsonResponse.getJsonObjetResponse().get("message").getAsString());
                    }
                    if(jsonResponse.getJsonObjetResponse().get("msgError") != null){
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }
                        showError("Error de comunicacion-403C");
                    }
                    return true;
                }

                @Override
                public boolean finish_fail_consumer_services() {
                    if (progressDialog.isShowing()) {
                        progressDialog.cancel();
                    }
                    showError("Intenta de nuevo por favor.");
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    private void fillInCategories (@NonNull JsonResponse jsonResponse){
        Gson gson = new Gson();
        List<CategoriasDTO> listCategories = new ArrayList<>();
        JsonArray jsonArray= (JsonArray) jsonResponse.getJsonObjetResponse().get("parameters");

        for(int x = 0; x < jsonArray.size(); x++){
            CategoriasDTO categoriasDTO ;
            categoriasDTO = gson.fromJson(jsonArray.get(x), CategoriasDTO.class);
            listCategories.add(categoriasDTO);
        }

        ModelInterface.parametersDTO.setParameters(listCategories);

    }

    private void nextActivity (String activity){
        Intent intent;
        switch(activity) {
            case "ACTIVITY_SIGNUP":
                intent = new Intent(this,ActivitySignUp.class);
                startActivity(intent);
                this.finish();
                break;
            case "ACTIVITY_PARAMETERS":
                intent = new Intent(this,ActivityParameters.class);
                startActivity(intent);
                this.finish();
                break;
        }


    }


}