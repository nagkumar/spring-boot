package com.tejasoft.sboot.api.client.models;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clientEmail")
public final class ClientEmail
	extends Base
{
    private static final long serialVersionUID = 1396542167093193958L;

    @Column(nullable = false, length = 80)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmailType emailType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public String getEmail()
    {
	return email;
    }

    public void setEmail(final String aEmail)
    {
	email = aEmail;
    }

    public EmailType getEmailType()
    {
	return emailType;
    }

    public void setEmailType(final EmailType aEmailType)
    {
	emailType = aEmailType;
    }

    public Client getClient()
    {
	return client;
    }

    public void setClient(final Client aClient)
    {
	client = aClient;
    }

    @Override
    public int hashCode()
    {
	return Objects.hashCode(getId(),
				getActive(),
				getVersion(),
				email,
				emailType,
				client);
    }

    @Override
    public boolean equals(final Object aObj)
    {
	return Objects.equal(this, aObj);
    }
}
