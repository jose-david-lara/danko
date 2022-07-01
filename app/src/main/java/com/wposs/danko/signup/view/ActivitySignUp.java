package com.wposs.danko.signup.view;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.wposs.danko.R;
import com.wposs.danko.interfaces.DialogoInterface;
import com.wposs.danko.interfaces.ModelInterface;
import com.wposs.danko.login.view.ActivityLogin;
import com.wposs.danko.signup.dto.UserCreateDTO;
import com.wposs.danko.signup.service.SignUpService;
import com.wposs.danko.utils.Defines;
import com.wposs.danko.utils.InfoDevice;
import com.wposs.danko.utils.UtilsClass;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

public class ActivitySignUp extends AppCompatActivity {


    private EditText inputNames;
    private EditText inputLastNames;
    private EditText inputEmail;
    private EditText inputPass;
    private EditText inputDateBirthDay;
    private Button buttonCalender;
    private TextInputLayout editextLyName;
    private TextInputLayout editextLyLastName;
    private TextInputLayout editextLyEmail;
    private TextInputLayout editextLyPass;
    private TextInputLayout editextLyBirthDate;
    private CheckBox checkBoxTermCond;
    private UtilsClass utilsClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        initComponents();

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


    private void initComponents() {

        inputNames = (EditText) findViewById(R.id.inputNames);
        inputLastNames = (EditText) findViewById(R.id.inputLastName);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputDateBirthDay = (EditText) findViewById(R.id.inputLastDateBirthDay);
        inputPass = (EditText) findViewById(R.id.inputPass);
        editextLyName = (TextInputLayout) findViewById(R.id.editext_ly_name);
        editextLyLastName = (TextInputLayout) findViewById(R.id.editext_ly_last_name);
        editextLyEmail = (TextInputLayout) findViewById(R.id.editext_ly_email);
        editextLyPass = (TextInputLayout) findViewById(R.id.editext_ly_pass);
        editextLyBirthDate = (TextInputLayout) findViewById(R.id.editext_ly_date_birthDAY);
        buttonCalender = (Button) findViewById(R.id.buttonCalender);
        checkBoxTermCond = (CheckBox) findViewById(R.id.checkBoxTermCond);
        utilsClass = new UtilsClass();


    }

    private boolean validateForm() {

        if (!validEditText(inputNames,editextLyName,getString(R.string.error_name_sign_up))) {
            return false;
        }
        if (!validEditText(inputLastNames,editextLyLastName,getString(R.string.error_last_name_sign_up))) {
            return false;
        }
        if (!validEditText(inputEmail,editextLyEmail,getString(R.string.error_email_sign_up))) {
            return false;
        }
        if (!validEditText(inputLastNames,editextLyLastName,getString(R.string.error_last_name_sign_up))) {
            return false;
        }
        if (!validEditText(inputDateBirthDay,editextLyBirthDate,getString(R.string.error_birthday_sign_up))) {
            return false;
        }
        if (!checkBoxTermCond.isChecked()) {
            new UtilsClass().dialogMessageSimple(ActivitySignUp.this, "Información", "Por favor acepte Terminos y Condiciones");
            return false;
        }

        return true;
    }

    private boolean validEditText (EditText editText, TextInputLayout textInputLayout, String textError){
        if(editText.getText().toString().trim().isEmpty()){
            //textInputLayout.setError(textError);
            editText.setError(textError);
            editText.requestFocus();
        }else{
            textInputLayout.setErrorEnabled(false);
            return true;
        }

        return false;

    }

    public void onClickCalender(View view) {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePicker = new DatePickerDialog(ActivitySignUp.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if(Period.between( LocalDate.of(year,month,dayofMonth),LocalDate.now() ).getYears() < 18){
                        new UtilsClass().dialogMessageSimple(ActivitySignUp.this, "Información", "Debes tener 18 años o mas");
                        return;
                    }
                }
                String dateLocal = dayofMonth + "/" + month + "/" + year;
                inputDateBirthDay.setText(dateLocal);

            }
        }, year, month, day);
        datePicker.show();


    }

    public void onClickTermCond(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getString(R.string.term_cond_sign_up));

        WebView wv = new WebView(this);
        wv.loadUrl("http:\\www.google.com");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();


    }

    public void onClickSignUp(View view) {
        UserCreateDTO userCreateDTO = new UserCreateDTO();


        if (validateForm()) {
            utilsClass.progressbar(ActivitySignUp.this,"Creando cuenta...", Defines.FLAG_SHOW_PROGRESS_BAR);
            userCreateDTO.setName(inputNames.getText().toString());
            userCreateDTO.setLast_name(inputLastNames.getText().toString());
            userCreateDTO.setUser(inputEmail.getText().toString());
            userCreateDTO.setPassword(inputPass.getText().toString());
            userCreateDTO.setBirthday(inputDateBirthDay.getText().toString());
            userCreateDTO.setUrl_photo("xxx");
            userCreateDTO.setDevice(InfoDevice.getIPAddressIPv4((ModelInterface.user.getName_interface_connect().equals("WIFI")) ? "wlan" : "radio", ActivitySignUp.this));
            new SignUpService().getSignUpUser(ActivitySignUp.this, userCreateDTO, this);
        }
    }

    public void onClickCancelSignUp(View view) {
        nextActivity();
    }

    public void responseCreateUser(Map<String, Object> responseService){
        utilsClass.progressbar(ActivitySignUp.this,null, Defines.FLAG_CANCEL_PROGRESS_BAR);
        if(responseService.get("error") != null){
            if(Objects.equals(responseService.get("error"), false) && Objects.equals(responseService.get("message"), "OKAY")){
                new UtilsClass().dialogMessage(this, new DialogoInterface() {
                    @Override
                    public void accepted() {
                        nextActivity();
                    }

                    @Override
                    public void denied() {

                    }
                }, "Cuenta creada exitosamente", R.drawable.ic_alert);


            }else if(Objects.equals(responseService.get("error"), false) && !Objects.equals(responseService.get("message"), "OKAY")){
                new UtilsClass().dialogMessageSimple(ActivitySignUp.this, "CUENTA", Objects.requireNonNull(responseService.get("message")).toString());
            }else if (!Objects.equals(responseService.get("error"), false)){
                new UtilsClass().dialogMessageSimple(ActivitySignUp.this, "CUENTA", "Error inesperado, por favor intente de nuevo");
            }
        }else{
            new UtilsClass().dialogMessageSimple(ActivitySignUp.this, "CUENTA", "Error inesperado, por favor intente de nuevo");
        }
    }


    private void nextActivity ( ){
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
        this.finish();
    }



    /*String dateLocal = dayofMonth + "/" + month + "/" + year;
                inputDateBirthDay.setText(dateLocal);
}
        },year,month,day);*/

}