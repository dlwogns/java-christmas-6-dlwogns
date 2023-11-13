package christmas.model;

import static christmas.constant.Badge.NONE;
import static christmas.constant.Badge.SANTA;
import static christmas.constant.Badge.STAR;
import static christmas.constant.Badge.TREE;
import static christmas.constant.Numbers.CHECKSANTA;
import static christmas.constant.Numbers.CHECKSTAR;
import static christmas.constant.Numbers.CHECKTREE;

public class EventBadge {
    private String eventbadge;

    public EventBadge(Integer totalDiscount) {
        eventbadge = checkEventBadge(totalDiscount);
    }
    private String checkEventBadge(Integer totalDiscount){
        if(totalDiscount < CHECKSTAR.getValue())
            return NONE.getValue();
        if(totalDiscount < CHECKTREE.getValue())
            return STAR.getValue();
        if(totalDiscount < CHECKSANTA.getValue())
            return TREE.getValue();
        return SANTA.getValue();
    }

    @Override
    public String toString() {
        return eventbadge;
    }
}
