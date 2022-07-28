package com.tejasoft.sboot.api.client.models;

import com.google.common.base.Objects;
import com.tejasoft.sboot.api.utils.APIConsts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = APIConsts.CLIENT_PHONE)
public final class ClientPhone
	extends Base
{
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
