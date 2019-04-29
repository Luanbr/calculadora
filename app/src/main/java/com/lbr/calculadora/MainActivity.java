package com.lbr.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lbr.calculadora.com.lbr.calculadora.model.Model;
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

        Log.i("OnCreate", calculate().toString());
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
                case R.id.btn_elevate:
                    concatExpression("^");
                    break;
                case R.id.btn_clean:
                    clean();
                    break;
                case R.id.btn_send:
                    send();
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
        findViewById(R.id.btn_send).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_negate).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_clean).setOnClickListener(calculateOnClickListener);
        findViewById(R.id.btn_square_root).setOnClickListener(calculateOnClickListener);
    }

    private void concatExpression(CharSequence numberOrOperation){
        final TextView txtExpression = findViewById(R.id.txtExpression);

        if(!isNumberAddOperationFormat(numberOrOperation)){
            txtExpression.setText(txtExpression.getText().toString().concat(numberOrOperation.toString()));
        }else{
            StringBuilder expression = new StringBuilder(txtExpression.getText().toString().concat(numberOrOperation.toString()));
            Model number = CalculateUtils.findFirstNumber(new StringBuilder(expression), numberOrOperation.toString());
            expression.delete(number.getIndex(), number.getLastIndexOperation());
            expression.insert(number.getIndex(), numberOrOperation.toString().concat(number.getNumberStr().toString()));
            txtExpression.setText(expression);
        }
    }

    private StringBuilder calculate(){
        CalculateUtils c = new CalculateUtils();
        final TextView txtExpression = findViewById(R.id.txtExpression);
        StringBuilder expression = new StringBuilder(txtExpression.getText());

        //Cosine
        Pattern pattern = Pattern.compile(CalculateUtils.COSINE);
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()){
            expression = c.manipulateExpressionOperationBeforeNumber(expression, CalculateUtils.COSINE);
        }

        //Sine
        pattern = Pattern.compile(CalculateUtils.SINE);
        matcher = pattern.matcher(expression);
        while (matcher.find()){
            expression = c.manipulateExpressionOperationBeforeNumber(expression, CalculateUtils.SINE);
        }

        //Tangent
        pattern = Pattern.compile(CalculateUtils.TANGENT);
        matcher = pattern.matcher(expression);
        while (matcher.find()){
            expression = c.manipulateExpressionOperationBeforeNumber(expression, CalculateUtils.TANGENT);
        }

        //Power
        pattern = Pattern.compile("\\^");
        matcher = pattern.matcher(expression);
        while (matcher.find()){
            expression = c.manipulateExpression(expression, "^");
        }

        //Multiplication
        pattern = Pattern.compile("\\*");
        matcher = pattern.matcher(expression);
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

    public boolean isNumberAddOperationFormat(CharSequence entry){
        //If pattern not (Number before operation with only one number )
        if(!(entry.equals(CalculateUtils.COSINE) || entry.equals(CalculateUtils.TANGENT) || entry.equals(CalculateUtils.SINE))){
            return  false;
        }
        return true;
    }

    public void clean(){
        final TextView txtExpression = findViewById(R.id.txtExpression);
        final TextView txtResult = findViewById(R.id.txtResult);
        txtExpression.setText(null);
        txtResult.setText(null);
    }

    public void send(){
        final TextView txtExpression = findViewById(R.id.txtExpression);
        final TextView txtResult = findViewById(R.id.txtResult);
        Intent it = new Intent(this, ActivitySecundary.class);
        Bundle params = new Bundle();
        params.putString("expression", txtExpression.getText().toString());
        params.putString("result", txtResult.getText().toString());

        it.putExtras(params);
        startActivity(it);
    }

    @Override
    protected void onStart() {
        Log.i("OnStart", calculate().toString());
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i("onRestart", calculate().toString());
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i("onResume", calculate().toString());
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("onPause", calculate().toString());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("onStop", calculate().toString());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("onDestroy", calculate().toString());
        super.onDestroy();
    }
}
