package cn.spk.data.serivce;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ICallableService {

    List<String> getCallableList() throws ExecutionException, InterruptedException;
}
