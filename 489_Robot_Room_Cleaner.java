// This is Jake's submission

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

 class Solution {

    enum Directions {
        UP,
        LEFT,
        RIGHT,
        DOWN
    }

    Map<Integer, Set<Integer>> dp;
    Map<Directions, Integer> auditXMap;
    Map<Directions, Integer> auditYMap;

    public boolean checkDp(int x, int y) {
        Set<Integer> currSet = dp.get(x);
        if (currSet == null) {
            currSet = new HashSet<>();
            dp.put(x, currSet);
            return false;
        }
        return currSet.contains(y);
    }

    public void turnRobot(Robot robot, int x, int y, Directions currDirection) {
        int auditX = x + auditXMap.get(currDirection);
        int auditY = y + auditYMap.get(currDirection);
        if (!checkDp(auditX, auditY) && robot.move()) {
            dfsRoom(robot, auditX, auditY, 0, currDirection);
            // backtrace the robot
            robot.turnLeft();
            robot.turnLeft();
            robot.move();
            robot.turnLeft();
        } else {
            robot.turnRight();
        }
    }

    public void dfsRoom(Robot robot, int x, int y, int numDirections, Directions currDirection) {
        // unconditionally clean it when entering a square. 
        robot.clean();
        Set<Integer> currSet = dp.get(x);
        if (currSet == null) {
            currSet = new HashSet<>();
            dp.put(x, currSet);
        }
        currSet.add(y);
        // move in each direction
        while (numDirections < 4) {
            turnRobot(robot, x, y, currDirection);
            if (currDirection == Directions.UP) {
                currDirection = Directions.RIGHT;
            } else if (currDirection == Directions.LEFT) {
                currDirection = Directions.UP;
            } else if (currDirection == Directions.RIGHT) {
                currDirection = Directions.DOWN;
            } else if (currDirection == Directions.DOWN) {
                currDirection = Directions.LEFT;
            }
            numDirections++;
        }
    }

    public void cleanRoom(Robot robot) {
        // use DFS to visit each one and keep track in a dp Map of the visited locations.
        dp = new HashMap<>();
        auditXMap = new HashMap<>();
        auditXMap.put(Directions.UP, -1);
        auditXMap.put(Directions.LEFT, 0);
        auditXMap.put(Directions.RIGHT, 0);
        auditXMap.put(Directions.DOWN, 1);
        auditYMap = new HashMap<>();
        auditYMap.put(Directions.UP, 0);
        auditYMap.put(Directions.LEFT, -1);
        auditYMap.put(Directions.RIGHT, 1);
        auditYMap.put(Directions.DOWN, 0);
        // start at (0, 0) and go up initally
        dfsRoom(robot, 0, 0, 0, Directions.UP);
    }
}