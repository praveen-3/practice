package org.website_visit_counter.interfaces;

public interface IHitCounterService {
    void incrementVisitCount(int pageIndex);
    int getVisitCount(int pageIndex);
} 