package com.hyd.resultdeal.listener;

import java.io.File;
import java.io.FileFilter;

public class FileFilterImpl implements FileFilter {
    @Override
    public boolean accept(File file) {
        return true;
    }
}
