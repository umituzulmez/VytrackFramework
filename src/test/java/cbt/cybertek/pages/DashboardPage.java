package cbt.cybertek.pages;

import cbt.cybertek.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

    //no need to write constructors, because it will use its parent's constructors
    public DashboardPage(){

        PageFactory.initElements(Driver.get(),this);
    }

}

