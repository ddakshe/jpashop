package kennen.jpashop.db;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String userId;
    private String userName;
    private int age;
}

