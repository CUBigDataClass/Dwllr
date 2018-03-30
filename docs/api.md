# Dwllr API Documentation

----------

Get a json object with all the information about a city.

----------
* **URL**

  /api/search

* **Methods:**
  
  `GET`

* **Expected Request Body**
    * **GET**
    ```json
    {
    	"city" : "<name or zip code (needs to be determined) of city>"
    }
    ```

* **Success Response:**
  * **GET**
       * Content:  
    ```json
    {
		"data" : {
			"city" : "<name of city>",
			"similarCities" : [
				"<first similar city name>",
				"<second similar city name>",
				"<third similar city name>",
				"<fourth similar city name>" 
			],
			"stats" : {
				"population" : 95951,
				"weather" : {},
				.
				.
				.
			}
		}
    }
    ```
 
* **Error Response:**

   * **GET**
   		* Content:
   		```json
   		{
   			"error" : {
   				"message" : "<message explaining error (e.g. city not found)>"
   			}
   		}
   		```

