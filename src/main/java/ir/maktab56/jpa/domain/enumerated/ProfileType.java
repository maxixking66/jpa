package ir.maktab56.jpa.domain.enumerated;

public enum ProfileType {

    ADMIN, CUSTOMER;

    @Override
    public String toString() {
        switch (this) {
            case ADMIN:
                return "ادمین";
            case CUSTOMER:
                return "مشتری";
        }
        return null;
    }
}
