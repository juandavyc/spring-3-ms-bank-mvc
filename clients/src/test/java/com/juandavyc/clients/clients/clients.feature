Feature: Gestión de Clientes (CRUD)

  Background:
    * url baseUrl
    * def clientBase = '/api/clients'
    * def randomId = function(){ return Math.floor(Math.random() * 1000000000).toString() }
    * def identification = randomId()

    * def clientPayload =
    """
    {
      "fullName": "Jose Lema",
      "gender": "MALE",
      "age": 30,
      "identification": "#(identification)",
      "address": "Otavalo sn y principal",
      "phoneNumber": "098254785",
      "password": "secure1234"
    }
    """

  Scenario: Ciclo de vida exitoso de un cliente

    Given path clientBase
    And request clientPayload
    When method post
    Then status 201
    And match response.data.fullName == 'Jose Lema'
    * def clientId = response.data.id

    Given path clientBase, clientId
    When method get
    Then status 200
    And match response.data.identification == identification

    * set clientPayload.phoneNumber = '555-2222345'
    * remove clientPayload.identification

    Given path clientBase, clientId
    And request clientPayload
    When method put
    Then status 200
    And match response.data.phoneNumber == '555-2222345'

    Given path clientBase, clientId
    When method delete
    Then status 200
    And match response.data.isDeleted == true
    And match response.status == 'DELETED'