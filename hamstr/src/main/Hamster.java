public class Hamster {

    private long hunger;

    private long greed;

    public Hamster(int hunger, int greed) {
        this.hunger = hunger;
        this.greed = greed;
    }

    public long getDailyFoodNeeds(int neighborCount) {
        return hunger + greed * neighborCount;
    }
}
