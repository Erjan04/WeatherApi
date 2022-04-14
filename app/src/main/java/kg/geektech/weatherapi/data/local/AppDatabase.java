package kg.geektech.weatherapi.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import kg.geektech.weatherapi.data.local.convertor.CloudConvertor;
import kg.geektech.weatherapi.data.local.convertor.CoordConvertor;
import kg.geektech.weatherapi.data.local.convertor.MainConvertor;
import kg.geektech.weatherapi.data.local.convertor.SysConvertor;
import kg.geektech.weatherapi.data.local.convertor.Weather1Convertor;
import kg.geektech.weatherapi.data.local.convertor.WindConvertor;
import kg.geektech.weatherapi.data.models.Weather;

@Database(entities = {Weather.class}, version = 1)
@TypeConverters({MainConvertor.class, CoordConvertor.class,
        SysConvertor.class, Weather1Convertor.class, WindConvertor.class, CloudConvertor.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
