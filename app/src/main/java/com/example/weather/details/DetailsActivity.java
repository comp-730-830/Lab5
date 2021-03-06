package com.example.weather.details;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.weather.R;
import com.example.weather.data.Forecast;
import com.example.weather.listView.DetailsListView;

public class DetailsActivity extends AppCompatActivity {
    public static final String FORECAST_EXTRA = "FORECAST";

    private Toolbar toolbar;
    private ImageView icon;
    private TextView description;
    private DetailsListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initViews();
        initToolbar();
        initListView();

        Forecast forecast = (Forecast) getIntent().getSerializableExtra(FORECAST_EXTRA);
        if (forecast != null) {
            bindForecast(forecast);
        }
    }

    private void initListView() {
        listView = findViewById(R.id.listView);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initViews() {
        icon = findViewById(R.id.icon);
        description = findViewById(R.id.description);
    }

    private void bindForecast(Forecast data) {
        int iconId = getResources().getIdentifier(data.getIcon(), "drawable", getPackageName());
        icon.setImageResource(iconId);
        description.setText(data.getDescription());
        toolbar.setTitle(data.getDateString("EE, MMM dd"));
        listView.show(data.getDetails());
    }
}
