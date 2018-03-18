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
        	"similarPlaces" : [
        		"<first similar city>",
        		"<second similar city>",
        		"<third similar city>",
        		"<fourth similar city>" 
        	],
        	"stats" : {
        		"population" : <int, population of city>,
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

