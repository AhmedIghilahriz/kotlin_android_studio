package com.example.appli;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private List<UltrasonicData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataAdapter(dataList);
        recyclerView.setAdapter(adapter);

        new Thread(this::fetchData).start();
    }

    private void fetchData() {
        try {
            String url = "http://192.168.215.62/mes_projets/ultrason.php";
            URL csvUrl = new URL(url);
            InputStreamReader reader = new InputStreamReader(csvUrl.openStream());

            // Parse CSV data
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> parsedData = csvReader.readAll();

            // Convert CSV data to UltrasonicData objects
            for (String[] record : parsedData) {
                int id = Integer.parseInt(record[0]);
                double value = Double.parseDouble(record[1]);
                Date timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(record[2]);
                dataList.add(new UltrasonicData(id, value, timestamp));
            }

            // Update UI on the main thread
            new Handler(Looper.getMainLooper()).post(() -> {
                adapter.notifyDataSetChanged();

                // Plot the graph
                plotGraph();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void plotGraph() {
        FrameLayout plotContainer = findViewById(R.id.plotContainer);
        double[] xData = new double[dataList.size()];
        double[] yData = new double[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            xData[i] = i;
            yData[i] = dataList.get(i).getValue();
        }

        Plot.plot(plotContainer,
                Plot.scatter(xData, yData, ScatterMode.MARKERS),
                Plot.layout().xaxis().title("Index").done(),
                Plot.layout().yaxis().title("Value").done(),
                Plot.layout().title("Ultrasonic Data").done());
    }
}
