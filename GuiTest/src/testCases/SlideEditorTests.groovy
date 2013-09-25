package testCases;

import java.awt.ItemSelectable;

import groovy.swing.factory.TitledBorderFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.openqa.selenium.internal.seleniumemulation.DragAndDrop;
import org.openqa.selenium.internal.seleniumemulation.DragAndDropToObject;
import org.openqa.selenium.internal.seleniumemulation.WaitForCondition;

import pageObjects.ShowItemList;
import pageObjects.ShowSelection;
import pageObjects.SlideEditor;
import pageObjects.FilePickerDialog;

class SlideEditorTests extends TestBase {
	def setup() {
		when:
		to ShowSelection
		selectShowById("show2")
		then:
		at ShowItemList
				
		when:
		waitFor {
			items.size() > 1
		}
		item(1).click()
		
		then:
		at SlideEditor
	}

	def "Change Title"() {
		when:
		at SlideEditor
		titleText.click()
		titleEditor.focus()
		titleEditor << Keys.chord(Keys.CONTROL, "a")
		titleEditor << "New Test Title"		
		previewImage.click()
		
		then:
		titleText.text() == "New Test Title"
		
		when:
		saveSlide()
		
		then:
		at ShowItemList
		titleOfItem(1) == "New Test Title"
		
		when:
		to ShowSelection
		selectShowById("show2")
		waitFor { at ShowItemList }
		
		then:
		titleOfItem(1) == "New Test Title"
	}
	
	def "Change Background"() {
		when:
		at SlideEditor
		selectBackgroundLink.click()
		
		then:
		at FilePickerDialog
		
		when:
		waitFor { filepickeritems.size() > 5 }
		filepickeritems[5].click()
		
		then:
		at SlideEditor
		waitFor {
			previewImageUrl.contains("IMG-0006.jpg")
		}
		
		when:
		saveSlide()
		
		then:
		at ShowItemList
		waitFor {
			imageOfItem(1).contains("IMG-0006.jpg")
		}
		
		when:
		to ShowSelection
		selectShowById("show2")
		waitFor { at ShowItemList }
		
		then:
		imageOfItem(1).contains("IMG-0006.jpg")
	}
	
	def "Change Text"() {
		when:
		at SlideEditor
		imageTextEditor << "This is a test description"
		saveSlide()
		
		to ShowSelection
		selectShowById("show2")
		waitFor { at ShowItemList }
		
		item(1).click()
		waitFor { at SlideEditor }
		
		then:
		waitFor {
			imageTextEditor == "This is a test description"
		}
	}
}
