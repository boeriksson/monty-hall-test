package se.sandevind.monty.service;

import org.springframework.stereotype.Service;
import se.sandevind.monty.StayOrSwap;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    public boolean run(int selected, StayOrSwap choice) {
        List<Boolean> listOfThree = getListOfThreeWithOneRandomTrue();
        if (choice == StayOrSwap.stay) {
            return listOfThree.get(selected -1);
        }
        int swap = 6 - (getFirstEmpty(selected, listOfThree) + 1) - selected - 1;
        return listOfThree.get(swap);
    }

    protected List<Boolean> getListOfThreeWithOneRandomTrue() {
        Random randomGen = new Random();
        List<Boolean> listOfThree = Arrays.asList(false, false, false);
        listOfThree.set(randomGen.nextInt(3), true);
        return listOfThree;
    }

    protected int getFirstEmpty(int selected, List<Boolean> listOfThree) {
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
