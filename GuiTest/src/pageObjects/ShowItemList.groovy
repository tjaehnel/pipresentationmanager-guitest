package pageObjects

import geb.Page

class ShowItemList extends Page {
	static url = "test"
	static at = {
		items.size() > 0
	}
	static content = {
		items { $("#agendaitems li") }
		item { index -> items[index] }
		titleOfItem { index -> item(index).find(".sidebartitle").text() }
		itemImage { index -> item(index).find(".sidebarimage") }
		itemDeleteBtn { index -> item(index).find(".sidebardelete") }
		imageOfItem { index -> itemImage(index).@src }
		addSlideBtn { $("#addslidebtn") }
		addVideoBtn { $("#addvideobtn") }
	}
	def clickItemByIndex(index) {
		waitFor {
			itemImage(index).isDisplayed()
		}
		itemImage(index).click()
	}
}
