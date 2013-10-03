package pageObjects

import geb.Page

class SlideEditor extends Page {
	static url = "test"
	static at = {
		headline.text().startsWith("Slide:")
	}
	static content = {
		headline { $("#pictureEditor h2") }
		titleText { $("#pictureEditor h2 .titletext") }
		titleEditActivate { $("#pictureEditor h2 .titleEditActivate") }
		titleEditDeactivate { $("#pictureEditor h2 .titleEditDeactivate") }
		titleEditor { $("#pictureEditor h2 input") }
		previewImage { $("#pictureEditor .previewImage") }
		previewImageUrl { previewImage.@src }
		selectBackgroundLink { $("#selectPictureLink") }
		saveSlideBtn { $("#pictureEditor .submitbutton") }
		imageTextEditor { $("#pictureEditor .imageText") }
	}
	
	def saveSlide() {
		saveSlideBtn.click();
		waitFor {
			$(".blockUI").size() < 1
			$(".jGrowl-message").size() > 0
		}
	}
}
