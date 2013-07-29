package testCases

import geb.spock.GebSpec

class TestBase extends GebSpec {
	def setup() {
		go "prepareTesting.php?command=cleanup"
		go "prepareTesting.php?command=init"
	}
	def cleanup() {
		go "prepareTesting.php?command=cleanup"
	}
}
