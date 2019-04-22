package com.lbr.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lbr.calculadora.com.lbr.calculadora.utils.CalculateUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtExpression = findViewById(R.id.txtExpression);
        final TextView txtResult = findViewById(R.id.txtResult);

        //Register button listener
        registerBtnListener();

    }

    private View.OnClickListener calculateOnClickListener = new View.OnClickListener(){
        @Override
        public  void onClick(View v){
            Button btn = (Button) v;
            switch (btn.getId()){
                case R.id.btn_result:
                    final TextView txtResult = findViewById(R.id.txtResult);
                    txtResult.setText(calculate());
                    break;
                default:
                    concatExpression(btn.getText());
            }
        }
    };

    private void registerBtnListener(){
        findViewById(R.id.btn_zero).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_one).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_two).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_three).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_four).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_five).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_six).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_seven).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_eight).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_nine).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_point).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_add).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_subtract).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_multiplication).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_division).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_percent).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_elevate).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_factorial).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_sine).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_cosine).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_tangent).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_result).setOnClickListener(calculateOnClickListener);
    }

    private void concatExpression(CharSequence numberOrOperation){
        final TextView txtExpression = findViewById(R.id.txtExpression);
        txtExpression.setText(txtExpression.getText().toString().concat(numberOrOperation.toString()));
    }

    private StringBuilder calculate(){
        CalculateUtils c = new CalculateUtils();
        final TextView txtExpression = findViewById(R.id.txtExpression);
        StringBuilder expression = new StringBuilder(txtExpression.getText());

        //Multiplication
        Pattern pattern = Pattern.compile("\\*");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()){
            expression = c.manipulateExpression(expression, "*");
        }

        //Division
        pattern = Pattern.compile("/");
        matcher = pattern.matcher(expression);
        while (matcher.find()){
            expression = c.manipulateExpression(expression, "/");
        }

        //Addition
        pattern = Pattern.compile("\\+");
        matcher = pattern.matcher(expression);
        while (matcher.find()){
            expression = c.manipulateExpression(expression, "+");
        }

        //Subtraction
        pattern = Pattern.compile("-");
        matcher = pattern.matcher(expression);
        while (matcher.find()){
            expression = c.manipulateExpression(expression, "-");
        }
        return expression;
    }
}
