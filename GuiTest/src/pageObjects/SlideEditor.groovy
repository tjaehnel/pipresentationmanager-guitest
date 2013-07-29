package pageObjects

import geb.Page

class SlideEditor extends Page {
	static url = "test"
	static at = {
		headline.text().startsWith("Picture:")
	}
	static content = {
		headline { $("#pictureEditor h2") }
		titleText { $("#pictureEditor h2 .title") }
		titleEditor { $("#pictureEditor h2 input") }
		previewImage { $("#pictureEditor .previewImage") }
		previewImageUrl { previewImage.@src }
		selectBackgroundLink { $("#selectPictureLink") }
		saveSlideBtn { $("#pictureEditor .submitbutton") }
		imageTextEditor { $("#pictureEditor .imageText") }
	}
	
	def saveSlide() {
		withAlert(wait: true) { saveSlideBtn.click() }
	}
}