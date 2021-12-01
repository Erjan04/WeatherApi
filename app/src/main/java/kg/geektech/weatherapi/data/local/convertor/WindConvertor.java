package kg.geektech.weatherapi.data.local.convertor;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapi.data.models.Wind;

public class WindConvertor {

    @TypeConverter
    public String fromMainString(Wind wind){
        if (wind == null){
            return null;
        }
    Gson gson = new Gson();
    Type type = new TypeToken<Wind>() {}.getType();
    return gson.toJson(wind,type);
    }
    @TypeConverter
    public Wind fromMainString(String windString){
        if (windString == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {}.getType();
        return gson.fromJson(windString,type);
    }


}
