package com.example.passwordapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView titleText,passwordGenText,passwordCheckText;

    private Button buttonGen,buttonCheck,buttonGenHistory;

    private EditText  textInputCheck;

    char[] letters ={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    char[] lettersUp ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    char[] numbers={'0','1','2','3','4','5','6','7','8','9'};

    char[] symbols ={'!','@','#','$','%','%','^','&','*','(',')','+','-','_','='};

    int passwordLength = 12;

    Random random = new Random();

    String[] PassListArr = new String[11];

    int h = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText =findViewById(R.id.titleText);

        passwordGenText =findViewById(R.id.passwordGenText);

        passwordCheckText =findViewById(R.id.passwordCheckText);

        buttonCheck=findViewById(R.id.buttonCheck);

        buttonGen=findViewById(R.id.buttonGen);

        buttonGenHistory=findViewById(R.id.buttonGenHistory);

        textInputCheck=findViewById(R.id.textInputCheck);

        for (int k = 0; k < PassListArr.length; k++){
            PassListArr[k] = "...";
        }

        buttonGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                char[] password = new char[passwordLength];
                for (int i =0; i < passwordLength;i++) {
                    int arrayIndex = random.nextInt(4);
                    switch (arrayIndex) {

                        case 0:
                            int letterIndex = random.nextInt(letters.length);
                            password[i] =letters[letterIndex];
                            break;

                        case 1:
                            int letterUpIndex = random.nextInt(lettersUp.length);
                            password[i] =lettersUp[letterUpIndex];
                            break;

                        case 2:
                            int numberIndex = random.nextInt(numbers.length);
                            password[i] =numbers[numberIndex];
                            break;

                        case 3:
                            int symbolIndex = random.nextInt(symbols.length);
                            password[i] =symbols[symbolIndex];
                            break;


                    }
                }

                String pass = "";

                for (char c : password) {
                    pass += c;
                }

                passwordGenText.setText(String.valueOf(pass));


                if (h > 10) {

                    h = 0;

                } else {
                    PassListArr[h] = (h+1) + ")" + pass;
                    h+=1;

                }
            }
        });

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass =String.valueOf(textInputCheck.getText());

                  if (pass.contains(" ")){
                    passwordCheckText.setText(String.valueOf("В пароле присутсвует пробелы \uD38D\uDED1"));

                  } else if (pass.matches(".*[а-яА-Я].*")){
                          passwordCheckText.setText(String.valueOf("В пароле присутсвует кириллица \uD38D\uDED1"));

                } else if (pass.length() <=8){
                    passwordCheckText.setText(String.valueOf("В пароле менее 8 символов \uD38D\uDED1"));

                } else if (pass.length() >=16){
                    passwordCheckText.setText(String.valueOf("В пароле более 16 символов \uD38D\uDED1"));

                } else if (!pass.matches(".*[A-Z].*")){
                    passwordCheckText.setText(String.valueOf("В пароле нет заглавных букв \uD38D\uDED1"));



                } else if (!pass.matches(".*\\d.*")){
                    passwordCheckText.setText(String.valueOf("В пароле нет цифр \uD38D\uDED1"));
                } else if (!containsSymbols(pass,symbols)){
                    passwordCheckText.setText(String.valueOf("В пароле нет спецсимволов \uD38D\uDED1"));
                } else {
                    passwordCheckText.setText(String.valueOf("Надёжный пароль \uD38D\uDC4C"));
                }
            }
        });

        buttonGenHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PassHistory.class);
                intent.putExtra("pl", PassListArr);
                startActivity(intent);
            }
        });


    }

    public static boolean containsSymbols(String str, char[] symbols) {
        for (char symbol : symbols) {
            if (str.indexOf(symbol) != -1) {
                return true;
            }
        }
        return false;
    }

}
