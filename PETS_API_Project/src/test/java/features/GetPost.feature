Feature:
    To perform a get request on the PetStore API based on the Pet Status
    validate the response payload for the status and elements for the data provided

    Scenario: Verify PetStore API response for the status and pets name
      Given I request PetStore application for the status data below
        | Status    |
        | available |
      Then I verify the response for the Status and Pet name data below
        | Status    | PetName |
        | available | doggie  |