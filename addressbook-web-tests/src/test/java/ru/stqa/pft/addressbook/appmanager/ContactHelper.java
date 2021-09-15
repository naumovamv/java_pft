package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    click(By.name("firstname"));
    type(By.name("firstname"), contactData.getFirstname());
    click(By.xpath("//div[@id='content']/form/label[3]"));
    click(By.name("lastname"));
    type(By.name("lastname"), contactData.getLastname());
    click(By.name("address"));
    type(By.name("address"), contactData.getAddress());
    click(By.xpath("//div[@id='content']/form/label[9]"));
    click(By.name("home"));
    click(By.name("mobile"));
    type(By.name("mobile"), contactData.getMobile());
    click(By.name("email"));
    type(By.name("email"), contactData.getEmail());


    if (creation) {
      new Select(wd.findElement(By.xpath("//select[@name='new_group']"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.xpath("//select[@name='new_group']")));
    }
  }

  public void initContactPage() {
    click(By.linkText("add new"));
  }

  public void deleteSelectContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact() { click(By.name("selected[]"));  }

  public void editContact() { click(By.xpath("//img[@alt='Edit']"));  }

  public void submitContactModification() { click(By.xpath("//div[@id='content']/form/input[22]"));  }

  public void createContact(ContactData contact, boolean creation) {
    initContactPage();
    fillContactForm(contact, creation);
    submitContactCreation();
    returnToContactPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
