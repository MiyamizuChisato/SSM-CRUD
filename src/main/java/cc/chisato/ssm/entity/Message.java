package cc.chisato.ssm.entity;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private int code;
    private String message;

    public static Message success() {
        Message message = new Message();
        message.setCode(200);
        message.setMessage("处理成功");
        return message;
    }

    public static Message fail() {
        Message message = new Message();
        message.setCode(400);
        message.setMessage("处理失败");
        return message;
    }

    public Message add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    private Map<String, Object> extend = new HashMap<String, Object>();
}
