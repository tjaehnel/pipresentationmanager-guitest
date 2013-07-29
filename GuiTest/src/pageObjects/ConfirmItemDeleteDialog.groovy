package pageObjects

import geb.Page

class ConfirmItemDeleteDialog extends Page {
	static url = "test"
	static at = {
		dialog.displayed == true
	}
	static content = {
		dialog { $("#confirmitemdeletedialog") }
		itemtitleText { dialog.find(".itemtitle").html() }
		yesBtn { dialog.parent().find(text: "Yes") }
		noBtn { dialog.parent().find(text: "No") }
	}
}
