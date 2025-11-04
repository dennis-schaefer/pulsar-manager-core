package de.schaeferd.pulsar.manager.security;

import java.util.Collection;
import java.util.Collections;

public record UserInfo(String name, Collection<String> authorities)
{
    public UserInfo(String name, Collection<String> authorities)
    {
        this.name = name;
        this.authorities = Collections.unmodifiableCollection(authorities);
    }
}