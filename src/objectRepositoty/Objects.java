package objectRepositoty;

public interface Objects {
	
	//Amazon
	String string_AUrl = "https://www.amazon.in";
	String string_ASearchField = "//input[@id= 'twotabsearchtextbox']";
	String string_ASearchIcon = "//span[@id= 'nav-search-submit-text']";
	String string_SearchText = "iPhone 12 mini 64gb blue";
	String string_AProductName = "//h2//*[text()=  'New Apple iPhone 12 Mini (64GB) - Blue']";
	String string_AProductPrice = "//span[@id= 'priceblock_ourprice']";
	//String string_AProductPrice = "//h2//*[text()=  'New Apple iPhone 12 Mini (64GB) - Blue']/../../../../div[3]//span[1]/span[1]";
	
	//Flipkart
	String string_FUrl = "https://www.flipkart.com";
	String string_LoginPopUp = "//button[text()= '✕']";
	String string_FSearchField = "//input[@title= 'Search for products, brands and more']";
	String string_FSearchIcon = "//button[@type= 'submit']";
	String string_FProductName = "//a//div[text()= 'APPLE iPhone 12 Mini (Blue, 64 GB)']";
	String string_FProductPrice = "//img[@id='price-info-icon']/../preceding-sibling::div/div[1]/div[1]";
	//String string_FProductPrice = "//a//div[text()= 'APPLE iPhone 12 Mini (Blue, 64 GB)']/../../div[2]/div[1]/div[1]/div[1]";
}