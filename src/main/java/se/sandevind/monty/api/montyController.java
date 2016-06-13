package se.sandevind.monty.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.sandevind.monty.StayOrSwap;
import se.sandevind.monty.service.GameService;

@RestController
public class MontyController {

    @Autowired
    GameService gameService;

    @RequestMapping(value="/run", method = RequestMethod.GET)
    public boolean runGame(@RequestParam("lake") int lake, @RequestParam("choice") String choice) {
        return gameService.run(lake, StayOrSwap.getEnum(choice));
    }
}
