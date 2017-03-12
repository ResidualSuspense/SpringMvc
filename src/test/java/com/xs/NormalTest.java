package com.xs;

import com.xs.domain.User;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by xiaosong on 2017/3/11.
 */
public class NormalTest {
    @Test
    public void assertTest(){
        User user =null;
        Assert.notNull(user,"user 不是空");
    }
}
