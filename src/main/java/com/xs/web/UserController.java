package com.xs.web;

import com.xs.domain.TokenModel;
import com.xs.service.LongTermTaskCallback;
import com.xs.service.impl.LongTimeAsyncCallService;
import com.xs.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by xiaosong on 2017/3/7.
 */
@RestController
public class UserController {
    @Autowired
    LongTimeAsyncCallService longTimeAsyncCallService;

    @RequestMapping("/login")
    public TokenModel login(String name, String psw, HttpServletResponse resp) {
        TokenModel tokenModel = new TokenModel();
        tokenModel.setUserId(1l);
        String token = UUID.randomUUID().toString().replace("-", "");
        tokenModel.setToken(token);
        resp.addHeader("Token_Parm", "" + System.currentTimeMillis() + RandomUtils.getSix(1, 999999));
        return tokenModel;
    }



    @RequestMapping(value = "/longtimetask", method = RequestMethod.GET)
    public WebAsyncTask longTimeTask() {
        System.out.println("/longtimetask被调用 thread id is : " + Thread.currentThread().getId());
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(3000); //假设是一些长时间任务
                System.out.println("执行成功 thread id is : " + Thread.currentThread().getId());
                return "执行成功";
            }
        };
        WebAsyncTask asyncTask = new WebAsyncTask(2000, callable);
        asyncTask.onTimeout(
                new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("执行超时 thread id is ：" + Thread.currentThread().getId());
                        return "执行超时";
                    }
                }
        );
        return new WebAsyncTask(3000, callable);
    }

    @RequestMapping(value="/asynctask", method = RequestMethod.GET)
    public DeferredResult<String> asyncTask(){
        DeferredResult deferredResult =new DeferredResult(5000L,"请求已经超过2秒了！");
        deferredResult.setResult("原始值setResult");
        System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
        longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {
            @Override
            public void callback(Object result) {
                System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
                //通过回调接口讲异步任务传过来
                System.out.println("执行完结果： "+result);
                deferredResult.setResult(result);
            }
        });
        return  deferredResult;
    }
}
