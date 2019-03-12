package org.vic.warrior;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vicdor
 * @create 2017-11-14 19:26
 */
public class Teleporter<F, T> {
    private static Teleporter teleporter;

    private Teleporter() {
    }

    /**
     * 返回一个porter
     *
     * @return
     */
    public static Teleporter newTeleporter() {
        if (teleporter != null) {
            return teleporter;
        }
        return new Teleporter();
    }

    /**
     * 将对象from按照接口定义的处理方式
     * 转化为T类型的数据结构输送出来
     *
     * @param from 原结构数据对象
     * @param iPorter 数据处理实现接口实例
     * @return
     */
    public T copyData(F from, IPorter<F, T> iPorter) {
        if (from == null) return null;
        return iPorter.transferF2T(from);
    }

    /**
     * 基于单个对象转化
     * 实现列表数据批量转化
     *
     * @param fromList 原结构数据列表
     * @param iPorter 单个对象转化处理实现接口实例
     * @return
     */
    public List<T> copyList(List<F> fromList, IPorter<F, T> iPorter) {
        if (fromList == null) return null;
        List<T> list = new ArrayList<>();
        if (fromList.size() == 0) return list;
        for (F f : fromList) {
            list.add(iPorter.transferF2T(f));
        }
        return list;
    }
}
