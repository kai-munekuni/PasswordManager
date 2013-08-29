package com.example.pas;

/**
 * Created by munekunikai on 13/08/28.
 */
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EntryCreationDialog extends Dialog {
    int num=5;
    private Entry entry;

    private Button cancelButton;
    private Button okButton,makeButton;
    private EditText nameEdit;
    private EditText passwordEdit;

    public EntryCreationDialog(Context context, String title, Entry entry) {
        super(context);
        this.setTitle(title);
        this.setCanceledOnTouchOutside(false);
        this.setContentView(R.layout.entry_creation_dialog);
        this.entry = entry;

        initComponents();
    }


    private void initComponents() {
        cancelButton = (Button)findViewById(R.id.cancel_button);
        okButton = (Button)findViewById(R.id.ok_button);
        makeButton = (Button)findViewById(R.id.make_button);
        nameEdit = (EditText)findViewById(R.id.name_edit);
        passwordEdit = (EditText)findViewById(R.id.password_edit);

        nameEdit.setText(entry.getServiceName());
        passwordEdit.setText(entry.getPassword());

        cancelButton.setOnClickListener(new CancelButtonListener());
        okButton.setOnClickListener(new OkButtonListener());
        makeButton.setOnClickListener(new MakeButtonListener());

    }

    private class CancelButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            EntryCreationDialog.this.cancel();
        }
    }

    private class OkButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            entry.setServiceName(nameEdit.getText().toString());
            entry.setPassword(passwordEdit.getText().toString());
            entry.save(getContext());
            EntryCreationDialog.this.dismiss();
        }
    }
    private class MakeButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            passwordEdit.setText(GeneActivity.getRandomString(num));
        }
    }
}





