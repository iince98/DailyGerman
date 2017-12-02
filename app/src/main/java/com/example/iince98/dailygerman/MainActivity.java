package com.example.iince98.dailygerman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button calisma= (Button)findViewById(R.id.btn_calisma);
        Button veriyonet= (Button)findViewById(R.id.btn_veriyonet);

        veriyonet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplication(), Veri_al.class);
                startActivity(intent);
            }
        });

        calisma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplication(), Test.class);
                startActivity(intent);
            }
        });
    }

}
