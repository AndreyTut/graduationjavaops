package my.study.graduation.model;

public class Money {

    private long amountInCents;

    public Money(long amountInCents) {
        this.amountInCents = amountInCents;
        dollars = (int) amountInCents / 100;
        cents = (int) amountInCents % 100;
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(long amountInCents) {
        this.amountInCents = amountInCents;
    }

    private int dollars;

    private int cents;

    public int getDollars() {
        return dollars;
    }

    public int getCents() {
        return cents;
    }

    @Override
    public String toString() {
        return dollars + "." + cents + "$";
    }
}
