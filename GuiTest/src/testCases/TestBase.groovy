package testCases

import geb.spock.GebSpec

class TestBase extends GebSpec {
	def setup() {
		go "prepareTesting.php?command=init"
		waitFor {
			$("#success").isDisplayed()
		}
	}
	def cleanup() {
		go "prepareTesting.php?command=cleanup"
		waitFor {
			$("p").isDisplayed()
		}
	}
}
