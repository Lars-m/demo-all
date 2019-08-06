package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String email;

    public Person() {
    }
    
//    public Person(PersonDto dto) {
//        this.firstName = dto.getFirstName();
//        this.lastName = dto.getLastName();
//        this.address1 = dto.getAddress1();
//        this.address2 = dto.getAddress2();
//        this.email = dto.getEmail();
//    }
    
    public Person(String fn,String ln, String a1, String a2,String email) {
        this.firstName = fn;
        this.lastName = ln;
        this.address1 = a1;
        this.address2 = a2;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    } 
}
