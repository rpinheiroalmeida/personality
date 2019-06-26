package com.sparke.networks.personality.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
class Predicate {
    private final String name;
    private final List<String> answeres;
}