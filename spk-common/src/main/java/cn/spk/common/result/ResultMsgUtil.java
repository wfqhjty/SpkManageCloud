package cn.spk.common.result;

public class ResultMsgUtil {

    public ResultMsg buildSuccess(Object data) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(HttpCodeEnum.OK.getCode());
        resultMsg.setMsg(HttpCodeEnum.OK.getMsg());
        resultMsg.setData(data);
        return resultMsg;
    }

    public ResultMsg buildFail(Object data){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(HttpCodeEnum.FAIL.getCode());
        resultMsg.setMsg(HttpCodeEnum.FAIL.getMsg());
        resultMsg.setData(data);
        return resultMsg;
    }
}
