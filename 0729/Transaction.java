public class Transaction {
    private String transactionId;
    private String account;
    private int amount;
    private int timeOrder;

    public Transaction(
        String transactionId,
        String account,
        int amount,
        int timeOrder
    ) {
        this.transactionId = transactionId;
        this.account = account;
        this.amount = amount;
        this.timeOrder = timeOrder;
    }

    public int getAmount() {
        return amount;
    }

    public int getTimeOrder() {
        return timeOrder;
    }

    @Override
    public String toString() {
        return transactionId + " account=" + account
            + " amount=" + amount + " timeOrder=" + timeOrder;
    }
}
