package kg.geektech.weatherapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapi.databinding.ActivityMainBinding;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}