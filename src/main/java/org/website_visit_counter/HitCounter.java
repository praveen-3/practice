package org.website_visit_counter;

import org.website_visit_counter.interfaces.IHitCounterService;
import org.website_visit_counter.services.HitCounterService;

public class HitCounter {
    private IHitCounterService hitCounterService;
    
    public void init(int totalPages) {
        this.hitCounterService = new HitCounterService(totalPages);
    }

    public void incrementVisitCount(int pageIndex) {
        hitCounterService.incrementVisitCount(pageIndex);
    }

    public int getVisitCount(int pageIndex) {
        return hitCounterService.getVisitCount(pageIndex);
    }
}
