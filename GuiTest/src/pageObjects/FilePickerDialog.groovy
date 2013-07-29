package pageObjects

import geb.Page

class FilePickerDialog extends Page {
	static url = "test"
	static at = {
		filepickerdialog.displayed == true
	}
	static content = {
		filepickerdialog { $("#filepickerdialog") }
		filepickeritems { $("#filepickerdialog #filepickeritems li") }
	}	
}
