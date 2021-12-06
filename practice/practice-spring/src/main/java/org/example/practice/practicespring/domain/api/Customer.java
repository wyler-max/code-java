package org.example.practice.practicespring.domain.api;

import org.example.practice.practicespring.domain.entity.EmailAddress;
import org.example.practice.practicespring.domain.entity.PostalAddress;
import org.example.practice.practicespring.domain.entity.Telephone;

/**
 * @author wangyulin
 * @date 2021/9/17
 */
public interface Customer {

    public void changePersonalName(String name);

    public void postalAddress(PostalAddress postalAddress);

    public void relocateTo(PostalAddress postalAddress);

    public void changeHomeTelephone(Telephone telephone);

    public void disconnectHomeTelephone();

    public void changeMobileTelephone(Telephone telephone);

    public void disconnectMobileTelephone();

    public void primaryEmailAddress(EmailAddress emailAddress);
}
