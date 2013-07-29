package testCases;

import java.sql.Driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.openqa.selenium.internal.seleniumemulation.DragAndDrop;
import org.openqa.selenium.internal.seleniumemulation.DragAndDropToObject;

import pageObjects.ConfirmItemDeleteDialog;
import pageObjects.ShowItemList;
import pageObjects.ShowSelection;
import pageObjects.SlideEditor;

class ShowItemsTests extends TestBase {
	def setup() {
		when:
		to ShowSelection
		selectShowById("show2")
		
		then:
		at ShowItemList
	}
	
	def navigateToShow() {
		when:
		to ShowSelection
		selectShowById("show2")
		
		then:
		at ShowItemList
	}

	def "Slide editor opens"() {
		when:
		item(0).click()
		
		then:
		at SlideEditor
	}
	
	def "Add and save new slide"() {
		when:
		addSlideBtn.click()
		waitFor { items.size() == 4 }
		
		then:
		titleOfItem(3) == "New Slide"
		
		when:
		saveShow()
		to ShowSelection
		selectShowById("show2")
		waitFor { at ShowItemList }
		
		then:
		titleOfItem(3) == "New Slide"
	}
	
	def "Add and save new video"() {
		when:
		addVideoBtn.click()
		waitFor { items.size() == 4 }
		
		then:
		titleOfItem(3) == "New Video"
		imageOfItem(3).contains("Video_sidebar.png")
		
		when:
		saveShow()
		to ShowSelection
		selectShowById("show2")
		waitFor { at ShowItemList }
		
		then:
		titleOfItem(3) == "New Video"
		imageOfItem(3).contains("Video_sidebar.png")
	}
	
	def "Reorder and save Items"() {
		when:
		Thread.sleep(1000);
		interact {
			//clickAndHold(items[1])
			//moveByOffset(15,100)
			//Thread.sleep(1000);
			//release()
			//Thread.sleep(1000);
			//clickAndHold(items[1])
			//moveByOffset(1,1) // workaround for Chrome driver bug
			//moveByOffset(15,200)
			//Thread.sleep(1000);
			//release()
			//Thread.sleep(1000);
			
			// Chrome driver does not support dragAndDropBy
			if (driver instanceof ChromeDriver) {
				System.out.println("ChromeDriver");
				dragAndDropBy(items[1], 15, 100)
			} else {
				System.out.println("FirefoxDriver");
				dragAndDropBy(items[1], 15, 150)
			}
		}
		Thread.sleep(1000);
		
		saveShow()
		to ShowSelection
		selectShowById("show2")
		waitFor { at ShowItemList }
		
		then:
		at ShowItemList
		items.size() == 3
		titleOfItem(1) == "Another item"
		titleOfItem(2) == "Something else"
	}
	
	def "Delete item and save show"() {
		when:
		// hack, because hovering does not work
		itemDeleteBtn(1).jquery.click()
		waitFor { at ConfirmItemDeleteDialog }
		yesBtn.click()
		
		then:
		at ShowItemList
		waitFor { items.size() == 2 }
		
		when:
		saveShow()
		to ShowSelection
		selectShowById("show2")
		waitFor { at ShowItemList }
		
		then:
		at ShowItemList
		items.size() == 2
	}
	
	def "Cancel deleting item"() {
		when:
		// hack, because hovering does not work
		itemDeleteBtn(1).jquery.click()
		waitFor { at ConfirmItemDeleteDialog }
		noBtn.click()
		
		then:
		at ShowItemList
		waitFor { items.size() == 3 }
	}
}
	