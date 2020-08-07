package com.hyd.subordtest.listener;

import java.io.File;
import java.io.FileFilter;

public class FileFilterImpl implements FileFilter {
    @Override
    public boolean accept(File file) {
        return file.toString().endsWith(".xml");
    }
}
