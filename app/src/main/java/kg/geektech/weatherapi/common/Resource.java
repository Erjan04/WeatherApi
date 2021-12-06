package kg.geektech.weatherapi.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<R> {

    @NonNull
    public final Status status;
    public final R data;
    public final String msg;

    public Resource(@NonNull Status status, R data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static <R> Resource<R> success(R data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <R> Resource<R> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }

    public static <R> Resource<R> error(String msg, @Nullable R data) {
        return new Resource<>(Status.ERROR, data, msg);
    }


    public enum Status {
        SUCCESS,
        LOADING,
        ERROR
    }
}
