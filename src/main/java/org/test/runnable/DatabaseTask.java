package org.test.runnable;

import java.util.concurrent.RecursiveAction;

public class DatabaseTask extends RecursiveAction {

    private int total;
    private int pageSize;
    private int pages;

    public DatabaseTask(int total, int pageSize){
        this.total = total;
        this.pageSize = pageSize;
        this.pages = this.total % this.pageSize != 0
                ? (this.total / this.pageSize +1) : this.total / this.pageSize;
    }

    @Override
    protected void compute() {


    }
}
