package com.tejasoft.sboot.archunit.model;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emailType")
public final class EmailType extends Base
{
    private static final long serialVersionUID = 1697687804373017457L;

    @Column(nullable = false, length = 250)
    private String name;

    public String getName()
    {
	return name;
    }

    public void setName(final String aName)
    {
	name = aName;
    }

    @Override
    public int hashCode()
    {
	return Objects.hashCode(getId(),
				getActive(),
				getVersion(),
				name);
    }

    @Override
    public boolean equals(final Object aObj)
    {
	return Objects.equal(this, aObj);
    }
}