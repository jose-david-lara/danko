package com.wposs.danko.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import com.wposs.danko.R;
import com.wposs.danko.interfaces.DialogoInterface;

public class UtilsClass extends AppCompatActivity {


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
}
