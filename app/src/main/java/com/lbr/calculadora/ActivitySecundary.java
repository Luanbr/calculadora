package com.lbr.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lbr.calculadora.com.lbr.calculadora.model.Model;
import com.lbr.calculadora.com.lbr.calculadora.utils.CalculateUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivitySecundary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);
        final TextView txtResult = findViewById(R.id.txtResult);

        Intent it = getIntent();
        if(it != null){
            Bundle params = it.getExtras();
            if(params != null){
                String expression = params.getString("expression");
                String result = params.getString("result");
                txtResult.setText(expression.concat("=").concat(result));
            }
        }

    }
}
