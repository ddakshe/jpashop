package kennen.jpashop.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipCode;

    protected Address() {
    }
}
