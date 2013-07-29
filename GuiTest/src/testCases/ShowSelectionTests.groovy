package testCases
import pageObjects.ShowItemList;
import pageObjects.ShowSelection;
import geb.spock.GebSpec

class ShowSelectionTests extends TestBase {

	def "All shows available"() {
		when:
		to ShowSelection
		
		then:
		shows.size() == 5 // four shows + 'select show' text
	}
	
	def "Select Show"() {
		when:
		to ShowSelection
		selectShowById('show1');

		then:
		at ShowItemList
		items.size() == 2
	}
}
