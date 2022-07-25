package com.example.archunit.model;

import com.example.archunit.utils.StringConsts;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;

@Entity
@Table(name = StringConsts.CLIENT_PHONE)
public final class ClientPhone extends Base
{
    @Serial
    private static final long serialVersionUID = 1396542167093193958L;

    @Column(nullable = false, length = 80)
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    private PhoneType phoneType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public String getNumber()
    {
	return number;
    }

    public void setNumber(final String aNumber)
    {
	number = aNumber;
    }

    public PhoneType getPhoneType()
    {
	return phoneType;
    }

    public void setPhoneType(final PhoneType aPhoneType)
    {
	phoneType = aPhoneType;
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
				number,
				phoneType,
				client);
    }

    @Override
    public boolean equals(final Object aObj)
    {
	return Objects.equal(this, aObj);
    }
}
