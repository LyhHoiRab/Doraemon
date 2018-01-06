package org.wah.doraemon.mybatis;

import java.util.ArrayList;
import java.util.List;

public class Criteria{

    //与
    private List<Criterion> andCriterion;
    //或
    private List<Criterion> orCriterion;
    //排序
    private List<Criterion> sorts;
    //分组
    private List<Criterion> groups;
    //分页
    private Criterion limit;

    public Criteria(){
        this.andCriterion = new ArrayList<Criterion>();
        this.orCriterion = new ArrayList<Criterion>();
        this.sorts = new ArrayList<Criterion>();
        this.groups = new ArrayList<Criterion>();
    }

    public List<Criterion> getAndCriterion(){
        return andCriterion;
    }

    public List<Criterion> getOrCriterion(){
        return orCriterion;
    }

    public List<Criterion> getSorts(){
        return sorts;
    }

    public List<Criterion> getGroups(){
        return groups;
    }

    public Criterion getLimit(){
        return limit;
    }

    public Criteria and(Criterion criterion){
        andCriterion.add(criterion);
        return this;
    }

    public Criteria or(Criterion criterion){
        orCriterion.add(criterion);
        return this;
    }

    public Criteria sort(Criterion criterion){
        sorts.add(criterion);
        return this;
    }

    public Criteria groupBy(Criterion criterion){
        groups.add(criterion);
        return this;
    }

    public Criteria limit(Criterion criterion){
        limit = criterion;
        return this;
    }

    public void clear(){
        andCriterion.clear();
        orCriterion.clear();
        sorts.clear();
        groups.clear();
        limit = null;
    }
}
