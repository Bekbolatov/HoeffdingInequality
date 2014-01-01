package com.sparkydots.ml.ml1.data;
import java.util.HashMap;
import java.util.Map;


public class TableOfDoubles {
    private Map<String, Double> _data;
    public TableOfDoubles() {
        _data = new HashMap<String, Double>();
    }
    public void setArg(String k, double v) {
        setArg(k, new Double(v));
    }
    public void setArg(String k, Double v) {
        _data.put(k, v);
    }
    public Double getArg(String k) {
        return _data.get(k);
    }
}
