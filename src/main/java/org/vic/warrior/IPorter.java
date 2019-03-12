package org.vic.warrior;

/**
 * 接口封装对象数据结构转化的算法逻辑
 * 由Teleporter封装使用
 *
 * @author vicdor
 * @create 2017-11-14 19:06
 */
public interface IPorter<F, T> {
    /**
     * 实现一种由F到T的数据处理
     *
     * @param f
     * @return
     */
    T transferF2T(F f);
}
