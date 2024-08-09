package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor // 프록시 등의 리플렉션을 위한 기본 생성자
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
