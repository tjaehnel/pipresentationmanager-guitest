package testCases;

import java.awt.ItemSelectable;

import javax.swing.text.DefaultEditorKit.PreviousWordAction;

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
		clickItemByIndex(1)
		
		then:
		at SlideEditor
	}
	
	def "Change title by focus"() {
		when:
		at SlideEditor
		titleText.click()
		titleEditor.focus()
		titleEditor << Keys.chord(Keys.CONTROL, "a")
		titleEditor << "New Test Title"
		previewImage.click()
		
		then:
		titleText.text() == "New Test Title"
	}
	
	def "Change title by icons"() {
		when:
		at SlideEditor
		titleEditActivate.click()
		titleEditor.focus()
		titleEditor << Keys.chord(Keys.CONTROL, "a")
		titleEditor << "New Test Title"
		titleEditDeactivate.click()
		
		then:
		titleText.text() == "New Test Title"
	}
	
	def "Change title edit leave by ENTER"() {
		when:
		at SlideEditor
		titleEditActivate.click()
		titleEditor.focus()
		titleEditor << Keys.chord(Keys.CONTROL, "a")
		titleEditor << "New Test Title"
		titleEditor << Keys.ENTER
		
		then:
		titleText.text() == "New Test Title"
	}
	
	def "Change title"() {
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
			$(".blockUI").size() < 1
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

}
