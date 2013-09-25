package pageObjects

import geb.Page

class MovieEditor extends Page {
	static url = "test"
	static at = {
		headline.text().startsWith("Movie:")
	}
	static content = {
		headline { $("#movieEditor h2") }
		titleText { $("#movieEditor h2 .title") }
		titleEditor { $("#movieEditor h2 input") }
		filename { $("#videoFilename").text() }
		selectMovieLink { $("#selectVideoLink") }
		saveMovieBtn { $("#movieEditor .submitbutton") }
	}
	
	def saveMovie() {
		saveMovieBtn.click();
		waitFor { $(".jGrowl-message").size() > 0 }
	}
}
