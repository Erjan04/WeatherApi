package kg.geektech.weatherapi.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import kg.geektech.weatherapi.data.local.convertor.MainConvertor;
import kg.geektech.weatherapi.data.models.Weather;

@Database(entities = Weather.class,version = 1)
@TypeConverters({MainConvertor.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
