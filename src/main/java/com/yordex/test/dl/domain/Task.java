package com.yordex.test.dl.domain;

import groovy.transform.ToString;
import groovy.transform.builder.Builder;
import java.util.Date;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Type;

@Entity
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "complete", nullable = false)
    private boolean complete;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "due_date", nullable = false)
    @Type(type = "date")
    private Date dueDate;

    @OneToOne
    @JoinColumn(name = "frequency_id")
    private Frequency frequency;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
