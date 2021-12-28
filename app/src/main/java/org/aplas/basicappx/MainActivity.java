package org.aplas.basicappx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Distance dist;
    private Weight weight;
    private Temperature temp;
    private Button convertBtn;
    private EditText inputTxt, outputTxt;
    private Spinner unitOri, unitConv;
    private RadioGroup unitType;
    private RadioButton checked;
    private CheckBox roundBox, formBox;
    private ImageView imgView, imgFormula;
    private AlertDialog startDialog;
    private ArrayAdapter<CharSequence> charSequenceArrayAdapter;

    public MainActivity() {
        this.dist = new Distance();
        this.weight = new Weight();
        this.temp = new Temperature();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertBtn = (Button) findViewById(R.id.convertButton);
        inputTxt = (EditText) findViewById(R.id.inputText);
        outputTxt = (EditText) findViewById(R.id.outputText);
        unitOri = (Spinner) findViewById(R.id.oriList);
        unitConv = (Spinner) findViewById(R.id.convList);
        unitType = (RadioGroup) findViewById(R.id.radioGroup);
        roundBox = (CheckBox) findViewById(R.id.chkRounded);
        formBox = (CheckBox) findViewById(R.id.chkFormula);
        imgView = (ImageView) findViewById(R.id.img);
        imgFormula = (ImageView) findViewById(R.id.imgFormula);

        unitType.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        checked = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                        int checkedRadio = 0;
                        if (checked.getId() == R.id.rbDist) {
                            checkedRadio = R.array.distList;
                            imgView.setImageResource(R.drawable.distance);
                        } else if (checked.getId() == R.id.rbTemp) {
                            checkedRadio = R.array.tempList;
                            imgView.setImageResource(R.drawable.temperature);
                        } else if (checked.getId() == R.id.rbWeight) {
                            checkedRadio = R.array.weightList;
                            imgView.setImageResource(R.drawable.weight);
                        }
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(unitType.getContext(), checkedRadio, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        unitOri.setAdapter(adapter);
                        unitConv.setAdapter(adapter);
                        inputTxt.setText("0");
                        outputTxt.setText("0");
                    }
                });
        formBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(formBox.isChecked()){
                    imgFormula.setVisibility(View.VISIBLE);
                } else {
                    imgFormula.setVisibility(View.INVISIBLE);
                }
            }
        });

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doConvert();
            }
        });

        unitOri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                doConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        unitConv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                doConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        roundBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                doConvert();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        startDialog = new AlertDialog.Builder(MainActivity.this).create();
        startDialog.setTitle("Application started");
        startDialog.setMessage("This app can use to convert any units");
        startDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        startDialog.show();
    }

    protected double convertUnit(String type, String oriUnit, String convUnit, double value){
        switch (type){
            case "Temperature":
                return temp.convert(oriUnit, convUnit, value);
            case "Distance":
                return dist.convert(oriUnit, convUnit, value);
            case "Weight":
                return weight.convert(oriUnit, convUnit, value);
        }
        return value;
    }

    protected String strResult(double value, boolean rounded) {
        if(rounded){
            DecimalFormat f = new DecimalFormat("#.##");
            return f.format(value);
        }
        DecimalFormat f = new DecimalFormat("#.#####");
        return f.format(value);
    }

    public void doConvert() {
        double val = 0, convert = 0;
        checked = (RadioButton) findViewById(unitType.getCheckedRadioButtonId());
        if (checked.getId() == R.id.rbDist) {
            val = Double.parseDouble(inputTxt.getText().toString());
            convert = convertUnit("Distance", unitOri.getSelectedItem().toString(), unitConv.getSelectedItem().toString(), val);
        } else if (checked.getId() == R.id.rbTemp) {
            val = Double.parseDouble(inputTxt.getText().toString());
            convert = convertUnit("Temperature", unitOri.getSelectedItem().toString(), unitConv.getSelectedItem().toString(), val);
        } else if (checked.getId() == R.id.rbWeight) {
            val = Double.parseDouble(inputTxt.getText().toString());
            convert = convertUnit("Weight", unitOri.getSelectedItem().toString(), unitConv.getSelectedItem().toString(), val);
        }
        outputTxt.setText(strResult(convert, roundBox.isChecked()));
    }
}