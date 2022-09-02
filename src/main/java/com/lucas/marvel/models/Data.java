package com.lucas.marvel.models;

import java.util.List;

public class Data {

    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<Chars> results;

    public List<Integer> ids;
    public Data() {

    }

    public int getOffset() {
        return offset;
    }

    public void setOffset() {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Chars> getResults() {
        return results;
    }

    public void setResults(List<Chars> results) {
        this.results = results;
    }

    public List<Integer> getIDResults() {
        return ids;
    }
    public void setIDResults(List<Integer> ids){
        this.ids = ids;
    }
}
