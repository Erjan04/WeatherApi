package kg.geektech.weatherapi.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.weatherapi.data.local.AppDatabase;
import kg.geektech.weatherapi.data.local.WeatherDao;
import kg.geektech.weatherapi.data.remote.RetrofitClient;
import kg.geektech.weatherapi.data.remote.WeatherApi;
import kg.geektech.weatherapi.data.repository.MainRepository;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Singleton
    @Provides
    public static WeatherApi provideApi() {
        return new RetrofitClient().provideApi();
    }

    @Singleton
    @Provides
    public static MainRepository provideMainRepository(WeatherApi api, WeatherDao dao) {
        return new MainRepository(api, dao);
    }

    @Singleton
    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "weather_database"
        )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public static WeatherDao provideWeatherDao(AppDatabase database) {
        return database.weatherDao();
    }


}
