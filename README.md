﻿The application uses Spring Boot for the backend and incorporates WebSocket communication to provide real-time updates to the user interface.

Here's a high-level description of the project's functionality:

User Interface (HTML and JavaScript):
The user interface is presented as a web page where users can interact with the application.
The web page displays a table that shows the current contents of the user's order backet.
Each row in the table represents a product that the user has added to the bucket. It shows the product title, the total quantity of that product in the bucket, and the price of the product.
The table also displays the total sum of the prices of all products in the bucket.
There is an input field where users can enter the ID of a product they want to add to the bucket.
There's a button labeled "Submit" that users can click to add the product with the specified ID to the bucket.
In the same way, you can add producs and users.

WebSocket Communication:
The application uses the SockJS and Stomp.js libraries to establish a WebSocket connection between the client (browser) and the server.
When a user connects to the web page, a WebSocket connection is established with the server.
The WebSocket connection allows the client to receive real-time updates from the server without needing to manually refresh the page.

Backend (Server-side) Logic:
The server-side logic is implemented using Spring Boot.
When a product is added to the basket, the server updates the basket's contents and calculates the new total sum of prices.
The server uses WebSockets to notify connected clients whenever there's a change in the basket's contents.
The server sends updates about the basket's contents over the WebSocket connection to the connected clients.
Data Storage (not shown in the provided code snippets):

The projec uses a database and form of data storage to store information about products, such as their titles and prices.
When a product is added to the backet, the server fetches the relevant product information from the data storage.
Thymeleaf Templating (not shown in the provided code snippets):

Thymeleaf is used to dynamically render HTML content based on data received from the server.
Thymeleaf expressions are used in the HTML code to display dynamic content such as product title, total and price.
