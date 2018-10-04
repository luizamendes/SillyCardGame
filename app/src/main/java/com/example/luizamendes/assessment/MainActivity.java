package com.example.luizamendes.assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.w3c.dom.Text;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private ImageView option1;
    private ImageView option2;
    private ImageView option3;
    private Button confirm_btn;
    private Button again_btn;
    private TextView feedback_tv;
    private ImageView selected_card;
    private boolean isValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        option1 = findViewById(R.id.option1_iv);
        option2 = findViewById(R.id.option2_iv);
        option3 = findViewById(R.id.option3_iv);
        confirm_btn = findViewById(R.id.confirm_btn);
        again_btn = findViewById(R.id.again_btn);
        again_btn.setVisibility(View.GONE);
        feedback_tv = findViewById(R.id.feedback_tv);
        isValid = true;

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid) {
                    option1.setAlpha(1.0f);
                    option2.setAlpha(0.5f);
                    option3.setAlpha(0.5f);
                    selected_card = option1;
                    clearFeedback();
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid) {
                    option2.setAlpha(1.0f);
                    option1.setAlpha(0.5f);
                    option3.setAlpha(0.5f);
                    selected_card = option2;
                    clearFeedback();
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid) {
                    option2.setAlpha(0.5f);
                    option1.setAlpha(0.5f);
                    option3.setAlpha(1.0f);
                    selected_card = option3;
                    clearFeedback();
                }
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected_card == null) {
                    feedback_tv.setText("Você deve selecionar uma carta!");
                    return;
                }
                if (isValid) {
                    int result = chooseCard();
                    if (result == 1) {
                        selected_card.setImageResource(R.drawable.cardright);
                        feedback_tv.setText("Você acertou!");
                    } else {
                        selected_card.setImageResource(R.drawable.cardjoker);
                        feedback_tv.setText("Você escolheu o coringa! Perdeu!");
                    }
                    again_btn.setVisibility(View.VISIBLE);
                    isValid = false;
                }
            }
        });

        again_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option1.setImageResource(R.drawable.cardback);
                option2.setImageResource(R.drawable.cardback);
                option3.setImageResource(R.drawable.cardback);
                again_btn.setVisibility(View.GONE);
                isValid = true;
                selected_card.setAlpha(0.5f);
                clearFeedback();
                selected_card = null;
            }
        });
    }

    private int chooseCard() {
        Random rn = new Random();
        int range = 3;
        int randomNum =  rn.nextInt(range) + 1;
        return randomNum;
    }

    private void clearFeedback() {
        feedback_tv.setText("");
    }
}
