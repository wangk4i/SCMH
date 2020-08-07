package com.hyd.resultdeal;

import com.hyd.resultdeal.listener.FileFilterImpl;
import com.hyd.resultdeal.listener.FileListener;
import com.hyd.resultdeal.service.FileDealService;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/6 11:03
 */
public class ListenerTest {
    public static void main(String[] args){
        try {
            String path = "D:\\mq\\exchange\\04received";
            // 构造观察类
            FileAlterationObserver observer = new FileAlterationObserver(path, new FileFilterImpl());
            // 构造收听类
            FileListener listener = new FileListener(new FileDealService());
            // 为观察对象添加收听对象
            observer.addListener(listener);
            // 配置Monitor，第一个参数是毫秒，监听间隔；第二个参数是绑定之前的观察对象
            FileAlterationMonitor fileMonitor = new FileAlterationMonitor(10000,
                    new FileAlterationObserver[]{observer});
            // 启动开始监听
            System.out.println("开始监听");
            fileMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
