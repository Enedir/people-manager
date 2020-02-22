package sajadv.webapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliveController {

    @RequestMapping("/is-alive")
    public Boolean isAlive() {
        return true;
    }
}
