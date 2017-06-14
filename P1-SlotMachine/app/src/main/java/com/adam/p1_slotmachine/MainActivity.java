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

        btnRunSlot.setEnabled(false);

        btnSetValue.setOnClickListener(this);
        btnNewGame.setOnClickListener(this);
        btnRunSlot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btn_setVal){
            //Set Value Functionality

            EditText inputAmt = (EditText) findViewById(R.id.input_amount);
            String tempAmt = inputAmt.getText().toString();
            int amt = Integer.parseInt(tempAmt);

            if( amt < 100 || amt > 500){
                Toast.makeText(getApplicationContext(), "Amount must be between 100 and 500, inclusive.",
                        Toast.LENGTH_SHORT).show();
            }else{

                Bank.setAmount(amt);

                //update
                TextView displayAmt = (TextView)findViewById(R.id.displayBankAmount);
                displayAmt.setText(Bank.getStrAmount());

                Button btnRunSlot = (Button)findViewById(R.id.btnRunSlot);
                btnRunSlot.setEnabled(true);

                //disable buttons and text
                inputAmt.clearFocus();
                inputAmt.setEnabled(false);
                //we are in setVal button event so v should be the Set Value button
                v.setEnabled(false);

            }

        }else if(v.getId() ==R.id.btnNewGame){

            Button btnSetValue = (Button)findViewById(R.id.btn_setVal);

            btnSetValue.setEnabled(true);

            Button btnRunSlot = (Button)findViewById(R.id.btnRunSlot);
            btnRunSlot.setEnabled(false);

            EditText inputAmt = (EditText) findViewById(R.id.input_amount);
            inputAmt.setText("");
            inputAmt.setEnabled(true);

            Bank.setAmount(0);
            TextView displayAmt = (TextView)findViewById(R.id.displayBankAmount);
            displayAmt.setText(Bank.getStrAmount());

        }else if(v.getId() ==R.id.btnRunSlot){
            //Pull the lever
            Bank.subtract(5);

            TextView displayAmt = (TextView)findViewById(R.id.displayBankAmount);
            displayAmt.setText(Bank.getStrAmount());

            SlotMachine.pullLever();

            TextView dispSlot1 = (TextView)findViewById(R.id.slot1);
            TextView dispSlot2 = (TextView)findViewById(R.id.slot2);
            TextView dispSlot3 = (TextView)findViewById(R.id.slot3);

            int slot1Val = SlotMachine.getSlot1();
            int slot2Val = SlotMachine.getSlot2();
            int slot3Val = SlotMachine.getSlot3();

            dispSlot1.setText(Integer.toString(slot1Val));
            dispSlot2.setText(Integer.toString(slot2Val));
            dispSlot3.setText(Integer.toString(slot3Val));

            //check reward. if else short circuit
            if(slot1Val == slot2Val && slot1Val == slot3Val && slot2Val == slot3Val){
                if(slot1Val == 9){
                    //get 1000
                }else if(slot1Val >= 5){
                    //get 100
                }else{
                    //get 40
                }
            }else if (slot1Val == slot2Val || slot1Val == slot3Val || slot2Val == slot3Val){
                //get $10

            }else{
                //just lose $5
            }

            if(Bank.getAmount() >= 1000){
                //win new game
            }else if(Bank.getAmount() <= 0){
                //lose. new game
            }else{
                //nothing
            }

        }
    }
}

