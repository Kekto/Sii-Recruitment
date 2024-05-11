# Sii Trainee Recruitment Task
An application to manage discount codes for sales or promotions

To run the application simply start the main file of the project.

## Exposed endpoints
By default the port is set to 8080, in case of problems regarding the port being already in use,
in **application.properties** add and set **server.port** to a value of your choosing. Though
make sure that this change is reflected in the URLs of the endpoints you are trying to access.

### Promotional Codes

#### 1. Get all promotional codes: 
    [GET] http://localhost:8080/api/v1/promocodes

#### 2. Get one promotional code details:
    [GET] http://localhost:8080/api/v1/promocodes/{name}

#### 3. Add a promotional code:
    [POST] http://localhost:8080/api/v1/promocodes

    {
        "name": "PROMOCODE01",
        "expirationDate": "2024-06-30",
        "amount": 20,
        "currency": "PLN",
        "remainingUses": 100
    }

We can also use add an attribute **percentage** to specify if we want the promotional code to 
act percentage based. By default its set to false.

    {
        "name": "PROMOCODE01",
        "expirationDate": "2024-06-30",
        "amount": 20,
        "currency": "PLN",
        "remainingUses": 100,
        "percentage": true
    }

#### 4. Edit a promotional code:
    [PUT] http://localhost:8080/api/v1/promocodes/{id}

    {
        "name": "PROMOCODE01EDITED",
        "expirationDate": "2024-06-30",
        "amount": 20,
        "currency": "PLN",
        "remainingUses": 100
    }

#### 5. Delete a promotional code:
    [DELETE] http://localhost:8080/api/v1/promocodes/{id}

### Products

#### 1. Get all products:
    [GET] http://localhost:8080/api/v1/products

#### 2. Add a product:
    [POST] http://localhost:8080/api/v1/products

    {
        "name": "PRODUCT01",
        "price": 22.59,
        "currency": "PLN"
    }

We can also add an attribute **description** to specify if we want to set the product's 
description. By default its set to null.

    {
        "name": "PRODUCT01",
        "price": 22.59,
        "currency": "PLN",
        "description":"This is a description"
    }

#### 3. Edit a product:
    [PUT] http://localhost:8080/api/v1/products/{id}

    {
        "name": "PRODUCT01EDITED",
        "price": 22.59,
        "currency": "PLN"
    }

#### 4. Delete a product:
    [DELETE] http://localhost:8080/api/v1/products/{id}

### Discounts

#### 1. Check product price after discount:
    [GET] http://localhost:8080/api/v1/discounts

    {
        "productId": 1,
        "promotionalCodeName": "PROMOCODE01"
    }

### Purchases

#### 1. Get all purchases:
    [GET] http://localhost:8080/api/v1/purchases

#### 2. Purchase a product:
Purchases can be made with or without a promotional code, therefore we can, but don't have to include the **promotionalCodeName** attribute.

    [POST] http://localhost:8080/api/v1/purchases
    {
        "productId": 1
    }

    or

    {
        "productId": 1,
        "promotionalCodeName": "PROMOCODE01"
    }

