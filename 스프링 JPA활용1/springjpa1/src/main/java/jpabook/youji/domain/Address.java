package jpabook.youji.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

//어딘가 내장될 수 있다.
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // 그래서 직접 상속할일도 없고 상속을 할 수 없게 하여 손대지 말게 한다.
    protected Address() { }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
