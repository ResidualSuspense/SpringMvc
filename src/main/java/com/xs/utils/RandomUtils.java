package com.xs.utils;

import org.springframework.util.StringUtils;

/**
 * Created by xiaosong on 2017/3/7.
 */
public class RandomUtils {
    public static int getSix(int min, int max) {
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return randNum;
    }
}
