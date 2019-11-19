package com.chi.twitter.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/*
    Annotated with @MappedSuperclass to express that it is not a managed entity class on its own but rather will be extended by entity classes.
    See: https://www.oreilly.com/library/view/spring-data/9781449331863/ch04.html
    See: https://vladmihalcea.com/how-to-inherit-properties-from-a-base-class-entity-using-mappedsuperclass-with-jpa-and-hibernate/
 */
@MappedSuperclass
/*
    EntityListeners is for createdDate and modifiedDate
    See: https://www.baeldung.com/database-auditing-jpa
 */
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    // define native id generator to help us auto generate non null id
    // See: https://vladmihalcea.com/why-should-not-use-the-auto-jpa-generationtype-with-mysql-and-hibernate/
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;
}
