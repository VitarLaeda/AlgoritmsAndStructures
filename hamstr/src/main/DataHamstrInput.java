public class DataHamstrInput {

    private Hamster[] hamsters;

    private long dailyBudget;

    public Hamster[] getHamsters() {
        return hamsters;
    }

    public long getDailyBudget() {
        return dailyBudget;
    }

    public DataHamstrInput(long dailyBudget, Hamster[] hamsters) {
        this.hamsters = hamsters;
        this.dailyBudget = dailyBudget;
    }
}
