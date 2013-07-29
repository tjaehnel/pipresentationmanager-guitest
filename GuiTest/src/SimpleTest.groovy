import pageObjects.ShowItemList;
import pageObjects.ShowSelection;
import geb.spock.GebSpec

class SimpleTest extends GebSpec {
	def "Select Show"() {
		when:
		to ShowSelection
		waitFor {
			shows.size() > 0
		}
		selectShowByIndex(2);

		then:
		at ShowItemList
		items.size() == 2
	}
}
