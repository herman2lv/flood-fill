package com.epam.rd.autocode.floodfill;

import java.util.Arrays;

public class FloodFillImpl implements FloodFill {
    @Override
    public void flood(String map, FloodLogger logger) {
        logger.log(map);
        if (isFlooded(map)) {
            return;
        }
        char[] updatedMap = Arrays.copyOf(map.toCharArray(), map.length());
        for (int i = 0; i < map.length(); i++) {
            if (map.toCharArray()[i] == WATER) {
                floodNeighbors(updatedMap, i);
            }
        }
        flood(String.valueOf(updatedMap), logger);
    }

    private boolean isFlooded(String map) {
        for (char element : map.toCharArray()) {
            if (element == LAND) {
                return false;
            }
        }
        return true;
    }



    private void floodNeighbors(char[] map, int i) {
        int length = getLength(map);
        int upCell = i - length;
        if (upCell >= 0) {
            map[upCell] = WATER;
        }
        int downCell = i + length;
        if (downCell < map.length) {
            map[downCell] = WATER;
        }
        int leftCell = i - 1;
        if (i % length != 0) {
            map[leftCell] = WATER;
        }
        int rightCell = i + 1;
        if ((i + 2) % length != 0 && rightCell < map.length) {
            map[rightCell] = WATER;
        }
    }

    private int getLength(char[] map) {
        int length = 1;
        for (char element : map) {
            if (element == '\n') {
                break;
            }
            length++;
        }
        return length;
    }
}
