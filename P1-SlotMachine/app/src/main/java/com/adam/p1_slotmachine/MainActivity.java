package com.adam.p1_slotmachine;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if(v.getId() == R.id.btn_setVal){
            // do stuff
            EditText inputAmt = (EditText) findViewById(R.id.input_amount);

            String tempAmt = inputAmt.getText().toString();

            int amt = Integer.parseInt(tempAmt);

            if( amt < 100 || amt > 500){
                Toast.makeText(getApplicationContext(), "Amount must be between 100 and 500, inclusive.",
                        Toast.LENGTH_SHORT).show();
            }else{

                Bank.setAmount(amt);

                TextView displayAmt = (TextView)findViewById(R.id.displayBankAmount);
                displayAmt.setText("$" + Integer.toString(Bank.getAmount()));

                inputAmt.clearFocus();
                inputAmt.setEnabled(false);

                //we are in setVal button event so v should be the Set Value button
                v.setEnabled(false);

            }

        }else if(v.getId() ==R.id.btnNewGame){

            Button btnSetValue = (Button)findViewById(R.id.btn_setVal);
            btnSetValue.setEnabled(true);

            EditText inputAmt = (EditText) findViewById(R.id.input_amount);
            inputAmt.setText("");
            inputAmt.setEnabled(true);

            Bank.setAmount(0);
            TextView displayAmt = (TextView)findViewById(R.id.displayBankAmount);
            displayAmt.setText(Bank.getStrAmount());

        }else if(v.getId() ==R.id.btnRunSlot){
            //do stuff
            int slot1;
            int slot2;
            int slot3;

        }
    }
}

