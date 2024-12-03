package com.example.aplikasipahlawan;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi ListView
        ListView lvdata1 = findViewById(R.id.lvdata);

        // Membuat list daftar nama pahlawan
        ArrayList<String> daftarpahlawan = new ArrayList<>();
        daftarpahlawan.add("Soekarno");
        daftarpahlawan.add("Mohammad Hatta");
        daftarpahlawan.add("Sudirman");
        daftarpahlawan.add("Pattimura");
        daftarpahlawan.add("Imam Bonjol");


        Map<String, Class<?>> activityMap = new HashMap<>();
        activityMap.put("Soekarno", Soekarno.class);
        activityMap.put("Mohammad Hatta", MohHatta.class);
        activityMap.put("Sudirman", Sudirman.class);
        activityMap.put("Pattimura", Pattimura.class);
        activityMap.put("Imam Bonjol", ImamBonjol.class);

        // Buat adapter untuk menghubungkan data ke ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, daftarpahlawan
        );

        // Menghubungkan ListView ke adapter
        lvdata1.setAdapter(adapter);

        lvdata1.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = daftarpahlawan.get(position);
            if (activityMap.containsKey(selectedItem)) {
                Toast.makeText(MainActivity.this, "Anda memilih: " + selectedItem, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, activityMap.get(selectedItem));
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Anda tidak memilih", Toast.LENGTH_SHORT).show();
            }
        });
    }
}