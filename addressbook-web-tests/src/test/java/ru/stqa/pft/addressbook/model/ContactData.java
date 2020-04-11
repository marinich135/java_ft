package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  private  String firstname;
  private  String middlename;
  private  String lastname;
  private  String nickname;
  private  String company;
  private  String address;
  private  String email;
  private  String email2;
  private  String email3;
  private  String homePhone;
  private  String mobilePhone;
  private  String workPhone;
  private  String allPhones;
  private  String allEmails;
  private  String allAddresses;
  private  String group;

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  private  File photo;

  public String getAllAddresses() {
    return allAddresses;
  }

  public ContactData withAllAddresses(String allAddresses) {
    this.allAddresses = allAddresses;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public String getEmail2() {    return email2;  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {    return email3;  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getHomePhone() {    return homePhone;  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {    return mobilePhone;  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() {    return workPhone;  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }


  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobilePhone;
  }

  public String getGroup() {
    return group;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ContactData)) return false;
    ContactData that = (ContactData) o;
    return getId() == that.getId() &&
            Objects.equals(getFirstname(), that.getFirstname()) &&
            Objects.equals(getLastname(), that.getLastname());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId(), getFirstname(), getLastname());
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
