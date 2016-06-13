package se.sandevind.monty.service;

import org.springframework.stereotype.Service;
import se.sandevind.monty.StayOrSwap;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GameService {
    private Random randomGen = new Random();

    public boolean run(int selected, StayOrSwap choice) {
        if (selected < 1 || selected > 3) {
            throw new IllegalArgumentException("Selected lake is out of range: " + selected);
        }
        List<Boolean> listOfThreeLakes = getListOfThreeLakesWithOneRandomNessie();
        if (choice == StayOrSwap.STAY) {
            return listOfThreeLakes.get(selected -1);
        }
        return getSwapLake(selected, listOfThreeLakes);
    }

    private Boolean getSwapLake(int selected, List<Boolean> listOfThreeLakes) {
        int swapLakeNo =  6 - (getFirstEmptyNonSelectedLake(selected, listOfThreeLakes) + 1) - selected - 1;
        return listOfThreeLakes.get(swapLakeNo);
    }

    List<Boolean> getListOfThreeLakesWithOneRandomNessie() {
        List<Boolean> listOfThreeLakes = Arrays.asList(false, false, false);
        listOfThreeLakes.set(randomGen.nextInt(3), true);
        return listOfThreeLakes;
    }

    int getFirstEmptyNonSelectedLake(int selected, List<Boolean> listOfThree) {
        int ix = 0;
        for (Boolean entry : listOfThree) {
            if (!entry && ix != selected -1) {
                return ix;
            }
            ix++;
        }
        throw new RuntimeException("No empty entry in list left...");
    }
}
