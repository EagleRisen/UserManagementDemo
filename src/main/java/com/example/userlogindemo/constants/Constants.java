package com.example.userlogindemo.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName Constants
 * @Description 系统级常量
 * @Author nsxconquer
 * @Date 2021/1/28 8:47 PM
 * @Version 1.0
 */
public class Constants {
    public static final String APP_NAME = "spring";

    public static final Charset CHARSET = StandardCharsets.UTF_8;

    public interface Flag {
        Integer YES = 1;
        Integer NO = 0;
    }

    public interface Operation {
        String ADD = "add";
        String UPDATE = "update";
        String DELETE = "delete";
    }

    public interface sex {
        Integer MALE = 1;
        Integer FEMALE = 0;
    }
}
