package org.website_visit_counter.services;

import org.website_visit_counter.interfaces.IHitCounterService;
import org.website_visit_counter.models.PageVisitCounter;

public class HitCounterService implements IHitCounterService {
    private final PageVisitCounter[] counters;

    public HitCounterService(int totalPages) {
        this.counters = new PageVisitCounter[totalPages];
        initializeCounters(totalPages);
    }

    private void initializeCounters(int totalPages) {
        for (int i = 0; i < totalPages; i++) {
            counters[i] = new PageVisitCounter(i);
        }
    }

    @Override
    public void incrementVisitCount(int pageIndex) {
        validatePageIndex(pageIndex);
        counters[pageIndex].increment();
    }

    @Override
    public int getVisitCount(int pageIndex) {
        validatePageIndex(pageIndex);
        return counters[pageIndex].getCount();
    }

    private void validatePageIndex(int pageIndex) {
        if (pageIndex < 0 || pageIndex >= counters.length) {
            throw new IllegalArgumentException("Invalid page index: " + pageIndex);
        }
    }
}
