# **Statistics-restful-API**
The main use case for our API is to calculate realtime statistic from the last 60 seconds. <br>There will be two APIs, one of them is called every time a transaction is made. <br>It is also the sole input of this rest API. <br>The other one returns the statistic based of the transactions of the last 60 seconds.
<br><br><br>

# **POST /transactions**

This endpoint is called to create a new transaction.

Body:
{ <br>"amount": "12.3343",<br>
"timestamp": "2018-07-17T09:59:51.312Z"<br>
}

Where:<br>
● amount – transaction amount; a string of arbitrary length that is parsable as a
BigDecimal<br>
● timestamp – transaction time in the ISO 8601 format
YYYY-MM-DDThh:mm:ss.sssZ in the UTC timezone <br>(this is not the current
timestamp)<br><br>
Returns: Empty body with one of the following:<br>
● 201 – in case of success<br>
● 204 – if the transaction is older than 60 seconds<br>
● 400 – if the JSON is invalid<br>
● 422 – if any of the fields are not parsable or the transaction date is in the future

<br><br><br><br>



# **GET /statistics<br>**
This endpoint returns the statistics computed on the transactions within the last 60
seconds.<br>
Returns:<br><br>
Body: {<br>
"sum": "1000.00",<br>
"avg": "100.53",<br>
"max": "200000.49",<br>
"min": "50.23",<br>
"count": 10<br>
}

Where:<br>
● sum – a BigDecimal specifying the total sum of transaction value in the last 60
seconds<br>
● avg – a BigDecimal specifying the average amount of transaction value in the
last 60 seconds<br>
● max – a BigDecimal specifying single highest transaction value in the last 60
seconds<br>
● min – a BigDecimal specifying single lowest transaction value in the last 60
seconds<br>
● count – a long specifying the total number of transactions that happened in the
last 60 seconds<br><br>
All BigDecimal values always contain exactly two decimal places and use
`HALF_ROUND_UP` rounding. eg: 10.345 is returned as 10.35, 10.8 is returned as
10.80
