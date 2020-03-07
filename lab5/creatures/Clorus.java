package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1.0);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public void move() {
        energy -= 0.03;
        //energy = Math.max(energy, 0.0);
    }

    public void stay() {
        energy -= 0.01;
        //energy = Math.max(energy, 0.0);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public Clorus replicate() {
        energy = energy * 0.5;
        double babyEnergy = energy * 0.5;
        return new Clorus(babyEnergy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> pilpNeighbors = new ArrayDeque<>();
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            Direction dire = entry.getKey();
            Occupant occu = entry.getValue();
            if (occu.name().equals("empty")) {
                emptyNeighbors.add(dire);
                continue;
            }
            if (occu.name().equals("plip")) {
                pilpNeighbors.add(dire);
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        if (! pilpNeighbors.isEmpty()) {
            Direction dire = randomEntry(pilpNeighbors);
            return new Action(Action.ActionType.ATTACK, dire);
        }

        if (energy >= 1.0) {
            Direction dire = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, dire);
        }

        Direction dire = randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, dire);
    }
}
