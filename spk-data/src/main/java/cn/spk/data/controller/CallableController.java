package cn.spk.data.controller;

import cn.spk.data.serivce.ICallableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RequestMapping
@RestController
public class CallableController {

    @Autowired
    private ICallableService callableService;

    @PostMapping("/getCallable")
    public List<String> getCallable(@RequestBody Map map) throws ExecutionException, InterruptedException {
        List<String> callableList = callableService.getCallableList();
        return callableList;

    }
}
