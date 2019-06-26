package com.sparke.networks.personality.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
class Condition {
    private final Predicate predicate;
    private final IfPositive ifPositive;
}