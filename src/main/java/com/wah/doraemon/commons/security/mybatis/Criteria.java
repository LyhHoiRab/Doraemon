package com.wah.doraemon.commons.security.mybatis;

import java.util.ArrayList;
import java.util.List;

public class Criteria{

    //与
    private List<Criterion> andCreiterions;
    //或
    private List<Criterion> orCreiterions;
    //排序
    private List<Criterion> sorts;
    //分组
    private List<Criterion> groups;
    //分页
    private Criterion limit;

    public Criteria(){
        this.andCreiterions = new ArrayList<Criterion>();
        this.orCreiterions = new ArrayList<Criterion>();
        this.sorts = new ArrayList<Criterion>();
        this.groups = new ArrayList<Criterion>();
    }

    public List<Criterion> getAndCreiterions(){
        return andCreiterions;
    }

    public void setAndCreiterions(List<Criterion> andCreiterions){
        this.andCreiterions = andCreiterions;
    }

    public List<Criterion> getOrCreiterions(){
        return orCreiterions;
    }

    public void setOrCreiterions(List<Criterion> orCreiterions){
        this.orCreiterions = orCreiterions;
    }

    public List<Criterion> getSorts(){
        return sorts;
    }

    public void setSorts(List<Criterion> sorts){
        this.sorts = sorts;
    }

    public Criterion getLimit(){
        return limit;
    }

    public void setLimit(Criterion limit){
        this.limit = limit;
    }

    public List<Criterion> getGroups(){
        return groups;
    }

    public void setGroups(List<Criterion> groups){
        this.groups = groups;
    }

    public Criteria and(Criterion criterion){
        andCreiterions.add(criterion);
        return this;
    }

    public Criteria or(Criterion criterion){
        orCreiterions.add(criterion);
        return this;
    }

    public Criteria sort(Criterion criterion){
        this.sorts.add(criterion);
        return this;
    }

    public Criteria limit(Criterion criterion){
        this.limit = criterion;
        return this;
    }

    public Criteria groupBy(Criterion criterion){
        this.groups.add(criterion);
        return this;
    }

    public void clear(){
        this.andCreiterions.clear();
        this.orCreiterions.clear();
        this.sorts.clear();
        this.groups.clear();
        this.limit = null;
    }
}
