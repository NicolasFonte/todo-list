package com.yordex.test.dl.domain

import groovy.transform.ToString
import groovy.transform.builder.Builder;

import javax.persistence.Entity;
import java.time.ZonedDateTime;

@Entity
@Builder
@ToString
public class Task {

    String id;

    ZonedDateTime creationDate;

    ZonedDateTime modificationDate;

    String description;

    String name;
}
