package kg.geektech.weatherapi.data.local.convertor;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import kg.geektech.weatherapi.data.models.Weather;

public class WeatherConvertor {

    @TypeConverter
    public String fromMainString(Weather weather){
        if (weather == null){
            return null;
        }
    Gson gson = new Gson();
    Type type = new TypeToken<Weather>() {}.getType();
    return gson.toJson(weather,type);
    }
    @TypeConverter
    public String fromMainString(String dataString){
        if (dataString == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Weather>() {}.getType();
        return gson.toJson(dataString,type);
    }


}
