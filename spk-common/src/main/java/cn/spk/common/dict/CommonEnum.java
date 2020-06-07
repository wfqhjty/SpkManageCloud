package cn.spk.common.dict;

public class CommonEnum {

    public enum ReturnMsgEnum {

        SUCCESS("请求成功"), ERROR("请求失败");

        private String value;

        ReturnMsgEnum(String value) {
            this.value=value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
