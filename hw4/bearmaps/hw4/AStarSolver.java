package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private LinkedList<Vertex> solution = new LinkedList<>();
    private SolverOutcome outcome;
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Map<Vertex, Vertex> edgeTo = new HashMap<>();
        Map<Vertex, Double> distToStart = new HashMap<>();
        Map<Vertex, Double> distToEnd = new HashMap<>();
        ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();

        Stopwatch sw = new Stopwatch();
        pq.add(start, 0);
        distToStart.put(start, 0.0);
        distToEnd.put(end, 0.0);

        while (pq.size() != 0) {
            if (pq.getSmallest().equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distToStart.get(end);
                Vertex curVertex = end;
                while (!curVertex.equals(start)) {
                    solution.addFirst(curVertex);
                    curVertex = edgeTo.get(curVertex);
                }
                solution.addFirst(start);
                explorationTime = sw.elapsedTime();
                return;
            }
            Vertex p = pq.removeSmallest();
            List<WeightedEdge<Vertex>> neighbourEdges = input.neighbors(p);
            numStatesExplored++;

            explorationTime = sw.elapsedTime();
            if (explorationTime > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                solution = new LinkedList<>();
                solutionWeight = 0;
                return;
            }

            for (WeightedEdge<Vertex> edge : neighbourEdges) {
                Vertex from = edge.from();
                Vertex to = edge.to();
                double weight = edge.weight();

                if (!distToStart.containsKey(to)) {
                    distToStart.put(to, Double.POSITIVE_INFINITY);
                }

                if (!distToEnd.containsKey(to)) {
                    distToEnd.put(to, input.estimatedDistanceToGoal(to, end));
                }

                if (distToStart.get(to) > distToStart.get(from) + weight) {
                    distToStart.put(to, distToStart.get(from) + weight);
                    edgeTo.put(to, from);
                    if (pq.contains(to)) {
                        pq.changePriority(to, distToStart.get(to) + distToEnd.get(to));
                    } else {
                        pq.add(to, distToStart.get(to) + distToEnd.get(to));
                    }
                }

            }

        }
        outcome = SolverOutcome.UNSOLVABLE;
        solution = new LinkedList<>();
        solutionWeight = 0.0;
        explorationTime = sw.elapsedTime();

    }


    public SolverOutcome outcome() {
        return outcome;
    }
    public List<Vertex> solution() {
        return solution;
    }
    public double solutionWeight() {
        return solutionWeight;
    }
    public int numStatesExplored() {
        return numStatesExplored;
    }
    public double explorationTime() {
        return explorationTime;
    }
}
