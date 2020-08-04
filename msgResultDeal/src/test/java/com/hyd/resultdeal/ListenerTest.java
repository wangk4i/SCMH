package com.hyd.resultdeal;

import com.hyd.resultdeal.listener.FileFilterImpl;
import com.hyd.resultdeal.listener.FileListener;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileFilter;
import java.util.ArrayList;

@SpringBootTest
public class ListenerTest {
    @Test
    void contextLoads() {
        /*try{
            FileAlterationObserver observer = new FileAlterationObserver("D:\\mq\\exchange\\04received", new FileFilterImpl());

            FileListener
        }*/

        System.out.println(new ArrayList<>());

    }
}
