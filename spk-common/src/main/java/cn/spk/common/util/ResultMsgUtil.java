package cn.spk.common.util;

import cn.spk.common.ResultMsg;
import cn.spk.common.dict.HttpCodeEnum;

import javax.servlet.http.HttpServletResponse;

public class ResultMsgUtil {

    private String applicationName = SpringContextUtils.getApplicationContext().getApplicationName();

    public ResultMsg buildSuccess(Object data) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(HttpCodeEnum.OK.getCode());
        resultMsg.setMsg(HttpCodeEnum.OK.getMsg());
        resultMsg.setData(data);
        return resultMsg;
    }
}
