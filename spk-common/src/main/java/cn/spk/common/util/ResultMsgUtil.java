package cn.spk.common.util;

import cn.spk.common.ResultMsg;
import cn.spk.common.dict.CommonEnum;
import cn.spk.common.dict.Constant;

import javax.servlet.http.HttpServletResponse;

public class ResultMsgUtil {

    private String applicationName = SpringContextUtils.getApplicationContext().getApplicationName();

    public ResultMsg buildSuccess(Object data) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(HttpServletResponse.SC_OK);
        resultMsg.setMsg(CommonEnum.ReturnMsgEnum.SUCCESS.getValue());
        resultMsg.setData(data);
        return resultMsg;
    }
}
