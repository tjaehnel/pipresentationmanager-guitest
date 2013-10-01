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

class SlideTextTests extends TestBase {
	def setup() {
		when:
		to ShowSelection
		selectShowById("show2")
		then:
		at ShowItemList
		
		waitFor {
			items.size() > 1
		}
	}
	
	def "Show Textbox when image config available"() {
		when:
		clickItemByIndex(1)
		
		then:
		at SlideEditor
		waitFor {
			imageTextEditor.isDisplayed()
		}
	}

	def "Hide Textbox when no image config available"() {
		when:
		item(0).click()
		
		then:
		at SlideEditor
		imageTextEditor.isDisplayed() == false
	}
	
	def "Change Text"() {
		when:
		clickItemByIndex(1)
		then:
		at SlideEditor

		when:
		waitFor{ imageTextEditor.isDisplayed() }
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
	
	def "Hide Textbox when selecting image without config"() {
		when:
		item(1).click()
		then:
		at SlideEditor

		when:
		selectBackgroundLink.click()
		then:
		at FilePickerDialog
		
		when:
		waitFor { filepickeritems.size() > 1 }
		filepickeritems[1].click()
		
		then:
		at SlideEditor
		waitFor {
			$(".blockUI").size() < 1
		}
		!imageTextEditor.isDisplayed();
	}
	
	def "Show Textbox when selecting image with config"() {
		when:
		item(0).click()
		then:
		at SlideEditor

		when:
		selectBackgroundLink.click()
		then:
		at FilePickerDialog
		
		when:
		waitFor { filepickeritems.size() > 12 }
		filepickeritems[12].click()
		
		then:
		at SlideEditor
		waitFor {
			$(".blockUI").size() < 1
		}
		imageTextEditor.isDisplayed();
	}
	
	def "Forget text when image has no configuration"() {
		when:
		clickItemByIndex(1)
		then:
		at SlideEditor
		waitFor {
			imageTextEditor.isDisplayed()
		}
		
		when:
		imageTextEditor << "This is a test description"
		saveSlide()
		then:	
		at ShowItemList

		when:
		item(1).click()
		waitFor { at SlideEditor }
		selectBackgroundLink.click()
		then:
		at FilePickerDialog
		
		when:
		waitFor { filepickeritems.size() > 1 }
		filepickeritems[1].click()
		then:
		at SlideEditor
		waitFor {
			previewImageUrl.contains("IMG-0002.jpg")
			$(".blockUI").size() < 1
		}
		saveSlide()
		at ShowItemList
		
		when:
		item(1).click()
		waitFor { at SlideEditor }
		selectBackgroundLink.click()
		then:
		at FilePickerDialog
		
		when:
		waitFor { filepickeritems.size() > 12 }
		filepickeritems[12].click()
		then:
		at SlideEditor
		waitFor {
			$(".blockUI").size() < 1
		}
		imageTextEditor == ""
	}
}
