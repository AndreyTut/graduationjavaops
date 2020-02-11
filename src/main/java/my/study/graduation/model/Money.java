package my.study.graduation.model;

public class Money {

    private long amountInCents;

    public Money(long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(long amountInCents) {
        this.amountInCents = amountInCents;
    }


    @Override
    public String toString() {
        return amountInCents / 100 + "." + amountInCents % 100 + "$";
    }
}
