package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String group;
  private final String address;
  private final String mobile;
  private final String email;

  public ContactData(String firstname, String lastname, String address, String mobile, String email, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.group = group;
    this.address = address;
    this.mobile = mobile;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getGroup() {  return group; }

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }
}
