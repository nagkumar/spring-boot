package com.tejasoft.sboot.archunit.model;

import com.google.common.base.Objects;
import com.tejasoft.sboot.archunit.utils.StringConsts;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "client")
public final class Client extends Base
{
    private static final long serialVersionUID = -2974615880078954663L;

    @Column(nullable = false, length = 100)
    private String firstname;

    @Column(nullable = false, length = 100)
    private String lastname;

    @Column(nullable = true, length = 100)
    private String documentType;

    @Column(nullable = true, length = 100)
    private String documentNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = StringConsts.CLIENT, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ClientPhone> phones;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = StringConsts.CLIENT, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ClientEmail> emails;

    @Column(nullable = true, length = 100)
    private String observation;

    public String getFirstname()
    {
	return firstname;
    }

    public void setFirstname(String aFirstname)
    {
	firstname = aFirstname;
    }

    public String getLastname()
    {
	return lastname;
    }

    public void setLastname(String aLastname)
    {
	lastname = aLastname;
    }

    public List<ClientPhone> getPhones()
    {
	return phones;
    }

    public void setPhones(List<ClientPhone> aPhones)
    {
	phones = aPhones;
    }

    public String getObservation()
    {
	return observation;
    }

    public void setObservation(final String aObservation)
    {
	observation = aObservation;
    }

    public String getDocumentType()
    {
	return documentType;
    }

    public void setDocumentType(final String aDocumentType)
    {
	documentType = aDocumentType;
    }

    public String getDocumentNumber()
    {
	return documentNumber;
    }

    public void setDocumentNumber(final String aDocumentNumber)
    {
	documentNumber = aDocumentNumber;
    }

    public List<ClientEmail> getEmails()
    {
	return emails;
    }

    public void setEmails(final List<ClientEmail> aEmails)
    {
	emails = aEmails;
    }

    @Override
    public int hashCode()
    {
	return Objects.hashCode(getId(),
				getActive(),
				getVersion(),
				firstname,
				lastname,
				documentType,
				documentNumber,
				phones,
				emails,
				observation);
    }

    @Override
    public boolean equals(final Object aObj)
    {
	return Objects.equal(this, aObj);
    }
}
