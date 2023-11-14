package christmas.model;

import static christmas.constant.Badge.NONE;
import static christmas.constant.Badge.SANTA;
import static christmas.constant.Badge.STAR;
import static christmas.constant.Badge.TREE;
import static christmas.constant.Numbers.CHECK_SANTA;
import static christmas.constant.Numbers.CHECK_STAR;
import static christmas.constant.Numbers.CHECK_TREE;

public class EventBadge {
    private final String eventbadge;

    public EventBadge(Integer totalDiscount) {
        eventbadge = checkEventBadge(totalDiscount);
    }

    private String checkEventBadge(Integer totalDiscount) {
        if (totalDiscount < CHECK_STAR.getValue()) {
            return NONE.getValue();
        }
        if (totalDiscount < CHECK_TREE.getValue()) {
            return STAR.getValue();
        }
        if (totalDiscount < CHECK_SANTA.getValue()) {
            return TREE.getValue();
        }
        return SANTA.getValue();
    }

    @Override
    public String toString() {
        return eventbadge;
    }
}
