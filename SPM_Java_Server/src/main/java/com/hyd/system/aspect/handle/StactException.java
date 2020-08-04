package com.hyd.system.aspect.handle;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by xieshuai on 2019/8/16 14:42
 * 获取异常堆栈信息工具类
 */
public class StactException {


    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
