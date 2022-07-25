package com.tejasoft.sboot.api.client.models;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
public abstract class Base implements Serializable
{
    private static final long serialVersionUID = -2053886894431223968L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active = Boolean.TRUE;

    public Long getId()
    {
	return id;
    }

    public void setId(final Long aId)
    {
	id = aId;
    }

    public Integer getVersion()
    {
	return version;
    }

    public void setVersion(final Integer aVersion)
    {
	version = aVersion;
    }

    public Boolean getActive()
    {
	return active;
    }

    public void setActive(final Boolean aActive)
    {
	active = aActive;
    }

    @Override
    public int hashCode()
    {
	return Objects.hashCode(id, active, version);
    }

    @Override
    public boolean equals(final Object aObj)
    {
	return Objects.equal(this, aObj);
    }
}
