package io.choerodon.testng.config.domain;

/**
 * @author dinghuang123@gmail.com
 * @since 2019/1/25
 */
public class BodyMatcherExtend {

    private String key;

    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BodyMatcherExtend{" + "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
