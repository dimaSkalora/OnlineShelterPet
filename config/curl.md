### Test MealRestController (application deployed in application context `OnlineShelterPet`).
> For windows use `Git Bash`

#### get All Users
`curl -s http://localhost:8080/OnlineShelterPet/rest/admin/users`

#### get Users 100001
`curl -s http://localhost:8080/OnlineShelterPet/rest/admin/users/100001`

#### get All Pets
`curl -s http://localhost:8080/OnlineShelterPet/rest/profile/pets`

#### get Pets 100003
`curl -s http://localhost:8080/OnlineShelterPet/rest/profile/pets/100003`

#### filter Pets
`curl -s "http://localhost:8080/OnlineShelterPet/rest/profile/pets/filter?startDate=2015-05-30&startTime=07:00:00&endDate=2015-05-31&endTime=11:00:00"`

#### get Pets not found
`curl -s -v http://localhost:8080/OnlineShelterPet/rest/profile/pets/100008`

#### delete Pets
`curl -s -X DELETE http://localhost:8080/OnlineShelterPet/rest/profile/pets/100002`

#### create Pets
`curl -s -X POST -d '{ "createdDate":"2018-01-30T07:00","typePet":"Dog","namePet":"d1","breed":"авчарка","sex":"m","color":"белый","age":2,"growth":50,"weight":40.5,"namePerson":вова","phone":"0731234567","email":"aedc@ds"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/OnlineShelterPet/rest/profile/pets`

#### update Pets
`curl -s -X PUT -d '{ "createdDate":"2018-01-29T17:00","typePet":"Dog","namePet":"d2","breed":"авчарка","sex":"f","color":"белый","age":1.5,"growth":60,"weight":42.5,"namePerson":anna","phone":"0731554567","email":"aeddac@ds"}}' -H 'Content-Type: application/json' http://localhost:8080/OnlineShelterPet/rest/profile/pets/100003`