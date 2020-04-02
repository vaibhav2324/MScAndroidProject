package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;


public class MainActivity extends AppCompatActivity {
    LinearLayout color_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color_change = (LinearLayout)findViewById(R.id.color_viewer);
        Button color_picker = (Button) findViewById(R.id.med_color);
        color_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });
    }

        public void openColorPicker(){
        final ColorPicker colorPicker = new ColorPicker(this);
            ArrayList<String> colors=new ArrayList<>();
            colors.add("#258174");
            colors.add("#3C8D2F");
            colors.add("#20724F");
            colors.add("#6A3AB2");
            colors.add("#323299");
            colors.add("#800080");
            colors.add("#B79716");
            colors.add("#258174");
            colors.add("#966D37");
            colors.add("#B77231");
            colors.add("#808000");


            colorPicker.setColors(colors)
            .setColumns(5)
                    .setRoundColorButton(true)
                    .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                        @Override
                        public void onChooseColor(int position, int color) {
                            color_change.setBackgroundColor(color);
                        }

                        @Override
                        public void onCancel() {

                        }
                    }).show();
            }


        }

