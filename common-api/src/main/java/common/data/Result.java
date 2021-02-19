package common.data;

import lombok.Data;

import java.util.HashMap;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T res;
    private HashMap<String,T> map = new HashMap<>();

    public Result() {}

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg,T res) {
        this.code = code;
        this.msg = msg;
        this.res = res;
    }

    public Result<T> data(String key, T value) {
        map.put(key,value);
        return this;
    }
}
