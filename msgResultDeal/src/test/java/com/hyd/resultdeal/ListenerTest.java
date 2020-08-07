package com.hyd.resultdeal;

import com.hyd.resultdeal.listener.FileFilterImpl;
import com.hyd.resultdeal.listener.FileListener;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ListenerTest {
    @Test
    void contextLoads() {

    }
}
