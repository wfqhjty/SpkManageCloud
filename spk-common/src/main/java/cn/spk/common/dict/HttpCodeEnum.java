package cn.spk.common.dict;

public enum HttpCodeEnum {
    OK(200, "请求成功"),
    INVALID_REQUEST(400, "参数错误"),
    UNAUTHORIZED(401, "没有权限"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器发生错误");

    private Integer code;
    private String msg;

    HttpCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
