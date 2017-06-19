# AnjularJS-Shopping-Cart

Live Demo: http://52.14.153.185:8080/angularjsproject/

## Project Overview

* Apply learned AngularJS in a basic, interactive application. 
* Load products JSON from database API
* Allow searching and sorting on list of products
* Show live shopping cart of products in order
* Submitting clears shopping cart and saves order info to database
* Admin page for adding/removing products, and reviewing past orders

## Current Progress

* Products hard-coded, need to set up Java API to handle database management
* Products can be searched and sorted Ascending/Descending, order can be reset with single button
* Live shopping cart appears with added products and their info, working button to remove each product from order
* Java API is implemented, allowing for querying all products, insert, and delete
* Admin page is built to facilitate easy product data management

## To Do

* Allow custom quantity for each product in order
* Allow order submission and access on admin page

## Current roadblocks

* Having trouble dynamically binding separate quantity for each row to controller; each order currently only supports a quantity of 1 for each product added until this is fixed
* Having trouble trying to alternate sort on column ascending/descending each click; ascending/descending checkboxes do the trick, but a more elegant solution would be nice.

## Database Plan

### Products
| Field | Type           | Null | Key | Default  | Extra          |
|---|---|---|---|---|---|
|id	| int(11) 	 | NO | PRIMARY | NULL | auto_increment |
|name   | varchar(30)    | YES  |         | NULL | |
|price  | decimal(13, 2) | YES  |         | NULL | |

### Orders
| Field    | Type        | Null | Key | Default  | Extra          |
|---|---|---|---|---|---|
|id	   | int(11) 	 | NO | PRIMARY | NULL | auto_increment |
|name      | varchar(30) | YES  |         | NULL | |
|datetime  | date        | YES  |         | NULL | |

### ProductOrders
| Field | Type           | Null | Key | Default  | Extra          |
|---|---|---|---|---|---|
|id	      | int(11)        | NO | PRIMARY | NULL | auto_increment |
|product_name | varchar(30)    | YES  |         | NULL | |
|total_price  | decimal(13, 2) | YES  |         | NULL | |
|order_id     | int(11)        | NO | FOREIGN | NULL | |
