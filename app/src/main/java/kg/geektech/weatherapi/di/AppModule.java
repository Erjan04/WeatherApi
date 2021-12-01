package kg.geektech.weatherapi.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.weatherapi.data.local.AppDatabase;
import kg.geektech.weatherapi.data.local.RoomClient;
import kg.geektech.weatherapi.data.local.WeatherDao;
import kg.geektech.weatherapi.data.remote.RetrofitClient;
import kg.geektech.weatherapi.data.remote.WeatherApi;
import kg.geektech.weatherapi.data.repository.MainRepository;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static WeatherApi provideApi(){
        return new RetrofitClient().provideApi();
    }

    @Provides
    public static MainRepository provideMainRepository(WeatherApi api, WeatherDao dao){
        return new MainRepository(api,dao);
    }
    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context){
        return new RoomClient().provideDatabase(context);
    }
    @Provides
    public static WeatherDao provideWeatherDao(AppDatabase database){
        return database.weatherDao();
    }

}
