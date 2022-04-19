package LivingThings;


public abstract class Animal extends LivingThing {
    protected LivingThing target = null;    // target (prey)
    protected int reactionTime = 2;         //

    /**
     * Updates the animal after every call/iteration
     * Updates timeToLive
     * Updates the target animal
     * Updates the chasing coordinates
     * Checks for Collision and Eats the Prey if Colliding
     */

    public void update() {

        // If Animal doesnt eat for a long time it dies
        if (timeToLive == 0) {
            Die();
        } else {
            timeToLive--;
        }

        // Choose the target
        if (target == null) {
            chooseTarget();
        } else if (target.isDead()) {
            chooseTarget();
        }

        // Chase the target
        chaseTarget();

        // Checks if target can be eaten
        checkCollision(target);

    }

    /**
     * Checks collision between the predator and the prey and eats if colliding
     */

    public void checkCollision(LivingThing livingThing) {

        if (target != null && target.isAlive) {
            int distance = calculateDistance(center,livingThing);

            int preyRadius = livingThing.size / 2;
            int predatorRadius = size / 2;

            // Eat the prey if colliding
            if ((predatorRadius + preyRadius) >= distance) {
                eat(livingThing);
            }
        }
    }


    /**
     * Chooses the nearest Target
     */
    public abstract void chooseTarget();

    /**
     * Chases the target inorder to eat it
     */
    public abstract void chaseTarget();

    /**
     * Generates offsprings
     */

    public abstract void generateOffSprings(int num);

    /**
     * Returns A unique ID for animal
     */
    public abstract String generateID();

    /**
     * Eats the prey
     */
    public abstract void eat(LivingThing livingThing);
}
