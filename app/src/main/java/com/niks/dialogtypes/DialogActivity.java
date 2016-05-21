package com.niks.dialogtypes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
public class DialogActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    Button btnCustomAlert, btnSingleChoice, btnMultipleChoice,
            btnSimpleProgress, btnSimpleProgressHorizontal, btnSimpleDialog,
            btnBuiltinIdOfAndroid;
    View view;
    AlertDialog altDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        btnCustomAlert = (Button) findViewById(R.id.btnCustomAlert);
        btnCustomAlert.setOnClickListener(this);
        btnSingleChoice = (Button) findViewById(R.id.btnSingleChoice);
        btnSingleChoice.setOnClickListener(this);
        btnMultipleChoice = (Button) findViewById(R.id.btnMultipleChoice);
        btnMultipleChoice.setOnClickListener(this);
        btnSimpleProgress = (Button) findViewById(R.id.btnSimpleProgress);
        btnSimpleProgress.setOnClickListener(this);
        btnSimpleProgressHorizontal = (Button) findViewById(R.id.btnSimpleProgressHorizontal);
        btnSimpleProgressHorizontal.setOnClickListener(this);
        btnSimpleDialog = (Button) findViewById(R.id.btnSimpleDialog);
        btnSimpleDialog.setOnClickListener(this);
        btnBuiltinIdOfAndroid = (Button) findViewById(R.id.btnBuiltinIdOfAndroid);
        btnBuiltinIdOfAndroid.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        switch (i) {
        case R.id.btnCustomAlert:
            Builder builder = new Builder(this);
            LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.dialog, null);
            final EditText etName = (EditText) view.findViewById(R.id.etName);
            Button btnOk = (Button) view.findViewById(R.id.btnOk);
            Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
            btnOk.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    {
                        if (etName.getText().toString().equalsIgnoreCase("")) {
                            etName.requestFocus();
                            etName.setError("Please enter your name");
                        } else {
                            Builder b = new Builder(
                                    DialogActivity.this);
                            b.setMessage(etName.getText().toString());
                            b.setPositiveButton("ok",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            b.show();
                        }
                    }
                }
            });

            btnCancel.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    altDialog.dismiss();
                }
            });
            builder.setView(view);
            altDialog = builder.create();
            altDialog.show();

            break;
        case R.id.btnSingleChoice:
            Builder builderSingle = new Builder(
                    DialogActivity.this);
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Select One Name:-");
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    DialogActivity.this,
                    android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("Hardik");
            arrayAdapter.add("Archit");
            arrayAdapter.add("Jignesh");
            arrayAdapter.add("Umang");
            arrayAdapter.add("Gatti");
            builderSingle.setNegativeButton("cancel",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            builderSingle.setAdapter(arrayAdapter,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String strName = arrayAdapter.getItem(which);
                            Builder builderInner = new Builder(
                                    DialogActivity.this);
                            builderInner.setMessage(strName);
                            builderInner.setTitle("Your Selected Item is");
                            builderInner.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            builderInner.show();
                        }
                    });
            builderSingle.show();
            break;

        case R.id.btnMultipleChoice:
            Builder builderMultiple = new Builder(
                    DialogActivity.this);
            builderMultiple.setIcon(R.mipmap.ic_launcher);
            builderMultiple.setTitle("Select item:-");
            final ArrayList<String> alSelectedItem = new ArrayList<String>();
            final String[] strItem = new String[] { "Hardik", "Archit",
                    "Jignesh", "Umang", "Gatti" };
            builderMultiple.setMultiChoiceItems(strItem, null,
                    new DialogInterface.OnMultiChoiceClickListener() {

                      @Override
                        public void onClick(DialogInterface dialog, int which,
                                boolean isChecked) {
                            if (isChecked) {
                                alSelectedItem.add(strItem[which]);
                            } else {
                                alSelectedItem.remove(strItem[which]);
                            }
                        }
                    });
            builderMultiple.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builderMultiple.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (alSelectedItem.size() != 0) {
                                String strMsg = "";
                                for (int i = 0; i < alSelectedItem.size(); i++) {
                                    strMsg = strMsg + " "
                                            + alSelectedItem.get(i);
                                }
                                Builder builderInnerMul = new Builder(
                                        DialogActivity.this);
                                builderInnerMul.setMessage(strMsg);
                                builderInnerMul.setIcon(R.mipmap.ic_launcher);
                                builderInnerMul
                                        .setTitle("You select following item:-");
                                builderInnerMul.setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                builderInnerMul.show();
                            }
                        }
                    });
            builderMultiple.show();
            break;

        case R.id.btnSimpleProgress:
            ProgressDialog pd = new ProgressDialog(DialogActivity.this);
            pd.setTitle("Progress");
            pd.setMessage("Loading...");
            pd.setCancelable(true);
            pd.show();
            break;
        case R.id.btnSimpleProgressHorizontal:
            ProgressDialog pd1 = new ProgressDialog(DialogActivity.this);
            pd1.setTitle("Progress");
            pd1.setMessage("Loading...");

            pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd1.show();
            break;
        case R.id.btnSimpleDialog:
            Builder simpleBuilder = new Builder(
                    DialogActivity.this);
            simpleBuilder.setTitle("This is Title");
            simpleBuilder.setMessage("Hello Hardik!!!");
            simpleBuilder.setIcon(R.mipmap.ic_launcher);
            simpleBuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            simpleBuilder.show();
            break;
//        case R.id.btnBuiltinIdOfAndroid:
//            Builder aalert = new AlertDialog.Builder(this);
//            ArrayAdapter<String> array = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1);
//            array.add("Hardik");
//            array.add("Umang");
//            array.add("Jignesh");
//            array.add("Archit");
//            array.add("Bhautik");
//            array.add("Chandu");
//            aalert.setTitle("Select name");
//            aalert.setPositiveButton("Ok",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//            aalert.setAdapter(array, null);
//            aalert.create().show();
//            break;
        }

    }
}