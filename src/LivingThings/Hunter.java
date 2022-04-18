package LivingThings;

public interface Hunter {

    void chooseTarget();

    void chaseTarget();

    void eat(LivingThing livingThing);

    void checkCollision(LivingThing plant);

}
