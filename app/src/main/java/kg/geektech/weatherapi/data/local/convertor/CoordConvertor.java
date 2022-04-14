package kg.geektech.weatherapi.data.local.convertor;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapi.data.models.Clouds;
import kg.geektech.weatherapi.data.models.Coord;

public class CoordConvertor {

    @TypeConverter
    public String fromMainString(Coord clouds){
        if (clouds == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {}.getType();
        return gson.toJson(clouds,type);
    }
    @TypeConverter
    public Coord fromMainString(String cloudString){
        if (cloudString == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {}.getType();
        return gson.fromJson(cloudString,type);
    }


}
