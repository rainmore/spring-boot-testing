package hello;

import java.io.Serializable;

public class Model implements Serializable {
    private String name;
    private String code;

    public Model(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return String.format("%s, %s", name, code);
    }
}
