package com.yordex.test.dl.domain

import groovy.transform.ToString
import groovy.transform.builder.Builder

import javax.persistence.Entity
import java.time.ZonedDateTime

@Entity
@Builder
@ToString(excludes = ["password"])
public class User {

    String id;

    ZonedDateTime creationDate;

    ZonedDateTime modificationDate;

    String password;
}
