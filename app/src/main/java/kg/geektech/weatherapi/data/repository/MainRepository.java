package kg.geektech.weatherapi.data.repository;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import kg.geektech.weatherapi.common.Resource;
import kg.geektech.weatherapi.data.local.WeatherDao;
import kg.geektech.weatherapi.data.models.Weather;
import kg.geektech.weatherapi.data.remote.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private String city;
    private  WeatherApi api;
    private  WeatherDao weatherDao;

    @Inject
    public MainRepository(WeatherApi weatherApi,WeatherDao dao){
        api = weatherApi;
        weatherDao = dao;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public MutableLiveData<Resource<Weather>> getTemp(){
        MutableLiveData<Resource<Weather>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getTemp(city,"c1ea15e8f360d97759f0b5a78fc32620","metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NotNull Call<Weather> call, @NotNull Response<Weather> response) {
                if (response.isSuccessful() && response.body() != null){
                    liveData.setValue(Resource.success(response.body()));
                    weatherDao.insert(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Weather> call, @NotNull Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(),null));
            }
        });
        return liveData;
    }
}
