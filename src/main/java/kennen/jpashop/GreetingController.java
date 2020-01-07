package kennen.jpashop;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @PostMapping("greeting")
    public Hello greeting(@RequestBody Hello hello){
        return hello;
    }
}
