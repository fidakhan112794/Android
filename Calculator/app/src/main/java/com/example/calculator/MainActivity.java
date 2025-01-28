package com.example.calculator;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity  {

    TextView solution_tv ,result_tv;
    MaterialButton btnC,btn_open_bracket,btn_close_bracket;
    MaterialButton btn_devide, btn_multiply, btn_plus, btn_minus, btn_equal;
    MaterialButton btn7, btn8, btn9, btn4, btn5, btn6, btn1, btn2,btn3, btn0;
    MaterialButton btnAC, btn_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        solution_tv = findViewById(R.id.solution_tv);
        result_tv = findViewById(R.id.result_tv);

        assignId(btn_dot,R.id.btn_dot);
        assignId(btn_minus,R.id.btn_minus);
        assignId(btn_plus,R.id.btn_plus);
        assignId(btn_devide,R.id.btn_devide);
        assignId(btn_multiply,R.id.btn_multiply);
        assignId(btn_equal,R.id.btn_equal);
        assignId(btn7,R.id.btn7);
        assignId(btn8,R.id.btn8);
        assignId(btn9,R.id.btn9);
        assignId(btn4,R.id.btn4);
        assignId(btn5,R.id.btn5);
        assignId(btn6,R.id.btn6);
        assignId(btn1,R.id.btn1);
        assignId(btn2,R.id.btn2);
        assignId(btn3,R.id.btn3);
        assignId(btn0,R.id.btn0);
        assignId(btnAC,R.id.btnAC);
        assignId(btnC,R.id.btnC);
        assignId(btn_open_bracket,R.id.btn_open_bracket);
        assignId(btn_close_bracket,R.id.btn_close_bracket);

      /*  btnC = findViewById(R.id.btnC);
        btn_open_bracket = findViewById(R.id.btn_open_bracket);
        btn_close_bracket = findViewById(R.id.btn_close_bracket);
        btn_devide = findViewById(R.id. btn_devide);
        btn_multiply = findViewById(R.id. btn_multiply);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_equal = findViewById(R.id. btn_equal);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn0 = findViewById(R.id.btn0);
        btnAC = findViewById(R.id.btnAC);
        btn_dot = findViewById(R.id.btn_dot);*/
    }

    void assignId(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialButton btn = (MaterialButton) v;
                String btnText = btn.getText().toString();
                String dataTocalculate = solution_tv.getText().toString();



                if (btnText.equals("AC")) {
                    solution_tv.setText("");
                    result_tv.setText("0");
                    return;
                }
                if (btnText.equals("=")) {
                    solution_tv.setText(result_tv.getText());
                    return;
                }
                if (btnText.equals("C")) {
                    dataTocalculate = dataTocalculate.substring(0, dataTocalculate.length()-1);
                } else {

                    dataTocalculate = dataTocalculate + btnText;
                }

                solution_tv.setText(dataTocalculate);
                String finalResult = getResult(dataTocalculate);
                if (!finalResult.equals("Err")) {
                    result_tv.setText(finalResult);
                    Log.d("abc",finalResult);

                }
            }
        });
    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            Log.d("resultTAg", "getResult:"+ finalResult+"//"+data);
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}