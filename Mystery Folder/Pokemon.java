public class Pokemon extends Card {
    private int hp;

    public int getHp() {
        return hp;
    }

    public void setHp(int userInputHP) {
        hp = userInputHP;
    }
    public String toString() {
        return this.getClass().getSimpleName() + " (HP: " + hp + ")";
    }
}
