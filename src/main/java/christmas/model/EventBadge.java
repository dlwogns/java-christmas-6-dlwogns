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
        if (checkNONEBadge(totalDiscount)) {
            return NONE.getValue();
        }
        if (checkStarBadge(totalDiscount)) {
            return STAR.getValue();
        }
        if (checkTreeBadge(totalDiscount)) {
            return TREE.getValue();
        }
        return SANTA.getValue();
    }

    private boolean checkNONEBadge(Integer totalDiscount) {
        return totalDiscount < CHECK_STAR.getValue();
    }

    private boolean checkStarBadge(Integer totalDiscount) {
        return totalDiscount < CHECK_TREE.getValue();
    }

    private boolean checkTreeBadge(Integer totalDiscount) {
        return totalDiscount < CHECK_SANTA.getValue();
    }

    @Override
    public String toString() {
        return eventbadge;
    }
}
