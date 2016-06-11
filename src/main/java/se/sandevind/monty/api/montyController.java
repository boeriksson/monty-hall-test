package se.sandevind.monty.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.sandevind.monty.StayOrSwap;
import se.sandevind.monty.vo.Round;

@RestController
public class montyController {

    @RequestMapping(value="/run", method= RequestMethod.GET)
    public Round runGame(@RequestParam("door") int door, @RequestParam("choice") StayOrSwap choice) {
        return new Round(true);
    }
}
