LITDemo
=======

This is a demo project intended to demonstrate the creation of a Dropwizard 7 C.R.U.D. service and a mock of the service with local integration tests.  This is the approach used in several Data Services projects (Mumford, Oracle, Switchboard 2, etc).

This was a 20% project that has not yet been completed; the DW7 service is created and will run locally but there are no tests as of yet (which is really the point of this exercise).  Hopefully someone in QA can pick this up at a later date and present to the larger group.

API
---
This is a simple CRUD service that keeps track of a sample product with an id, name, and weight.

##Examples

#Create
curl -X POST -H "Content-Type: application/json" -d '{
  "id": 1,
  "name": "TestProduct 1",
  "weight": 23
}' http://localhost:8080/product

#Read
curl -X GET http://localhost:8080/product/1

#Update
curl -X PUT -H "Content-Type: application/json" -d '{
  "id": 1,
  "name": "TestProduct 1",
  "weight": 23
}' http://localhost:8080/product

#Delete
curl -X DELETE http://localhost:8080/product/1

