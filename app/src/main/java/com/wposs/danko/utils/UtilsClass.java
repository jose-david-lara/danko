package com.wposs.danko.utils;

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

    private TextView textDialogBody;


    public void dialogMessage (Context context, DialogoInterface interfaceDialog, String dialogBody){

        textDialogBody = (TextView) findViewById(R.id.textDialogError);

        Dialog dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.dialog_error);

        dialogo.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogo.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        textDialogBody.setText(dialogBody);
        ImageView imageView = dialogo.findViewById(R.id.vectorImage);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textDialogBody.setTextColor(context.getColor(R.color.redError));
        }*/
        imageView.setImageDrawable(context.getDrawable(R.drawable.error));


        dialogo.show();
        dialogo.getWindow().setAttributes(lp);
        dialogo.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialogo.findViewById(R.id.buttonAcceptDialog).setOnClickListener(v -> {
            interfaceDialog.accepted();
        });

        Toast.makeText(context, dialogBody, Toast.LENGTH_SHORT).show();

    }
}
