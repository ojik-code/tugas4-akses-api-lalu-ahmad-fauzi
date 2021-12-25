package com.example.tugas4api_laluahmadfauzi;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tugas4api_laluahmadfauzi.model.DataWeb;
public class DetailWeb extends AppCompatActivity {
    DataWeb web;
    ImageView logo;
    TextView titleWeb, baca_lebih_lanjut, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        web = (DataWeb) intent.getSerializableExtra("Web");
        inisialisasiView();
    }

    void inisialisasiView() {
        logo = findViewById(R.id.logo);
        titleWeb = findViewById(R.id.jenisweb);
        description = findViewById(R.id.deskripsi);
        baca_lebih_lanjut = findViewById(R.id.lihat_detail);
        titleWeb.setText(web.getNameWeb());;
        baca_lebih_lanjut.setText(web.getBaca_lebih_lanjut());
        description.setText(web.getDescription());
        Glide
                .with(this)
                .load(web.getLogo())
                .into(logo);
    }

    public void clikToDetail(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(web.getBaca_lebih_lanjut()));
        startActivity(intent);
    }
}

