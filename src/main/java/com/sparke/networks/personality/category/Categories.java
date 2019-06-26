package com.sparke.networks.personality.category;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Categories {
    private List<String> categories;
}
