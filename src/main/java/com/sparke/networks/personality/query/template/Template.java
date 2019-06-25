package com.sparke.networks.personality.query.template;


import java.util.Objects;

public class Template {

    private String name;

    public Template(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return Objects.equals(name, template.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
