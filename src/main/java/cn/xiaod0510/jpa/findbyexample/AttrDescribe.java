package cn.xiaod0510.jpa.findbyexample;

import java.util.Arrays;

/**
 * 属性描述
 * Created by xiaod0510@gmail.com on 16-11-30 上午10:33.
 */
public class AttrDescribe<C, T extends BaseExample> {
    protected String name;
    protected T condiction;

    public AttrDescribe() {
    }

    public AttrDescribe(String name) {
        this.name = name;
    }

    public AttrDescribe(String name, Object condiction) {
        this.condiction = (T) condiction;
        this.name = name;
    }

    public T isNull() {
        condiction.add(name, BaseExample.PredicateType.isNull, null);
        return (T) condiction;
    }

    public T eq(C value) {
        condiction.add(name, BaseExample.PredicateType.eq, value);
        return (T) condiction;
    }

    public T gt(C value) {
        condiction.add(name, BaseExample.PredicateType.gt, value);
        return (T) condiction;
    }

    public T lt(C value) {
        condiction.add(name, BaseExample.PredicateType.lt, value);
        return (T) condiction;
    }

    public T in(C... value) {
        condiction.add(name, BaseExample.PredicateType.in, Arrays.asList(value));
        return (T) condiction;
    }

    public T like(C... value) {
        StringBuffer stringBuffer = new StringBuffer();
        for (C c : value) {
            stringBuffer.append(c);
        }
        condiction.add(name, BaseExample.PredicateType.like, stringBuffer.toString());
        return (T) condiction;
    }

    public T startsWith(C value) {
        condiction.add(name, BaseExample.PredicateType.like, value + "%");
        return (T) condiction;
    }

    public T endsWith(C value) {
        condiction.add(name, BaseExample.PredicateType.like, "%" + value);
        return (T) condiction;
    }

    public T between(Comparable<C> from, Comparable<C> to) {
        condiction.add(name, BaseExample.PredicateType.between, new Comparable[]{from, to});
        return (T) condiction;
    }

    public T isNull(C value) {
        condiction.add(name, BaseExample.PredicateType.isNull, value);
        return (T) condiction;
    }

    public T notEq(C value) {
        condiction.add(name, BaseExample.PredicateType.notEq, value);
        return (T) condiction;
    }

    public T notLike(C value) {
        condiction.add(name, BaseExample.PredicateType.notLike, value);
        return (T) condiction;
    }

    public T isNotNull(C value) {
        condiction.add(name, BaseExample.PredicateType.isNotNull, value);
        return (T) condiction;
    }

    public T notIn(C value) {
        condiction.add(name, BaseExample.PredicateType.notIn, value);
        return (T) condiction;
    }

    public T gteq(C value) {
        condiction.add(name, BaseExample.PredicateType.gtEq, value);
        return (T) condiction;
    }

    public T lteq(C value) {
        condiction.add(name, BaseExample.PredicateType.ltEq, value);
        return (T) condiction;
    }
}
