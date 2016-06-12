package se.sandevind.monty.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.sandevind.monty.StayOrSwap;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class GameServiceTest {

    GameService gameService;

    @Before
    public void initGameServiceSpy() {
        gameService = Mockito.spy(GameService.class);
    }

    private void setListOfTree(Boolean one, Boolean two, Boolean three) {
        List<Boolean> listOfThree = Arrays.asList(one, two, three);
        Mockito.when(gameService.getListOfThreeWithOneRandomTrue()).thenReturn(listOfThree);
    }

    @Test
    public void testGetFirstEmpty1() {
        int result = gameService.getFirstEmpty(1, Arrays.asList(false, true, false));
        assertThat(result, is(2));
    }

    @Test
    public void testGetFirstEmpty2() {
        int result = gameService.getFirstEmpty(1, Arrays.asList(false, false, true));
        assertThat(result, is(1));
    }

    @Test
    public void testGetFirstEmpty3() {
        int result = gameService.getFirstEmpty(2, Arrays.asList(false, true, false));
        assertThat(result, is(0));
    }

    @Test
    public void testGetFirstEmptyException() {
        try {
            int result = gameService.getFirstEmpty(2, Arrays.asList(true, true, true));
            fail("Exception expected");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("No empty entry in list left..."));
        }
    }

    @Test
    public void testRunWithStayHit() {
        setListOfTree(true, false, false);
        Boolean result = gameService.run(1, StayOrSwap.stay);
        assertThat(result, is(true));
    }

    @Test
    public void testRunWithStayMiss() {
        setListOfTree(false, true, false);
        Boolean result = gameService.run(1, StayOrSwap.stay);
        assertThat(result, is(false));
    }

    @Test
    public void testRunWithSwapHit() {
        setListOfTree(false, true, false);
        Boolean result = gameService.run(1, StayOrSwap.swap);
        assertThat(result, is(true));
    }

    @Test
    public void testRunWithSwapMiss() {
        setListOfTree(true, false, false);
        Boolean result = gameService.run(1, StayOrSwap.swap);
        assertThat(result, is(false));
    }
}
