package com.example.projects.creditcard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputLayout cardNumber, date, code, fName, lName;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardNumber = findViewById(R.id.cardNumber);
        date = findViewById(R.id.date);
        code = findViewById(R.id.code);
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        submit = findViewById(R.id.submit);

        String datee = date.getEditText().getText().toString();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidNumber() && isValidCVV() && isValidFName() && isValidLName() ){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create(); //Read Update
                    alertDialog.setMessage("Payment Successful");

                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                }
            }
        });

    }

    public boolean isValidNumber(){
        String cNumber = cardNumber.getEditText().getText().toString();
        if(cNumber.isEmpty()){
            cardNumber.setError("Field cannot be empty");
            return false;
        }else if((cNumber.length() >= 13 && cNumber.length() <= 16) && (cNumber.startsWith("4") || cNumber.startsWith("5") ||
                cNumber.startsWith("34") || cNumber.startsWith("37") || cNumber.startsWith("6")) && validNumber(cNumber)){
                cardNumber.setError(null);
                cardNumber.setErrorEnabled(false);
                return true;
        }else {
                cardNumber.setError("Something wrong with Card number");
                return false;
            }
        }

    public boolean validNumber(String number){
        int [] credit = new int[number.length()];
        for(int i=0; i<number.length(); i++){
            credit[i] = Integer.parseInt(number.substring(i, i+1));
        }
        for(int i=credit.length -2; i>=0; i=i-2){
            int tempValue = credit[i];
            tempValue = tempValue*2;
            if(tempValue>9){
                tempValue = tempValue % 10 + 1;
            } credit[i] = tempValue;
        }
        int total =0;
        for(int i=0; i<credit.length; i++){
            total += credit[i];
        }
        if(total % 10 ==0){
            return true;
        }else {
            return false;
        }
    }

    public boolean isValidCVV(){
        String cvv = code.getEditText().getText().toString();
        String match = "^[0-9]{3,4}$";
        if (cvv.isEmpty()) {
            code.setError("Field cannot be empty");
            return false;
        }
        else if(!cvv.matches(match)){
            code.setError("CVV is invalid");
            return false;
        }else{
            code.setError(null);
            code.setErrorEnabled(false);
            return true;
        }
    }

    public boolean isValidFName(){
        String first = fName.getEditText().getText().toString();
        String match = "[A-Z][a-z]*";
        if (first.isEmpty()) {
            fName.setError("Field cannot be empty");
            return false;
        } else if (first.contains(" ") && first.matches(match)) {
            fName.setError(null);
            fName.setErrorEnabled(false);
            return true;
        }else {
            fName.setError(null);
            fName.setErrorEnabled(false);
            return true;
        }
    }

    public boolean isValidLName(){
        String last = lName.getEditText().getText().toString();
        String match = "[A-Z][a-z]*";
        if (last.isEmpty()) {
            lName.setError("Field cannot be empty");
            return false;
        } else if (last.contains(" ") && last.matches(match)) {
            lName.setError(null);
            lName.setErrorEnabled(false);
            return true;
        }else{
            lName.setError(null);
            lName.setErrorEnabled(false);
            return true;
        }
    }
}