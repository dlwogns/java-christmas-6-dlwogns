package christmas.model;

import static christmas.constant.Badge.NONE;
import static christmas.constant.Badge.SANTA;
import static christmas.constant.Badge.STAR;
import static christmas.constant.Badge.TREE;

public class EventBadge {
    private String eventbadge;

    public EventBadge(Integer totalDiscount) {
        eventbadge = checkEventBadge(totalDiscount);
    }
    private String checkEventBadge(Integer totalDiscount){
        if(totalDiscount < 5000)
            return NONE.getValue();
        if(totalDiscount < 10000)
            return STAR.getValue();
        if(totalDiscount < 20000)
            return TREE.getValue();
        return SANTA.getValue();
    }

    @Override
    public String toString() {
        return eventbadge;
    }
}
