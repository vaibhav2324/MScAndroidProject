package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;


public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private LinearLayout color_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.instructions) ;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        color_change = (LinearLayout)findViewById(R.id.color_viewer);
        color_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });
        }

        public void openDialog(){
            InstructionDialog dialogFragment = new InstructionDialog();
            dialogFragment.show(getSupportFragmentManager(),"Instruction Dialog Box");
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

