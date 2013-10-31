package testCases;

import groovy.swing.factory.TitledBorderFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.openqa.selenium.internal.seleniumemulation.DragAndDrop;
import org.openqa.selenium.internal.seleniumemulation.DragAndDropToObject;
import org.openqa.selenium.internal.seleniumemulation.WaitForCondition;

import pageObjects.ShowItemList;
import pageObjects.ShowSelection;
import pageObjects.MovieEditor;
import pageObjects.FilePickerDialog;

class MovieEditorTests extends TestBase {
	def setup() {
		when:
		to ShowSelection
		selectShowById("show2")
		then:
		at ShowItemList
				
		when:
		item(2).click()
		
		at MovieEditor		
	}
	
	def "Change title by focus"() {
		when:
		at MovieEditor
		titleText.click()
		titleEditor.focus()
		titleEditor << Keys.chord(Keys.CONTROL, "a")
		titleEditor << "New Test Title"
		headline.click() // unfocus
		
		then:
		titleText.text() == "New Test Title"
	}
	
	def "Change title by icons"() {
		when:
		at MovieEditor
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
		at MovieEditor
		titleEditActivate.click()
		titleEditor.focus()
		titleEditor << Keys.chord(Keys.CONTROL, "a")
		titleEditor << "New Test Title"
		titleEditor << Keys.ENTER
		
		then:
		titleText.text() == "New Test Title"
	}
	
	def "Change Title"() {
		when:
		at MovieEditor
		titleText.click()
		titleEditor.focus()
		titleEditor << Keys.chord(Keys.CONTROL, "a")
		titleEditor << "New Test Title"		
		headline.click() // unfocus
		
		then:
		titleText.text() == "New Test Title"
		
		when:
		saveMovie()
		
		then:
		at ShowItemList
		titleOfItem(2) == "New Test Title"
		
		when:
		to ShowSelection
		selectShowById("show2")
		waitFor { at ShowItemList }
		
		then:
		titleOfItem(2) == "New Test Title"
	}
	
	def "Change Movie"() {
		when:
		at MovieEditor
		selectMovieLink.click()
		
		then:
		at FilePickerDialog
		
		when:
		waitFor { filepickeritems.size() > 1 }
		filepickeritems[1].click()
		
		then:
		at MovieEditor
		waitFor {
			filename == "file2.mp4"
		}
		
		when:
		saveMovie()
		to ShowSelection
		selectShowById("show2")
		waitFor { at ShowItemList }
		item(2).click()
		waitFor {
			at MovieEditor
		}
		
		then:
		filename == "file2.mp4"

	}

}
