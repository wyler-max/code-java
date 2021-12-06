package org.example.practice.practicespring.domain.api.impl;

import org.example.practice.practicespring.domain.api.Customer;
import org.example.practice.practicespring.domain.entity.EmailAddress;
import org.example.practice.practicespring.domain.entity.PostalAddress;
import org.example.practice.practicespring.domain.entity.Telephone;

/**
 * @author wangyulin
 * @date 2021/9/17
 */
public class CustomerImpl implements Customer {

    @Override
    public void changePersonalName(String name) {
        // todo changeName
    }

    @Override
    public void postalAddress(PostalAddress postalAddress) {

    }

    @Override
    public void relocateTo(PostalAddress postalAddress) {

    }

    @Override
    public void changeHomeTelephone(Telephone telephone) {

    }

    @Override
    public void disconnectHomeTelephone() {

    }

    @Override
    public void changeMobileTelephone(Telephone telephone) {

    }

    @Override
    public void disconnectMobileTelephone() {

    }

    @Override
    public void primaryEmailAddress(EmailAddress emailAddress) {

    }
}
