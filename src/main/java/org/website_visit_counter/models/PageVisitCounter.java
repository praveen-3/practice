package org.website_visit_counter.models;

import java.util.concurrent.atomic.AtomicInteger;

import org.website_visit_counter.interfaces.IVisitCounter;

public class PageVisitCounter implements IVisitCounter {
    private final AtomicInteger visitCount;
    private final int pageIndex;

    public PageVisitCounter(int pageIndex) {
        this.pageIndex = pageIndex;
        this.visitCount = new AtomicInteger(0);
    }

    @Override
    public void increment() {
        visitCount.incrementAndGet();
    }

    @Override
    public int getCount() {
        return visitCount.get();
    }

    public int getPageIndex() {
        return pageIndex;
    }
}
