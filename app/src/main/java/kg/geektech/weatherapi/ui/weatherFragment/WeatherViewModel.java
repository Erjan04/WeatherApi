package kg.geektech.weatherapi.ui.weatherFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapi.common.Resource;
import kg.geektech.weatherapi.data.models.Weather;
import kg.geektech.weatherapi.data.repository.MainRepository;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final MainRepository repository;
    public LiveData<Resource<Weather>> tempLiveData;

    @Inject
    public WeatherViewModel(MainRepository repository){
        this.repository = repository;
    }
    public void fetchTemp(String lat,String lon){
         tempLiveData = repository.getTemp(lat,lon);
    }
}
