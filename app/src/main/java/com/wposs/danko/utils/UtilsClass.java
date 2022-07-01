package com.wposs.danko.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wposs.danko.R;
import com.wposs.danko.interfaces.DialogoInterface;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.io.ConsumeServicesExpress;
import com.wposs.danko.dto.JsonResponse;

public class UtilsClass extends AppCompatActivity {

    public ProgressDialog progressDialog;


    @SuppressLint("UseCompatLoadingForDrawables")
    public void dialogMessage (Context context, DialogoInterface interfaceDialog, String dialogBody, int idDrawable){

        //

        Dialog dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.dialog_error);

        dialogo.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogo.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView textDialogBody =dialogo.findViewById(R.id.textDialogError);
        textDialogBody.setText(dialogBody);
        ImageView imageView = dialogo.findViewById(R.id.vectorImage);

        imageView.setImageDrawable(context.getDrawable(idDrawable));


        dialogo.show();
        dialogo.getWindow().setAttributes(lp);
        dialogo.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialogo.findViewById(R.id.buttonAcceptDialog).setOnClickListener(v -> {
            interfaceDialog.accepted();
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void dialogMessageOptions (Context context, DialogoInterface interfaceDialog, String dialogBody, int idDrawable){

        Dialog dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.dialog_msj_accept_cancl);

        dialogo.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogo.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView textDialogBody =dialogo.findViewById(R.id.textDialogAccpCancl);
        textDialogBody.setText(dialogBody);
        ImageView imageView = dialogo.findViewById(R.id.vectorImageAccpCancl);

        imageView.setImageDrawable(context.getDrawable(idDrawable));


        dialogo.show();
        dialogo.getWindow().setAttributes(lp);
        dialogo.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialogo.findViewById(R.id.buttonCancelDialogAccpCancl).setOnClickListener(v -> {
            dialogo.cancel();
            interfaceDialog.denied();
        });
        dialogo.findViewById(R.id.buttonAcceptDialogAccpCancl).setOnClickListener(v -> {
            interfaceDialog.accepted();
        });




    }

    public void dialogMessageSimple(Context activity, String Tittle, String msjBody) {


        AlertDialog.Builder dialogo = new AlertDialog.Builder(activity);
        dialogo.setTitle(Tittle);
        dialogo.setMessage(msjBody);
        dialogo.setCancelable(true);
        dialogo.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        dialogo.show();

    }

    public boolean echoTest(Context context) {
        new ConsumeServicesExpress().consume_api(Defines.ECHO_TEST, new OnResponseInterface() {
            @Override
            public boolean finish_consumer_services(JsonResponse jsonResponse) {
                Toast.makeText(context, "Servidor En Linea", Toast.LENGTH_SHORT).show();
                return true;
            }


            @Override
            public boolean finish_fail_consumer_services() {
                Toast.makeText(context, "Servidor En falla", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return true;
    }

    public void progressbar (Context context, String body, boolean flagShow){

        if(flagShow == Defines.FLAG_SHOW_PROGRESS_BAR) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(body);
            progressDialog.show();
        }else if(flagShow == Defines.FLAG_CANCEL_PROGRESS_BAR){
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

    }

}
