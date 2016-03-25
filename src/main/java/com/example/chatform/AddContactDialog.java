package com.example.chatform;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.SQLException;
public class AddContactDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context ctx = getActivity();
        final EditText et = new EditText(ctx);
        et.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        et.setHint("abc@example.com");

        final AlertDialog alert = new AlertDialog.Builder(ctx)
                .setTitle("Add Contact")
                .setView(et)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        alert.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                Button okBtn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                okBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String email = et.getText().toString();
                        if (!isEmailValid(email)) {
                            et.setError("Invalid email!");
                            return;
                        }

                        try {
                            ContentValues values = new ContentValues(2);
                            values.put(DataProvider.COL_NAME, email.substring(0, email.indexOf('@')));
                            values.put(DataProvider.COL_EMAIL, email);
                            ctx.getContentResolver().insert(DataProvider.CONTENT_URI_PROFILE, values);
                        } catch (SQLException sqle) {
                        }
                        alert.dismiss();
                    }
                });
            }
        });
        return alert;
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
public void show(FragmentManager fragmentManager, String addContactDialog) {
    }


}
