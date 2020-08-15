package com.hyd.responsexml.file;

import com.hyd.responsexml.listener.FileMonitor;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by xieshuai on 2020/8/8 上午 11:09
 */
@Component
public class FileListenerFactory {

    @Value("${config.listener-success-folder}")
    private String successFolder;

    @Value("${config.listener-error-folder}")
    private String errorFolder;

    // 设置轮询间隔
    private final long interval = TimeUnit.SECONDS.toMillis(1);

    // 自动注入业务服务
    @Autowired
    private FileMonitor listenerService;

    public FileAlterationMonitor getMonitor() {
        // 创建过滤器
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".txt"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);

        // 装配过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(successFolder), filter);
        FileAlterationObserver observer2 = new FileAlterationObserver(new File(errorFolder), filter);


        // 向监听者添加监听器，并注入业务服务
        observer.addListener(new FileListener(listenerService));

        // 返回监听者
        return new FileAlterationMonitor(interval, observer, observer2);
    }
}
