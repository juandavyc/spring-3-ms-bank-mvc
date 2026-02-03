Feature: Gestión de Cuentas (Microservicio Accounts)

  Background:
    * url baseUrl
    * def accountBase = '/api/accounts'
    * def accountPayload =
    """
    {
      "type": "CREDIT",
      "openingBalance": 2000.0,
      "clientId": "a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d"
    }
    """

  Scenario: Flujo completo de cuenta de crédito
    Given path accountBase
    And request accountPayload
    When method post
    Then status 201
    And match response.data.type == 'CREDIT'
    * def accountId = response.data.id
    Given path accountBase
    When method get
    Then status 200
    And match response.data == '#[]'
    Given path accountBase, accountId
    When method get
    Then status 200
    And match response.data.id == accountId
    And match response.data.clientId == 'a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d'
    Given path accountBase, accountId
    When method delete
    Then status 200
    And match response.data.isDeleted == true