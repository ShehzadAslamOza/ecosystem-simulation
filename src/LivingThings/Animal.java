package LivingThings;


public abstract class Animal extends LivingThing {
    protected LivingThing target = null;
    protected int reactionTime = 6;



    public void checkCollision(LivingThing plant) {

        if (target != null && target.isAlive) {
            int distance = calculateDistance(center,plant);

            int plantRadius = plant.size / 2;
            int herbivoreRadius = size / 2;

            if ((herbivoreRadius + plantRadius) >= distance) {
                eat(plant);
            }
        }
    }

    public void update() {
        if (timeToLive == 0) {
            Die();
        } else {
            timeToLive--;
        }

        if (target == null) {
            chooseTarget();
        } else if (target.isDead()) {
            chooseTarget();
        }

        chaseTarget();

        checkCollision(target);

    }

    public abstract void chooseTarget();

    public abstract void chaseTarget();

    public abstract void generateOffSprings(int num);

    public abstract String generateID();

    public abstract void eat(LivingThing livingThing);
}
