package com.adam.p1_slotmachine;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Buttons for listeners
        Button btnSetValue = (Button)findViewById(R.id.btn_setVal);
        Button btnNewGame = (Button)findViewById(R.id.btnNewGame);
        Button btnRunSlot = (Button)findViewById(R.id.btnRunSlot);


        btnSetValue.setOnClickListener(this);
        btnNewGame.setOnClickListener(this);
        btnRunSlot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() ==R.id.btn_setVal){
            // do stuff
            EditText inputAmt = (EditText) findViewById(R.id.input_amount);

            String tempAmt = inputAmt.getText().toString();
            int amt = Integer.parseInt(tempAmt);
            Bank.setAmount(amt);

            TextView displayAmt = (TextView)findViewById(R.id.displayBankAmount);
            displayAmt.setText("$" + Integer.toString(Bank.getAmount()));


        }else if(v.getId() ==R.id.btnNewGame){
            // do stuff
        }else if(v.getId() ==R.id.btnRunSlot){
            //do stuff
        }
    }

    public static class Bank{
        private static int amount;

        public static void setAmount(int amt){
            amount = amt;
        }
        public static int getAmount(){
            return amount;
        }


    }
}
