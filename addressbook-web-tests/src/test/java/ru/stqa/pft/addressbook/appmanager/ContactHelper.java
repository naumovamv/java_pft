package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
    closeAlertBox();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  private void initContactModifyByID(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactPage();
    fillContactForm(contact, creation);
    submitContactCreation();
    contactCache = null;
    returnToContactPage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModifyByID(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectContact();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname =  cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress("Saint-Petersburg").withMobile("+71111111111").withEmail("test@test.ru").withGroup("test1"));
    }
    return new Contacts(contactCache);
  }
 }


