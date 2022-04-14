package kg.geektech.weatherapi.ui.weatherFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapi.R;
import kg.geektech.weatherapi.base.BaseFragment;
import kg.geektech.weatherapi.data.local.WeatherDao;
import kg.geektech.weatherapi.data.models.Main;
import kg.geektech.weatherapi.data.models.Sys;
import kg.geektech.weatherapi.data.models.Weather;
import kg.geektech.weatherapi.data.models.Weather__1;
import kg.geektech.weatherapi.data.models.Wind;
import kg.geektech.weatherapi.databinding.FragmentWeatherBinding;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherViewModel model;
    private Wind wind;
    private Main main;
    private WeatherFragmentArgs args;
    private Weather weather;
    private Sys sys;
    private ArrayList<Weather__1> weather__1s = new ArrayList<>();
    @Inject
    WeatherDao dao;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            args = WeatherFragmentArgs.fromBundle(getArguments());
    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected void setupObservers() {
        observeModel();
        observeWeather();
    }

    private void observeModel() {
        model = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        model.fetchTemp(args.getLongitude(), args.getLatitude());
    }

    private void observeWeather() {
        model.tempLiveData.observe(getViewLifecycleOwner(), response -> {
            switch (response.status) {
                case SUCCESS:
                    wind = response.data.getWind();
                    weather = response.data;
                    sys = response.data.getSys();
                    main = response.data.getMain();
                    weather__1s = (ArrayList<Weather__1>) response.data.getWeather();
                    binding.progress.setVisibility(View.GONE);
                    setWeather();
                    break;

                case LOADING:
                    binding.progress.setVisibility(View.VISIBLE);
                    break;

                case ERROR:
                    Toast.makeText(requireContext(), "Internet not connected!Loading last data", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                    wind = dao.getWeather().getWind();
                    main = dao.getWeather().getMain();
                    sys = dao.getWeather().getSys();
                    weather = dao.getWeather();
                    weather__1s = (ArrayList<Weather__1>) dao.getWeather().getWeather();
                    setWeather();
                    break;
            }
        });
    }

    @Override
    protected void setupUI() {
        binding.cityBtn.setOnClickListener(v
                -> navController.navigate(R.id.action_weatherFragment_to_findFragment));
    }

    @SuppressLint("SetTextI18n")
    private void setWeather() {
        binding.weatherNowTv.setText(weather__1s.get(0).getMain());
        Glide.with(requireContext())
                .load("https://openweather0map.org/img/wn/" + weather__1s.get(0).getIcon() + ".png")
                .override(100, 100)
                .into(binding.weatherIv);
        binding.tempmaxTv.setText(String.valueOf((int) Math.round(main.getTempMax())));
        binding.windTv.setText((int) Math.round(wind.getSpeed()) + " m/ s");
        binding.cityBtn.setText(weather.getName());
        binding.tempnowTv.setText(String.valueOf((int) Math.round(main.getTemp())));
        binding.barometrTv.setText(main.getPressure() + "mBar");
        binding.textViewHumidity.setText(main.getHumidity() + "%");
        binding.tempminTv.setText(String.valueOf((int) Math.round(main.getTempMin())));
    }
}
