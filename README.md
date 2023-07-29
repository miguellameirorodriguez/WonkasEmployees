# Oompa Loompa Crew App - Android

Android app for entry level test written in Kotlin and following MVVM architecture.

# Libraries used

 - Coroutines:
	 - 
	 Coroutines library is mainly used to get in an asynchronous way data from the network without blocking the main thread of the application.
 - Koin:
	 - 
	 Koin is being used to help us manage the different dependencies our components may need in our application.
 - Retrofit:
	 - 
	 Used to perform network requests in order to get the Oompa Loompas from the API. 
 - Gson:
	 - 
	 Used to parse the JSON response of the API into an object.
 - Glide:
	 - 
	 Use to load images from the internet and load them into ImageViews
 - Navigation component

## API Endpoints used

First one to get the full list of Oompa Loompas by paginating the data.
Second one to get one specific Oompa Loompa by it's id.

GET -> https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas?page=1

GET -> https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/13
