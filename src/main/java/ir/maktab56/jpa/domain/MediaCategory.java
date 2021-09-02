package ir.maktab56.jpa.domain;

import java.util.Set;

public class MediaCategory extends Category<MediaCategory> {

    @Override
    public Set<MediaCategory> getChildrenCategories() {
        return super.getChildrenCategories();
    }

    @Override
    public MediaCategory getParent() {
        return super.getParent();
    }
}
