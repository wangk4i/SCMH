package com.hyd.centermq.utils;


/**
 * Created by xieshuai on 2020/6/2 10:20
 * 布隆过滤器。(由二维向量组成的hash，只支持添加。用于判断item是否一定不在该队列中)
 */
public class BloomFilter {

    /**   * 数组长度   */
    private int size;
    /**   * 数组   */
    private int[] array;

    public BloomFilter(int size) {
        this.size = size;
        this.array = new int[size];
    }
    /**   * 添加数据   */
    public void add(String item) {
        int firstIndex = func1(item);
        int secondIndex = func2(item);
        array[firstIndex % size] = 1;
        array[secondIndex % size] = 1;
    }
    /**   * 判断数据 item 是否存在集合中   */
    public boolean contains(String item) {
        int firstIndex = func1(item);
        int secondIndex = func2(item);
        int firstValue = array[firstIndex % size];
        int secondValue = array[secondIndex % size];
        return firstValue != 0 && secondValue != 0;
    }
    /**   * hash 算法 func1   */
    private int func1(String key) {
        int hash = 7;
        hash += 61 * hash + key.hashCode();
        hash ^= hash >> 15;
        hash += hash << 3;
        hash ^= hash >> 7;
        hash += hash << 11;
        return Math.abs(hash);
    }
    /**   * hash 算法 func2   */
    private int func2(String key) {
        int hash = 7;
        for (int i = 0, len = key.length(); i < len; i++) {
            hash += key.charAt(i);
            hash += (hash << 7);
            hash ^= (hash >> 17);
            hash += (hash << 5);
            hash ^= (hash >> 13);
        }
        return Math.abs(hash);
    }
}
