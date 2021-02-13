package youji.jpaEx.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")

    @Column(name = "name", nullable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) //string으로 하여 그대로 넣는것을 추천한다, ordinal X
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member() {
    }
}
