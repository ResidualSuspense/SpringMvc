package com.xs.service.impl;

import com.xs.service.LongTermTaskCallback;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaosong on 2017/3/11.
 * 耗时任务
 */
@Service
public class LongTimeAsyncCallService {
    private final int CorePoolSize = 4;
    private final int NeedSeconds = 3;
    private Random random = new Random();

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(CorePoolSize);

    public void makeRemoteCallAndUnknownWhenFinish(LongTermTaskCallback callback){
        System.out.println("完成此任务需要 : " + NeedSeconds + " 秒");
      scheduledExecutorService.schedule(new Runnable() {
          @Override
          public void run() {

              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              callback.callback("我是异步任务结果！");
          }
      },0,TimeUnit.SECONDS);
    }

}
