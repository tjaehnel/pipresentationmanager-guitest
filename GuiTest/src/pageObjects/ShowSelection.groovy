package pageObjects

import geb.Page
import groovy.lang.MetaClass;

class ShowSelection extends Page {
	static url = "test"
	static at = {
		shows.size() > 0
	}
	static content = {
		showlist { $("#showselect") }
		shows (wait: true) { showlist.find("option") }
	}
	
	def selectShowById(id) {
		int index = shows.findIndexOf {
			it.@value == id
		}
		selectShowByIndex(index)
	}
	
	def selectShowByIndex(index) {
		// this is a bit of a hack, since usage of the multiselect item hides the select
		// element and geb does not allow interacting with hidden elements.
		// so we have to operate the multiselect item manually by knowing the internals
		showlist.jquery.multiselect("open");
		$("label", for: "ui-multiselect-showselect-option-" + index).click();
	}
}
